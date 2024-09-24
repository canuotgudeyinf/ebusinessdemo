package org.example.ie.controller.admin;

import org.example.ie.common.http.ResponseResult;
import org.example.ie.common.sercurity.anno.AuthIgrone;
import org.example.ie.entity.GoodsEntity;
import org.example.ie.service.admin.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
            //System.out.println(filecontent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileName = file.getOriginalFilename();
    }

    @AuthIgrone
    @PostMapping("/getGoods")
    public ResponseResult<Map<String, Object>> getGoods(@RequestBody GoodsEntity goodsEntity) {
        return goodsService.getGoods(goodsEntity);
    }

    @AuthIgrone
    @PostMapping("/add")
    public ResponseResult<Map<String, String>> add(@RequestBody GoodsEntity goodsEntity) {
        goodsEntity.setFileName(fileName);
        goodsEntity.setLogoFile(filecontent);

        return goodsService.add(goodsEntity);
    }

    @AuthIgrone
    @PostMapping("/delete")
    public ResponseResult<Map<String, String>> delete(@RequestBody GoodsEntity goodsEntity) {
        return goodsService.delete(goodsEntity);
    }

    @AuthIgrone
    @PostMapping("/getAdvGoods")
    public ResponseResult<List<GoodsEntity>> getAdvGoods() {
        return goodsService.getAdvGoods();

    }

    @AuthIgrone
    @PostMapping("/getGoodsIndex")
    public ResponseResult<List<GoodsEntity>> getGoodsIndex(@RequestBody GoodsEntity goodsEntity) {
        return goodsService.getGoodsIndex(goodsEntity);
    }

    @AuthIgrone//TODO
    @PostMapping("/getGoodsById")
    public ResponseResult<GoodsEntity> getGoodsById(@RequestBody GoodsEntity goodsEntity) {
        return goodsService.getGoodsById(goodsEntity);
    }
}