package org.example.ie.mapper.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.ie.entity.CartEntity;
import org.example.ie.entity.GoodsEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoodsMapper extends BaseMapper<GoodsEntity> {
        List<Map<String,Object>> myCartGoods(int uid);
}
