package org.example.ie.service.before;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.entity.CartEntity;

import java.util.Map;

public interface CartService extends IService<CartEntity> {
    ResponseResult<Map<String, Object>> addCart(CartEntity cartEntity);

    ResponseResult<Map<String, Object>> bupDateCart(CartEntity cartEntity);
}
