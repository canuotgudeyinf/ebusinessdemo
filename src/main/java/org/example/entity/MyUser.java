package org.example.entity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
@Data
@TableName("user")
public class MyUser {
    private Integer uid;//与数据表中的字段名相同
    private String uname;
    private String usex;
}
