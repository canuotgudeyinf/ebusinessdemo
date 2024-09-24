package org.example.ie.service.before;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.entity.BUserEntity;

import java.util.Map;

public interface BUserService extends IService<BUserEntity> {
    ResponseResult<Map<String, String>> login(BUserEntity bUserEntity);

    ResponseResult<Map<String, String>> register(BUserEntity bUserEntity);
}
