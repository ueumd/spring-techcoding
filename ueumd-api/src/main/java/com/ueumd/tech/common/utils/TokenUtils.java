package com.ueumd.tech.common.utils;

import com.alibaba.fastjson.JSON;
import com.ueumd.tech.common.TokenPackageType;
import com.ueumd.tech.common.entity.BaseToken;
import com.ueumd.tech.common.entity.TokenPackage;
import com.ueumd.tech.common.entity.User;
import com.ueumd.tech.common.exception.PermissionException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-06 22:59
 */
@Component
public class TokenUtils {

    // 加密KEY
    public static final String TOKEN_ENCRY_KEY  = "MDF4GgYiY2Q0NjIxZPT3HaxGuoZTgtMjYyN2I0Zop";

    // token过期时间 7 天
    public static final Long TOKEN_EXPIRE_TIME = 7 * 24 * 3600L;

    // token默认时间,单位分
    private static final Integer TOKEN_DEFAULT_TIME = 24 * 60 * 30;

    private static String jwsKey;

    private static RedisUtils redisUtils;

    /**
     * 静态属性注入
     * @param redisUtils
     */
    @Autowired
    public TokenUtils(RedisUtils redisUtils) {
        TokenUtils.redisUtils = redisUtils;
    }

    public static User getUserByToken(String token) {
        return getToken(token, User.class);
    }

    /**
     * 返回token的统一方法
     * @param tokenPackage toke对象的包装类
     * @param id           token对象的主键
     * @return
     */
    public static String getTokenUnifiedApproach(TokenPackage tokenPackage, long id) {
        String toJSONString = JSON.toJSONString(tokenPackage);
        Claims claims = Jwts.claims().setSubject(toJSONString);
        LocalDateTime localDateTime = LocalDateTime.now();
        Date expirationTime = Date.from(localDateTime.plusMinutes(TOKEN_DEFAULT_TIME).atZone(ZoneId.systemDefault()).toInstant());
        String jws = Jwts.builder().setClaims(claims).setId(String.valueOf(id))
                .setIssuedAt(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(expirationTime).signWith(SignatureAlgorithm.HS512, jwsKey).compact();
        return jws;
    }

    public static String getToken(User user) {
        if (user == null) {
            return "";
        }
        String jws = getTokenUnifiedApproach(user, user.getId());
        String key = TOKEN_ENCRY_KEY + user.getType() + "_" + user.getId();
        redisUtils.set(key, jws, TOKEN_EXPIRE_TIME);
        return jws;
    }

    public static <T> T getToken(String jws, Class<T> clazz) throws PermissionException {
        if (StringUtils.isEmpty(jws)) {
            throw PermissionException.PERMISSION_TOKEN_EXPIRE;
        } else {
            Claims claims = Jwts.parser().setSigningKey(jwsKey).parseClaimsJws(jws).getBody();
            String subject = claims.getSubject();
            return JSON.parseObject(subject, clazz);
        }
    }

    public static TokenPackage getTokenPackageByToken(String token) throws PermissionException {
        return getToken(token, BaseToken.class);
    }


    public static void checkUserToken(String token) throws PermissionException {
        if(StringUtils.isEmpty(token)) {
            throw PermissionException.PERMISSION_TOKEN_EXPIRE;
        } else {
            String subject = Jwts.parser().setSigningKey(jwsKey).parseClaimsJws(token).getBody().getSubject();
            User user = JSON.parseObject(subject, User.class);

            if (user == null || (!TokenPackageType.USER.getName().equals(user.getType()))) {
                throw PermissionException.PERMISSION_TOKEN_EXPIRE;
            }
        }
    }

    @Value("${system.config.jws-key:xrUPMmchaD59NPX0}")
    private void setJwsKey(String jwsKey) {
        TokenUtils.jwsKey = jwsKey;
    }
}
