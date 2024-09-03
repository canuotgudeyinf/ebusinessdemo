package org.example.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyUserController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/findAll")
    public List<MyUser> findAll(){
        return userMapper.selectList(null);
    }
    @GetMapping("/myFindAll")
    public List<MyUser> myFindAll(){
        return userMapper.myFindAll();
    }
}
