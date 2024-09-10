package org.example.ie.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.entity.GoodsEntity;

import java.util.List;
import java.util.Map;

public interface GoodsService extends IService<GoodsEntity> {

    ResponseResult<Map<String,Object>> getGoods(GoodsEntity goodsEntity);
}
