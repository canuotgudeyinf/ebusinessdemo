package org.example.ie.service.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.entity.GoodsTypeEntity;
import org.example.ie.mapper.admin.GoodsTypeMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsTypeEntity> implements GoodsTypeService {
    @Override
    public ResponseResult<Map<String, Object>> add(GoodsTypeEntity goodsType) {
        if(save(goodsType))
            return ResponseResult.getMessageResult(null,"A001");
        return ResponseResult.getMessageResult(null,"A002");
    }

    @Override
    public ResponseResult<Map<String, Object>> update(GoodsTypeEntity goodsType) {
        if(updateById(goodsType))
            return ResponseResult.getMessageResult(null, "A001");
        return ResponseResult.getMessageResult(null,"A002");
    }

    @Override
    public ResponseResult<Map<String, Object>> delete(GoodsTypeEntity goodsType) {
        if(removeById(goodsType.getId()))
            return ResponseResult.getMessageResult(null, "A001");
        return ResponseResult.getMessageResult(null, "A002");
    }

    @Override
    public ResponseResult<Map<String,Object>> getGoodsType(GoodsTypeEntity goodsTypeEntity) {
        IPage<GoodsTypeEntity> iPage = new Page<>(goodsTypeEntity.getCurrentPage(), 2);
        IPage<GoodsTypeEntity> page = page(iPage);
        Map<String ,Object> myres = new HashMap<>();
        myres.put("allGoodsType",page.getRecords());
        myres.put("totalPage",page.getPages());
        return ResponseResult.getSuccessResult(myres);
    }

}
