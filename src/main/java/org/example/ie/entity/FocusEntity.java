package org.example.ie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("focustable")
@SuppressWarnings("all")
public class FocusEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer busertableId;
    private Integer goodstableId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date focustime;
}
