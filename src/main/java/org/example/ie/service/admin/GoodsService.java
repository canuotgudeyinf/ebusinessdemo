package org.example.ie.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.entity.CartEntity;
import org.example.ie.entity.GoodsEntity;

import java.util.List;
import java.util.Map;

public interface GoodsService extends IService<GoodsEntity> {
    ResponseResult<Map<String, Object>> getGoods(GoodsEntity goodsEntity);

    ResponseResult<Map<String, String>> add(GoodsEntity goodsEntity);

    ResponseResult<Map<String, String>> delete(GoodsEntity goodsEntity);
    //首页获取收藏商品
    //ResponseResult<Map<String, Object>> iPageMyFocusGoods(GoodsEntity goodsEntity);

    //我的购物车，此方法调用结束后，会调用cartService.bupDateCart
    ResponseResult<List<Map<String, Object>>> myCartGoods(CartEntity cartEntity);

    //获取广告商品
    ResponseResult<List<GoodsEntity>>  getAdvGoods();
    //
    ////首页商品列表
    ResponseResult<List<GoodsEntity>>  getGoodsIndex(GoodsEntity goodsEntity);
    //
    ////首页商品点击获取详情
    ResponseResult<GoodsEntity>  getGoodsById(GoodsEntity goodsEntity);
}
