package org.example.ie.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.entity.OrdersEntity;

import java.util.List;
import java.util.Map;

public interface OrderService extends IService<OrdersEntity> {

    ResponseResult<Map<String, Object>> getAllOrders(OrdersEntity ordersEntity);

    ResponseResult<OrdersEntity> getOrdersDetail(OrdersEntity ordersEntity);
}
