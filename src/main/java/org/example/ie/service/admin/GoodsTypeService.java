package org.example.ie.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.entity.GoodsTypeEntity;

import java.util.Map;

public interface GoodsTypeService extends IService<GoodsTypeEntity> {

    ResponseResult<Map<String, Object>> add(GoodsTypeEntity goodsType);

    ResponseResult<Map<String, Object>> update(GoodsTypeEntity goodsType);

    ResponseResult<Map<String, Object>> delete(GoodsTypeEntity goodsType);

    ResponseResult<Map<String,Object>> getGoodsType(GoodsTypeEntity goodsTypeEntity);
}
