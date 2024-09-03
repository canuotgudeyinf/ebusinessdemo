package org.example.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.test.entity.MyUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<MyUser> {
    List<MyUser> myFindAll();
}
