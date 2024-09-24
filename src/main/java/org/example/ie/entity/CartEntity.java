package org.example.ie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("carttable")
@SuppressWarnings("all")  //去除代码的破浪线
public class CartEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer busertableId;
    private Integer goodstableId;
    private Integer shoppingnum;
    @TableField(exist = false)
    private List<Integer> bshoppingnum;
    @TableField(exist = false)
    private List<Integer> bcid;
}
