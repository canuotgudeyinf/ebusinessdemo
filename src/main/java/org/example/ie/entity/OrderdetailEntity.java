package org.example.ie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("orderdetail")
@SuppressWarnings("all")
public class OrderdetailEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer orderbasetableId;
    private Integer goodstableId;
    private Integer shoppingnum;
}
