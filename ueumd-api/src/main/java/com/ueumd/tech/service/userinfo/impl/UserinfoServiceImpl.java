package com.ueumd.tech.service.userinfo.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ueumd.tech.common.entity.User;
import com.ueumd.tech.common.exception.BusinessException;
import com.ueumd.tech.common.exception.ExceptionCode;
import com.ueumd.tech.common.utils.TokenUtils;
import com.ueumd.tech.mapper.userinfo.UserinfoMapper;
import com.ueumd.tech.model.entity.userinfo.Userinfo;
import com.ueumd.tech.model.vo.userinfo.LoginUserinfoVO;
import com.ueumd.tech.model.vo.userinfo.UserinfoLoginResultVO;
import com.ueumd.tech.service.userinfo.UserinfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

import static com.ueumd.tech.common.constant.UserConstant.USER_LOGIN_STATE;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-06 10:31
 */
@Service
@Slf4j
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements UserinfoService {

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "ueumd";

    @Override
    public long userRegister(String username, String password, String checkPassword) {
        return 0;
    }

    @Override
    public UserinfoLoginResultVO userLogin(String username, String password, HttpServletRequest request) {
        if (StringUtils.isAnyBlank(username, password)) {
            throw  new BusinessException(ExceptionCode.PARAMS_ERROR);
        }

        if (username.length() < 4) {
            throw new BusinessException(ExceptionCode.PARAMS_ERROR);
        }

        if (password.length() < 8) {
            throw new BusinessException(ExceptionCode.PARAMS_ERROR);
        }

        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());

        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", encryptPassword);

        Userinfo userinfo = this.baseMapper.selectOne(queryWrapper);

        if (userinfo == null) {
            log.info("user login failed, username cannot match password");
            throw new BusinessException(ExceptionCode.PARAMS_ERROR);
        }

        User user = new User();
        user.setId(userinfo.getId());

        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        String token = TokenUtils.getToken(user);
        return new UserinfoLoginResultVO(token, user);
    }

    public LoginUserinfoVO getLoginUserVO(Userinfo userinfo) {
        if (userinfo == null) {
            return null;
        }
        LoginUserinfoVO loginUserVO = new LoginUserinfoVO();
        BeanUtils.copyProperties(userinfo, loginUserVO);
        return loginUserVO;
    }

    @Override
    public LoginUserinfoVO getUserinfo(Long userId) {
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        Userinfo userinfo = this.baseMapper.selectOne(queryWrapper);
        if (userinfo == null) {
            log.info("user login failed, username cannot match password");
            throw new BusinessException(ExceptionCode.PARAMS_ERROR);
        }
        LoginUserinfoVO loginUserinfoVO = new LoginUserinfoVO();
        BeanUtils.copyProperties(userinfo, loginUserinfoVO);
        return loginUserinfoVO;
    }

    private UserinfoLoginResultVO getLoginResult(Userinfo userInfo) {
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        String token = TokenUtils.getToken(user);
        return new UserinfoLoginResultVO(token, user);
    }
}
