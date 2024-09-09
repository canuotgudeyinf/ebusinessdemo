package org.example.ie.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Configuration：这个注解表明该类是一个配置类，用于替代传统的XML配置。它是一个Bean定义的元数据，通过@，可以定义和管理Bean。
 * @PropertySource：该注解用于指定一个属性源文件，这里指定的是类路径下的conf/conf.properties文件。这个文件中定义的属性可以在整个应用程序中使用。
 * 这个类的主要功能是加载并配置一个名为conf.properties的属性文件，使得在应用程序中可以方便地访问和使用这个文件中定义的属性。
 */
@Configuration
@PropertySource(value={"classpath:conf/conf.properties"})
public class PropertyConfig {

}
