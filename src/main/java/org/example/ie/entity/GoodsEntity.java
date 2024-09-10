package org.example.ie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
@TableName("goodstable")
@SuppressWarnings("all")
public class GoodsEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String gname;
    private Double goprice;
    private Double grprice;
    private Integer gstore;
    private String gpicture;
    private Integer isAdvertisement;
    private Integer goodstypeId;
    @TableField(exist = false)
    private String typename;
    @TableField(exist = false)
    private byte[] logoFile;
    @TableField(exist = false)
    private String fileName;
    @TableField(exist = false)
    private String act;
    @TableField(exist = false)
    private Integer currentPage;
    @TableField(exist = false)
    private Integer busertableId;
}
