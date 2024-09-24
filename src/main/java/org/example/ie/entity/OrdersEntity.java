package org.example.ie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@SuppressWarnings("all")
@TableName("orderbasetable")
public class OrdersEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer busertableId;
    private Double amount;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date orderdate;
    @TableField(exist = false)
    private Integer currentPage;
    @TableField(exist = false)
    private String startDate;
    @TableField(exist = false)
    private String endDate;
    @TableField(exist = false)
    private Double totalamount;//封装查询结果
    @TableField(exist = false)
    private String months;//封装查询结果
    @TableField(exist = false)
    private List<Integer> bshoppingnum;
    @TableField(exist = false)
    private List<Integer> bgid;
}
