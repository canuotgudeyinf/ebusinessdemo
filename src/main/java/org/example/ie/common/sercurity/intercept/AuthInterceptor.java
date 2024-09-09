package org.example.ie.common.sercurity.intercept;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.common.http.StatusCode;
import org.example.ie.common.sercurity.anno.AuthIgrone;
import org.example.ie.common.sercurity.utils.HttpJwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.annotation.Annotation;

@Component
@Slf4j
@SuppressWarnings("all")

/**
 * @classname: AuthInterceptor
 * @Author: xujia
 * @Description: 实现HandlerInterceptor接口。HandlerInterceptor是Spring框架中的一个接口，用于拦截HTTP请求和响应，通常用于实现权限验证、日志记录等功能
 * @Date: 2024/9/8  15:17
 * @Param:
 * @version: 1.0
 **/
public class AuthInterceptor implements HandlerInterceptor {
	@Autowired
	private HttpJwtTokenUtil jwtHttpUtil;

	/**
	 * preHandle主要用于处理HTTP请求前的预处理工作：
	 * 记录请求开始的日志信息；
	 * 检查处理器是否为HandlerMethod类型，若不是则直接返回true；
	 * 获取方法上的AuthIgrone注解，如果存在该注解，则直接放行；
	 * 使用jwtHttpUtil.validate方法验证请求的有效性，如果验证失败，则返回错误信息并阻止请求继续执行；否则放行请求。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info(request.getRequestURI() + " is start");
		//https://blog.csdn.net/tgy003/article/details/136520919
		//https://blog.csdn.net/alises1314/article/details/140528573
		//https://blog.csdn.net/alises1314/article/details/140528573

		/**
		 HandlerMethod 是 Spring 框架中的一个类，用于封装处理 HTTP 请求的方法。它通常与 Spring MVC 一起使用，用于处理控制器中的方法调用。HandlerMethod
		 提供了关于控制器方法的信息，包括方法名、参数、返回类型等。
		 实现原理
		 HandlerMethod 通过反射机制获取控制器方法的信息。它包含以下主要部分：
		 Controller 对象：表示处理请求的控制器实例。
		 Method 对象：表示控制器中的具体方法。
		 Bean 属性：表示控制器在 Spring 容器中的 Bean 名称。
		 用途
		 HandlerMethod 在 Spring MVC 中主要用于以下用途：
		 请求映射：通过 HandlerMethod 可以获取到处理请求的方法，从而确定请求应该由哪个方法来处理。
		 参数解析：HandlerMethod 可以解析方法参数，包括请求参数、请求头、请求体等。
		 返回值处理：HandlerMethod 可以处理方法的返回值，并将其转换为合适的响应格式（如 JSON、XML 等）。
		 拦截器支持：HandlerMethod 支持在方法执行前后添加拦截器，实现自定义的预处理和后处理逻辑。
		 注意事项
		 性能考虑：由于 HandlerMethod 使用反射机制，频繁创建 HandlerMethod 实例可能会影响性能。因此，通常在应用启动时创建并缓存 HandlerMethod 实例。
		 安全性：在使用 HandlerMethod 时，需要注意确保控制器方法的安全性，避免潜在的安全漏洞，如 SQL 注入、XSS 攻击等。
		 异常处理：在控制器方法中，应该合理处理可能出现的异常，避免未处理的异常导致应用崩溃。
		 */
		// 如果handler不是HandlerMethod实例，直接返回
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Annotation authIgrone =  handlerMethod.getMethodAnnotation(AuthIgrone.class);
		/**
		 * 从一个处理器方法（HandlerMethod）中获取一个特定的注解（Annotation），具体来说，是获取名为AuthIgrone的注解。下面是对这段代码的详细解释：
		 * HandlerMethod:
		 * HandlerMethod是Spring框架中的一个类，用于封装控制器方法的信息。它包含了方法本身、所属的控制器类、方法参数等信息。
		 * 通过HandlerMethod对象，可以获取到控制器方法的各种元数据，如方法名、参数类型、返回类型等。
		 * 获取处理器方法:
		 * HandlerMethod handlerMethod = (HandlerMethod) handler;
		 * 这行代码将handler对象强制转换为HandlerMethod类型。handler通常是一个处理器对象，可能是通过HandlerInterceptor拦截器获取的。
		 * 获取方法注解:
		 * Annotation authIgrone = handlerMethod.getMethodAnnotation(AuthIgrone.class);
		 * 这行代码尝试从HandlerMethod对象中获取名为AuthIgrone的方法注解。getMethodAnnotation方法会返回一个Annotation对象，如果方法上存在AuthIgrone注解，
		 * 则返回该注解实例；否则返回null。
		 * 用途
		 * 这段代码的用途通常是在Spring MVC框架中，用于在拦截器（Interceptor）或控制器（Controller）中检查某个方法是否被AuthIgrone注解标记。如果方法被标记，
		 * 则可以跳过某些权限验证或授权逻辑。
		 * 注意事项
		 * 注解定义:
		 * AuthIgrone注解需要在代码的其他地方定义。它应该是一个自定义注解，用于标记需要忽略权限验证的方法。
		 * 类型转换:
		 * handler对象必须是一个HandlerMethod实例，否则强制转换会抛出ClassCastException异常。通常，handler对象是通过拦截器传递的，确保传递的是正确的类型。
		 * 空指针异常:
		 * 如果方法上没有AuthIgrone注解，getMethodAnnotation方法会返回null。在使用authIgrone对象之前，应该进行null检查，以避免空指针异常。
		 */
		//无需签名,直接放行
        if(authIgrone != null) {
        	return true;
        }
        
        boolean res = jwtHttpUtil.validate(request);
		if(!res) {
			ResponseResult<Object> rrs = ResponseResult.getMessageResult(null,"E001", StatusCode.C405);
			response.getWriter().write(JSONUtil.toJsonStr(rrs));
			return false;
		} else {
			return true;
		}
	}
}
/**
 * 这段Java代码的主要功能是验证一个HTTP请求是否通过JWT（JSON Web Token）的验证，并根据验证结果返回相应的响应。下面是对代码的详细解释：
 * JWT验证：
 * boolean res = jwtHttpUtil.validate(request);
 * 这行代码调用了jwtHttpUtil对象的validate方法，传入request对象，用于验证请求是否包含有效的JWT。validate方法返回一个布尔值res，表示验证结果
 * （true表示验证通过，false表示验证失败）。
 * 验证失败的处理：
 * if(!res) {
 *     ResponseResult<Object> rrs = ResponseResult.getMessageResult(null,"E001", StatusCode.C405);
 *     response.getWriter().write(JSONUtil.toJsonStr(rrs));
 *     return false;
 * }
 * 如果验证失败（res为false），则执行以下操作：
 * 创建一个ResponseResult对象rrs，该对象包含错误信息。getMessageResult方法接受三个参数：null（表示没有数据返回）、错误代码"E001"和状态码StatusCode.C405
 * （表示方法不允许，通常用于表示客户端请求的方法不允许）。
 * 使用response.getWriter().write(JSONUtil.toJsonStr(rrs))将rrs对象转换为JSON字符串，并通过response对象返回给客户端。
 * 返回false，表示验证未通过。
 * 验证通过的处理：
 * return true;
 * 如果验证通过（res为true），则直接返回true，表示验证通过。
 * 用途： 这段代码通常用于保护需要认证的API端点，确保只有持有有效JWT的请求才能访问这些端点。如果请求没有通过JWT验证，服务器会返回一个错误响应，告知客户端请求被拒绝。
 * 注意事项：
 * jwtHttpUtil对象需要正确实现JWT验证逻辑，确保其validate方法能够正确验证请求中的JWT。
 * ResponseResult和StatusCode类需要定义好，以便生成合适的响应结果。
 * JSONUtil类用于将ResponseResult对象转换为JSON字符串，确保响应能够被客户端正确解析。
 * 代码中假设request和response对象已经正确初始化，并且response.getWriter()方法可用。
 */
