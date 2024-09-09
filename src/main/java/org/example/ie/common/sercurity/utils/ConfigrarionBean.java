package org.example.ie.common.sercurity.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
//https://blog.csdn.net/qq_35430000/article/details/108910387

/**
 * 定义了一个名为ConfigrarionBean的类，它使用了Spring框架的一些注解和特性。下面是对这段代码的详细解释：
 * 类和注解
 * 用途
 * 这个类的主要用途是从配置文件中读取一些配置项，并将这些配置项注入到类的成员变量中。这些配置项可能用于Token验证、Redis缓存等场景。
 * 注意事项
 * 配置文件位置: 确保Spring Boot项目的配置文件（如application.properties或application.yml）中包含了这些配置项。
 * 类型转换: 使用@Value注解时，如果配置项的值类型与成员变量的类型不匹配，Spring会尝试进行类型转换。如果转换失败，会抛出异常。
 * Lombok依赖: 如果使用@Getter注解，需要确保项目中引入了Lombok库，并且正确配置了编译器支持。
 */
@Component
@Getter
public class ConfigrarionBean {
    //这个注解用于从Spring的配置文件（如application.properties或application.yml）中读取配置项 * 的值。
//${token.header}是配置文件中的一个键，Spring会自动将其对应的值注入到tokenHeader变量中。
    @Value("${token.header}")
    private String tokenHeader;
    //注入token.secret配置项的值
    @Value("${token.secret}")
    private String secret;
    //用于注入token.iss配置项的值。
    @Value("${token.iss}")
    private String iss;
    //用于注入token.expiration配置项的值，并将其转换为int类型
    @Value("${token.expiration}")
    private int expiration;
    //用于注入redis.expiration配置项的值，并将其转换为long类型
    @Value("${redis.expiration}")
    private long redisExpiration;
}
