package org.example.ie.service.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.ie.entity.GoodsTypeEntity;
import org.example.ie.mapper.admin.GoodsTypeMapper;
import org.springframework.stereotype.Service;

@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsTypeEntity> implements GoodsTypeService {
}
