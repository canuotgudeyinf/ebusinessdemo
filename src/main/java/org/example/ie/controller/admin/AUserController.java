package org.example.ie.controller.admin;

import org.example.ie.common.http.ResponseResult;
import org.example.ie.common.sercurity.anno.AuthIgrone;
import org.example.ie.entity.AUserEntity;
import org.example.ie.service.admin.AUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AUserController {
    @Autowired
    private AUserService aUserService;

    @AuthIgrone
    @PostMapping("/login")
    public ResponseResult<Map<String, String>> login(@RequestParam AUserEntity aUserEntity) {
        return aUserService.login(aUserEntity);
    }
}
