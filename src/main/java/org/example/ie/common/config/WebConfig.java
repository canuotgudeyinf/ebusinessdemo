package org.example.ie.common.config;

import org.example.ie.common.sercurity.intercept.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	private AuthInterceptor authInter;

	/**
	 * 定义了一个Spring MVC的配置类WebConfig，实现了WebMvcConfigurer接口，并重写了addInterceptors方法。它的主要作用是添加一个拦截器
	 * AuthInterceptor到Spring MVC的拦截器链中，以便在处理请求时进行权限验证。
	 * 实现原理
	 * 配置类声明：@Configuration注解表明这是一个配置类，Spring会自动扫描并加载这个类。
	 * 拦截器注入：通过@Autowired注解将AuthInterceptor注入到WebConfig类中。
	 * 拦截器注册：重写了addInterceptors方法，通过InterceptorRegistry的addInterceptor方法将AuthInterceptor添加到拦截器链中，
	 * 并指定拦截的路径模式/api/**。
	 * 用途
	 * 该配置类的主要用途是在处理API请求时，通过AuthInterceptor进行权限验证。例如，检查用户是否已经登录，或者是否有权限访问特定的API端点。
	 * 注意事项
	 * 路径模式：在addPathPatterns方法中指定的路径模式/api/**表示拦截所有以/api/开头的请求。如果需要拦截其他路径，可以修改这个模式。
	 * 拦截器顺序：如果存在多个拦截器，可以通过InterceptorRegistry的addInterceptor方法的order参数来指定拦截器的执行顺序。
	 * public void addInterceptors(InterceptorRegistry registry) {
	 * registry.addInterceptor(authInterceptor).addPathPatterns("/api/**").order(1);
	 * registry.addInterceptor(loggingInterceptor).addPathPatterns("/**").order(2);
	 * 权限验证逻辑：AuthInterceptor需要实现具体的权限验证逻辑，例如检查请求头中的认证信息，或者从数据库中查询用户权限。
	 * 通过这种方式，可以很方便地在Spring MVC应用中实现权限验证功能，提高系统的安全性。
	 */

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInter).addPathPatterns("/api/**");
	}
}
