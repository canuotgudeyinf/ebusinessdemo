package org.example.ie.service.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.common.sercurity.utils.ConfigrarionBean;
import org.example.ie.common.sercurity.utils.JwtTokenUtil;
import org.example.ie.common.sercurity.utils.RedisUtil;
import org.example.ie.entity.AUserEntity;
import org.example.ie.mapper.admin.AUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AUserServiceImpl extends ServiceImpl<AUserMapper, AUserEntity> implements AUserService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ConfigrarionBean config;


    @Override
    public ResponseResult<Map<String, String>> login(AUserEntity aUserEntity) {
        long res = lambdaQuery().eq(AUserEntity::getAname,aUserEntity.getAname()).count();
        if(res == 0){
            return ResponseResult.getMessageResult(null,"A001");
        }
        Map<String, String> mapparam = new HashMap<>();
        mapparam.put("username", aUserEntity.getAname());
        mapparam.put("apwd", aUserEntity.getApwd());
        List<AUserEntity> mu = query().allEq(mapparam).list();
        if(!mu.isEmpty()){
            String token = jwtTokenUtil.createToken(aUserEntity.getAname());

            redisUtil.set("login_" + aUserEntity.getAname(), aUserEntity.getAname(), config.getRedisExpiration());
            Map<String, String> myres = new HashMap<>();
            myres.put("authtoken", token);
            myres.put("aname", aUserEntity.getAname());
            myres.put("aid", mu.getFirst().getId()+"");
            return ResponseResult.getSuccessResult(myres);
        }else{//密码错误
            return ResponseResult.getMessageResult(null, "A002");
        }
    }
}

