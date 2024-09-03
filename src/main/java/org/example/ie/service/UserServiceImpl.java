package org.example.ie.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.ie.entity.MyUser;
import org.example.ie.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, MyUser> implements UserService{
}
