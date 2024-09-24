package org.example.ie.controller.admin;

import org.example.ie.common.http.ResponseResult;
import org.example.ie.entity.OrdersEntity;
import org.example.ie.service.admin.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/orders")
public class OrdersController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/allorders")
    public ResponseResult<Map<String,Object>> allOrders(@RequestBody OrdersEntity ordersEntity) {
        return orderService.getAllOrders(ordersEntity);
    }

    @PostMapping("/getordersdetail")
    public ResponseResult<OrdersEntity> getOrdersDetail(@RequestBody OrdersEntity ordersEntity) {
        return orderService.getOrdersDetail(ordersEntity);
    }
}
