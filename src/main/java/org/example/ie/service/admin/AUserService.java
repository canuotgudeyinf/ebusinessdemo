package org.example.ie.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.entity.AUserEntity;

import java.util.Map;

public interface AUserService extends IService<AUserEntity> {
    ResponseResult<Map<String, String>> login(AUserEntity aUserEntity);

    ResponseResult<Map<String, Object>> changeUserName(AUserEntity aUserEntity);
}
