package org.example.ie.service.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.entity.GoodsEntity;
import org.example.ie.mapper.admin.GoodsMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper,GoodsEntity> implements GoodsService {

    @Override
    public ResponseResult<Map<String,Object>> getGoods(GoodsEntity goodsEntity) {
        IPage<GoodsEntity> iPage = new Page<>(goodsEntity.getCurrentPage(), 2);

        IPage<GoodsEntity> page = page(iPage);

        return null;
    }
}
