package com.ueumd.tech.controller.userinfo;

import com.ueumd.tech.common.annotation.JwtIgnore;
import com.ueumd.tech.common.constant.HeaderValue;
import com.ueumd.tech.common.entity.User;
import com.ueumd.tech.common.exception.BusinessException;
import com.ueumd.tech.common.exception.ExceptionCode;
import com.ueumd.tech.common.utils.TokenUtils;
import com.ueumd.tech.common.vo.ResponseDTO;
import com.ueumd.tech.model.dto.userinfo.UserLoginRequestDTO;
import com.ueumd.tech.model.vo.userinfo.LoginUserinfoVO;
import com.ueumd.tech.model.vo.userinfo.UserinfoLoginResultVO;
import com.ueumd.tech.service.userinfo.UserinfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-05 18:38
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserinfoController {

    @Resource
    private UserinfoService userinfoService;

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @param request
     * @return
     */
    @JwtIgnore
    @PostMapping("/login")
    public ResponseDTO<UserinfoLoginResultVO> userLogin(@RequestBody UserLoginRequestDTO userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ExceptionCode.PARAMS_ERROR);
        }
        String username = userLoginRequest.getUsername();
        String password = userLoginRequest.getPassword();
        if (StringUtils.isAnyBlank(username, password)) {
            throw new BusinessException(ExceptionCode.PARAMS_ERROR);
        }
        UserinfoLoginResultVO loginUserVO = userinfoService.userLogin(username, password, request);
        return new ResponseDTO<>(loginUserVO);
    }

    @GetMapping("/getUserinfo")
    public ResponseDTO<LoginUserinfoVO> getUserinfo(@RequestAttribute(HeaderValue.USER_ID) Long userId, @RequestHeader(HeaderValue.TOKEN) String token){

        User user = TokenUtils.getUserByToken(token);

        System.out.println("==========" + userId);
        System.out.println("==========" + user.toString());

        log.info("------11111111111-----" + userId);

        return new ResponseDTO<>(userinfoService.getUserinfo(userId));
    }
}
