package org.example.ie.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.entity.BUserEntity;
import org.example.ie.entity.CartEntity;
import org.example.ie.entity.GoodsEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface GoodsService extends IService<GoodsEntity> {

    ResponseResult<Map<String,Object>> getGoods(GoodsEntity goodsEntity);

    ResponseResult<Map<String, Object>> add(GoodsEntity goodsEntity);

    ResponseResult<Map<String, Object>> del(GoodsEntity goodsEntity);

    ResponseResult<Map<String, Object>> fileInit(MultipartFile file);

    ResponseResult<Map<String, Object>> update(GoodsEntity goodsEntity);

    ResponseResult<List<GoodsEntity>> getAdvGoods();

    ResponseResult<List<Map<String,Object>>> myCartGoods(CartEntity cartEntity);

    ResponseResult<List<GoodsEntity>> getGoodsIndex(GoodsEntity goodsEntity);

    ResponseResult<GoodsEntity> getGoodsById(GoodsEntity goodsEntity);
}
