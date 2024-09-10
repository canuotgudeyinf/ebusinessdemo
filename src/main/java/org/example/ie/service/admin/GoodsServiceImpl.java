package org.example.ie.service.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.ie.entity.GoodsEntity;
import org.example.ie.mapper.admin.GoodsMapper;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper,GoodsEntity> implements GoodsService {

}
