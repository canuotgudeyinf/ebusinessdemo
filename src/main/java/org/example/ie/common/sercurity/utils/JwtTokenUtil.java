package org.example.ie.common.sercurity.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @classname: JwtTokenUtil
 * @Author: xujia
 * @Description: hutool工具类token操作方法
 * @Date: 2024/9/8  22:37
 * @Param:
 * @version: 1.0
 **/

@Component//https://blog.csdn.net/qq_35430000/article/details/108910387
public class JwtTokenUtil {
	@Autowired
	private ConfigrarionBean myConfig;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * @Description: 获取当前时间并计算过期时间，基于myConfig.getExpiration()分钟数。
     * 创建一个用于存储JWT载荷的Map对象。
     * 在载荷中存入签发人(iss)、签发时间(iat)、过期时间(exp)、生效时间(nbf)以及用户名。
     * 使用JWTUtil.createToken()方法和密钥(myConfig.getSecret())生成JWT令牌。
     * 返回生成的JWT令牌。
     * 函数返回的token是一个包含用户信息和时效信息的加密字符串，可用于用户身份验证和授权过程。
     * @Author: xujia
     * @Date: 2024/8/29  22:07
     * @Param: [userName]
     * @version: 1.0
     * @Return: java.lang.String
     **/
    public String createToken(String userName) {
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.MINUTE, myConfig.getExpiration());
        Map<String,Object> payload = new HashMap<>();
        // 签发人
        payload.put(JWTPayload.ISSUER, myConfig.getIss());
        //签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        //过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        //生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        //载荷
        payload.put(HttpJwtTokenUtil.KEY_USERNAME, userName);
        String token = JWTUtil.createToken(payload, myConfig.getSecret().getBytes());
        return token;
    }

    /**
     * 验证token签名
     * @return
     */
    public boolean verify(String token) {

        return JWTUtil.verify(token, myConfig.getSecret().getBytes());
    }

    /**
     * 从token获取用户信息
     * @param token
     * @return
     */
    public String getUsername(String token) {
        return JWTUtil.parseToken(token).getPayload(HttpJwtTokenUtil.KEY_USERNAME).toString();
    }

    /**
     * 是否已过期, 未过期，延长时间
     * 
     * @param token
     * @return
     */
    public boolean isExpiration(String token) {
        String userName = getUsername(token);
        String key = "login_" + userName;
        boolean hasEx = redisUtil.hasKey(key);
        if(hasEx) {
            redisUtil.expire(key, myConfig.getRedisExpiration());
            return true;
        } else {
            return false;
        }
    }
}
