package com.ueumd.tech.config;

import com.ueumd.tech.common.TokenPackageType;
import com.ueumd.tech.common.annotation.JwtIgnore;
import com.ueumd.tech.common.annotation.ResourceSecurity;
import com.ueumd.tech.common.annotation.TokenIsIgnore;
import com.ueumd.tech.common.constant.HeaderValue;
import com.ueumd.tech.common.entity.TokenPackage;
import com.ueumd.tech.common.exception.PermissionException;
import com.ueumd.tech.common.utils.IPUtil;
import com.ueumd.tech.common.utils.RedisUtils;
import com.ueumd.tech.common.utils.StringUtils;
import com.ueumd.tech.common.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: JwtInterceptor
 * @Description:
 * @Version: 1.0
 **/
@Slf4j
@Component
public class JwtInterceptor implements AsyncHandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
        request.setAttribute(HeaderValue.USER_ID, 0);
        String token = request.getHeader(HeaderValue.TOKEN);
        if(redisUtils == null){
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            redisUtils = factory.getBean("redisUtils", RedisUtils.class);
        }
        if (handler instanceof HandlerMethod) {
            boolean isSafe = checkResourceSecurity(request, handler);
            if (!isSafe) return false;
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
            if (jwtIgnore != null) {
                return true;
            }
            TokenIsIgnore tokenIsIgnore = handlerMethod.getMethodAnnotation(TokenIsIgnore.class);
            if (tokenIsIgnore != null) {
                if (StringUtils.isNotEmpty(token)) {
                    checkToken(token, request);
                }
                return true;
            }
        }
        if(StringUtils.isEmpty(token)){
            throw PermissionException.PERMISSION_USER_NOT_LOGIN;
        }
        checkToken(token, request);
        return true;
    }

    private void checkToken(String token, HttpServletRequest request) {
        //返回token对象的包装类
        TokenPackage tokenPackage = TokenUtils.getTokenPackageByToken(token);
        if (tokenPackage != null && TokenPackageType.USER.getName().equals(tokenPackage.getType())) {
            String key = TokenUtils.TOKEN_ENCRY_KEY + tokenPackage.getType() + "_" + tokenPackage.getId();
            String message = redisUtils.get(key);
            if (message == null) {
                throw PermissionException.PERMISSION_TOKEN_EXPIRE;
            } else {
                request.setAttribute(HeaderValue.USER_ID, tokenPackage.getId());
                //缓存未过时，因为进行了操作，将其过时时间重置为15分钟
                redisUtils.set(key, token, TokenUtils.TOKEN_EXPIRE_TIME);
            }
        }
    }

    private boolean checkResourceSecurity(HttpServletRequest request, Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        ResourceSecurity resourceSecurity = handlerMethod.getMethodAnnotation(ResourceSecurity.class);
        if (resourceSecurity != null) {
            // 执行进一步验证，判断1s之内调用次数
            final Method method = handlerMethod.getMethod();
            final String methodName = method.getName();
            final String ipAddr = IPUtil.getIpAddr(request);
            String key = ipAddr + methodName;
            Map<String, Object> result = redisUtils.get(key);
            if (result != null) {
                final Long lastTime = (Long) result.get("lastTime");
                int times = (int) result.get("times");
                if (System.currentTimeMillis() - lastTime < WebConfig.intervalTime) {
                    // 如果5s（指定时间）内之内调用次数超过10次（次数可配置），则拦截
                    if (times > WebConfig.limitTimes) {
                        // result.put("times", 1);
                        result.put("lastTime", System.currentTimeMillis());
                        redisUtils.set(key, result);
                        return false;
                    }
                    result.put("times", ++times);
                } else {
                    result.put("times", 1);
                    result.put("lastTime", System.currentTimeMillis());
                }
                redisUtils.set(key, result);
            } else {
                result = new HashMap<>();
                result.put("times", 1);
                result.put("lastTime", System.currentTimeMillis());
                redisUtils.set(key, result);
            }
            return true;
        }
        return true;
    }

}
