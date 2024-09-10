package org.example.ie.controller.admin;

import org.example.ie.common.http.ResponseResult;
import org.example.ie.common.sercurity.anno.AuthIgrone;
import org.example.ie.entity.GoodsTypeEntity;
import org.example.ie.service.admin.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/type")
public class GoodsTypeController {
    @Autowired
    private GoodsTypeService goodsTypeService;


    @AuthIgrone
    @PostMapping("/getAllGoodsType")
    public ResponseResult<List<GoodsTypeEntity>> getAllGoodsType() {
        return ResponseResult.getSuccessResult(goodsTypeService.list());
    }

    @AuthIgrone
    @PostMapping("getGoodsType")
    public ResponseResult<Map<String,Object>> getGoodsType(@RequestBody GoodsTypeEntity goodsTypeEntity) {
        return goodsTypeService.getGoodsType(goodsTypeEntity);
    }

    @PostMapping("/add")
    public ResponseResult<Map<String, Object>> addGoodsType(@RequestBody GoodsTypeEntity goodsType) {
        return goodsTypeService.add(goodsType);
    }

    @PostMapping("/Update")
    public ResponseResult<Map<String, Object>> updateGoodsType(@RequestBody GoodsTypeEntity goodsType) {
        return goodsTypeService.update(goodsType);
    }

    @PostMapping("/delete")
    public ResponseResult<Map<String, Object>> deleteGoodsType(@RequestBody GoodsTypeEntity goodsType) {
        return goodsTypeService.delete(goodsType);
    }


}
