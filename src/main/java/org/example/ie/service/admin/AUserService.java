package org.example.ie.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.entity.AUserEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface AUserService extends IService<AUserEntity> {
    ResponseResult<Map<String, String>> login(AUserEntity aUserEntity);
}
