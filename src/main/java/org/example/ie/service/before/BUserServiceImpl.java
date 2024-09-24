package org.example.ie.service.before;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.ie.common.MD5Util;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.common.sercurity.utils.ConfigrarionBean;
import org.example.ie.common.sercurity.utils.JwtTokenUtil;
import org.example.ie.common.sercurity.utils.RedisUtil;
import org.example.ie.entity.BUserEntity;
import org.example.ie.mapper.before.BUserMapper;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BUserServiceImpl extends ServiceImpl<BUserMapper, BUserEntity> implements BUserService{
    private final RedisUtil redisUtil;
    private final JwtTokenUtil jwtTokenUtil;
    private final ConfigrarionBean configrarionBean;

    public BUserServiceImpl(RedisUtil redisUtil, JwtTokenUtil jwtTokenUtil, ConfigrarionBean configrarionBean) {
        this.redisUtil = redisUtil;
        this.jwtTokenUtil = jwtTokenUtil;
        this.configrarionBean = configrarionBean;
    }

    @Override
    public ResponseResult<Map<String, String>> login(BUserEntity bUserEntity) {
        String randcode = (String) redisUtil.get("code");
        if(!randcode.equalsIgnoreCase(bUserEntity.getCode())){
            return ResponseResult.getMessageResult(null,"A000");
        }
        if(query().eq("bemail",bUserEntity.getBemail()).count() == 0) return ResponseResult.getMessageResult(null,"A001");
        Map<String,String> map = new HashMap<>();
        bUserEntity.setBpwd(MD5Util.MD5(bUserEntity.getBpwd()));
        map.put("bemail",bUserEntity.getBemail());
        map.put("bpwd",bUserEntity.getBpwd());
        List<BUserEntity> list = query().allEq(map).list();
        if(!list.isEmpty()) {
            String token = jwtTokenUtil.createToken(bUserEntity.getBemail());
            //buser用户信息保存到redis
            redisUtil.set("login_" + bUserEntity.getBemail(), bUserEntity.getBemail(), configrarionBean.getRedisExpiration());
            Map<String, String> myres = new HashMap<>();
            myres.put("buserauthtoken", token);
            myres.put("bemail", bUserEntity.getBemail());
            myres.put("bid", list.getFirst().getId()+"");
            return ResponseResult.getSuccessResult(myres);
        }
        return ResponseResult.getMessageResult(null,"A000");
    }

    @Override
    public ResponseResult<Map<String, String>> register(BUserEntity bUserEntity) {
        if(query().eq("bemail", bUserEntity.getBemail()).count() == 0){
            bUserEntity.setBpwd(MD5Util.MD5(bUserEntity.getBpwd()));
            if (saveOrUpdate(bUserEntity))
                return ResponseResult.getMessageResult(null,"A002");
            else return ResponseResult.getMessageResult(null,"A003");
        }
        else return ResponseResult.getMessageResult(null,"A001");
    }
}
