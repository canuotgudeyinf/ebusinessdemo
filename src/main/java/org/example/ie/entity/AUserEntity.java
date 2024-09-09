package org.example.ie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@SuppressWarnings("all")  //去除代码的破浪线
@TableName("ausertable")
public class AUserEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String aname;
    private String apwd;
}
