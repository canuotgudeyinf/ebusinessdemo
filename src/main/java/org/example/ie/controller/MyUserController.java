package org.example.ie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.ie.entity.MyUser;
import org.example.ie.mapper.UserMapper;
import org.example.ie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MyUserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @GetMapping("/testMapperSave")
    public MyUser testMapperSave() {
        MyUser mu = new MyUser();
        mu.setUname("testMapperSave陈恒1");
        mu.setUsex("女");
        userMapper.insert(mu);
        //实体类主键属性使用@TableId注解后，主键自动回填
        return mu;
    }

    @GetMapping("/testMapperDelete")
    public int testMapperDelete() {
        List<Long> list = Arrays.asList(27L, 55L, 97L);
        return userMapper.deleteByIds(list);

    }

    @GetMapping("/testMapperUpdate")
    public MyUser testMapperUpdate() {
        MyUser mu = new MyUser();
        mu.setUid(1);
        mu.setUname("李四");
        mu.setUsex("男");
        userMapper.updateById(mu);
        return mu;
    }

    /*
     * @Description:
     * @param []
     * @Return: java.util.List<com.ch.ch7_3.entity.MyUser>
     * @Author: Roy xu
     * @Date: 2024/9/3 14:11
     **/
    @GetMapping("/testMapperSelect")
    public List<MyUser> testMapperSelect() {

        return userMapper.selectList(null);
    }

    @GetMapping("/testModelSave")
    public MyUser testModelSave() {
        MyUser mu = new MyUser();
        mu.setUname("testModelSave陈恒2");
        mu.setUsex("男");
        mu.insert();
        return mu;
    }

    @GetMapping("/testServiceSave")
    public List<MyUser> testServiceSave() {
        MyUser mu1 = new MyUser();
        mu1.setUname("testServiceSave陈恒1");
        mu1.setUsex("女");
        MyUser mu2 = new MyUser();
        mu2.setUname("testServiceSave陈恒2");
        mu2.setUsex("男");
        List<MyUser> list = Arrays.asList(mu1, mu2);
        userService.saveBatch(list);
        return list;
    }

    @GetMapping("/testServiceUpdate")
    public List<MyUser> testServiceUpdate() {
        MyUser mu1 = new MyUser();
        mu1.setUid(98);
        mu1.setUname("testServiceSave陈恒110");
        mu1.setUsex("女");
        MyUser mu2 = new MyUser();
        mu2.setUid(99);
        mu2.setUname("testServiceSave陈恒220");
        mu2.setUsex("男");
        List<MyUser> list = Arrays.asList(mu1, mu2);
        userService.updateBatchById(list);
        return list;
    }

    @GetMapping("/testServicePage")
    public List<MyUser> testServicePage() {
        //1为当前页，5为页面大小
        IPage<MyUser> iPage = new Page<>(1, 5);
        //条件构造器
        QueryWrapper<MyUser> wrapper = new QueryWrapper<>();
        wrapper.like("uname", "陈");
        wrapper.eq("usex", "男");
        IPage<MyUser> page = userService.page(iPage, wrapper);

//        IPage<MyUser> page = userService.page(iPage);
//        System.out.println(page.getPages());
        //返回当前页的记录
        return page.getRecords();
    }

    @GetMapping("/testServiceQuery")
    public List<MyUser> testServiceQuery() {
//        QueryWrapper<MyUser> queryWrapper = new QueryWrapper<MyUser>();
//        queryWrapper.like("uname", "陈");
//        return userService.list(queryWrapper);
        //链式查询
        return userService
                .query()
                .like("uname", "陈")
                .eq("usex", "男")
                .list();
    }
}
