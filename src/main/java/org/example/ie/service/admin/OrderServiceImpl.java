package org.example.ie.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.entity.OrdersEntity;
import org.example.ie.mapper.admin.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrdersEntity> implements OrderService {
    OrderMapper orderMapper;
    @Override
    public ResponseResult<Map<String, Object>> getAllOrders(OrdersEntity ordersEntity) {
        if(ordersEntity.getCurrentPage() == null || ordersEntity.getCurrentPage() < 1){
            ordersEntity.setCurrentPage(1);

        }
        LambdaQueryWrapper<OrdersEntity> queryWrapper = new LambdaQueryWrapper<>();
        if(ordersEntity.getId() != null ){
            queryWrapper.eq(OrdersEntity::getId,ordersEntity.getId());
        }

        IPage<OrdersEntity> iPage = new Page<>(ordersEntity.getCurrentPage(), 2);

        IPage<OrdersEntity> page = page(iPage,queryWrapper);
        Map<String ,Object> myres = new HashMap<>();
        myres.put("allOrders",page.getRecords());
        myres.put("totalPage",page.getPages());
        return ResponseResult.getSuccessResult(myres);
    }

    @Override
    public ResponseResult<OrdersEntity> getOrdersDetail(OrdersEntity ordersEntity) {
        return ResponseResult.getSuccessResult(orderMapper.selectById(ordersEntity.getId()));
    }
}
