package org.example.ie.controller.before;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.common.sercurity.anno.AuthIgrone;
import org.example.ie.common.sercurity.utils.ConfigrarionBean;
import org.example.ie.common.sercurity.utils.RedisUtil;
import org.example.ie.entity.BUserEntity;
import org.example.ie.service.before.BUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * @classname: BUserController
 * @Author: xujia
 * @Description: 用户前端控制器
 * @Date: 2024/9/5  23:00
 * @Param:
 * @version: 1.0
 **/
@RestController
@RequestMapping("/api/before")
@SuppressWarnings("all")
public class BUserController {
    @Autowired
    private BUserService bUserService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ConfigrarionBean config;

    @AuthIgrone
    @PostMapping("/register")
    public ResponseResult<Map<String, String>> register(@RequestBody BUserEntity bUserEntity){
       return bUserService.register(bUserEntity);
    }

    @AuthIgrone
    @PostMapping("/login")
    public ResponseResult<Map<String, String>> login(@RequestBody BUserEntity bUserEntity){
        return bUserService.login(bUserEntity);
    }

    @AuthIgrone
    @GetMapping("/getcode")
    public void getcode(HttpServletResponse response) throws IOException {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(116, 30, 4, 10);
        redisUtil.set("code",circleCaptcha.getCode(), config.getRedisExpiration());//验证码存到redis缓存中
        ServletOutputStream outputStream = response.getOutputStream();
        circleCaptcha.write(outputStream);
        outputStream.close();
    }


}
