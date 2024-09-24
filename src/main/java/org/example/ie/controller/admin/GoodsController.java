package org.example.ie.controller.admin;

import org.example.ie.common.http.ResponseResult;
import org.example.ie.common.sercurity.anno.AuthIgrone;
import org.example.ie.entity.BUserEntity;
import org.example.ie.entity.CartEntity;
import org.example.ie.entity.GoodsEntity;
import org.example.ie.service.admin.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    private static String fileName;
    private static byte[] filecontent;

    @AuthIgrone
    @PostMapping("/fileInit")
    public void fileInit(@RequestBody MultipartFile file) {
        //MultipartFile对象不能在另一个方法中使用，所以把文件对象变成字节数组
        try {
            filecontent = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileName = file.getOriginalFilename();
    }

    @PostMapping("/getGoods")
    public ResponseResult<Map<String,Object>> getGoods(@RequestBody GoodsEntity goodsEntity) {
        return goodsService.getGoods(goodsEntity);
    }

    @PostMapping("/add")
    public ResponseResult<Map<String, Object>> add(@RequestBody GoodsEntity goodsEntity) {
        return goodsService.add(goodsEntity);
    }

    @PostMapping("/delete")
    public ResponseResult<Map<String, Object>> delete(@RequestBody GoodsEntity goodsEntity) {
        return goodsService.del(goodsEntity);
    }

    @PostMapping("/update")
    public ResponseResult<Map<String, Object>> update(@RequestBody GoodsEntity goodsEntity) {
        return goodsService.update(goodsEntity);
    }

    @AuthIgrone
    @PostMapping("/getAdvGoods")
    public ResponseResult<List<GoodsEntity>>  getAdvGoods(){
        return goodsService.getAdvGoods();
    }

    @PostMapping("/getCartGoods")
    public ResponseResult<List<Map<String,Object>>> getCartGoods(@RequestBody CartEntity cartEntity){
        return goodsService.myCartGoods(cartEntity);
    }

    @AuthIgrone
    @PostMapping("/getGoodsIndex")
    public ResponseResult<List<GoodsEntity>> getGoodsIndex(@RequestBody GoodsEntity goodsEntity){
        return goodsService.getGoodsIndex(goodsEntity);
    }

    @AuthIgrone
    @PostMapping("/getGoodsById")
    public ResponseResult<GoodsEntity> getGoodsById(@RequestBody GoodsEntity goodsEntity){
        return goodsService.getGoodsById(goodsEntity);
    }

}
