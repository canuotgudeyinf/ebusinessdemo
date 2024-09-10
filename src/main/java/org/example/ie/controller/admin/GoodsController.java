package org.example.ie.controller.admin;

import org.example.ie.common.http.ResponseResult;
import org.example.ie.entity.GoodsEntity;
import org.example.ie.service.admin.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/getGoods")
    public ResponseResult<Map<String,Object>> getGoods(@RequestBody GoodsEntity goodsEntity) {
        return goodsService.getGoods(goodsEntity);
    }
}
