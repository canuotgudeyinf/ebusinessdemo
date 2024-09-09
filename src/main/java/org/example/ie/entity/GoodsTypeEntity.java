package org.example.ie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@SuppressWarnings("all")
@TableName("goodstype")
public class GoodsTypeEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String typename;
    @TableField(exist = false)
    private Integer currentPage;
}
