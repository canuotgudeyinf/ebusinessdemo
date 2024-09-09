package org.example.ie.common.sercurity.utils;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 *@Description: HttpJwtTokenUtil
 *这是一个Spring注解，用于标识这个类是一个Spring组件，可以被Spring容器自动扫描和管理。Spring会自动将这个类注册为一个Bean，
 *可以在其他地方通过依赖注入（Dependency Injection）来使用它
 *Slf4j:Lombok注解，用于自动生成一个名为log的日志记录器（Logger），方便在类中进行日志记录
 *@Author: xujia
 * @Date: 2024/8/29  22:04
 * @Param:
 * @version: 1.0
 * @Return:
 **/
@Component
@Slf4j
@AllArgsConstructor
public class HttpJwtTokenUtil {
	public static final String KEY_USERNAME = "userName";
	//	@Autowired
	private final ConfigrarionBean jwtConfig;
	//	@Autowired
	private final JwtTokenUtil jwtUtil;
/**
 *@Description: 验证HTTP请求中携带的令牌是否有效
 * 具体逻辑如下：
 * 创建一个空的userDetail映射。
 * 从请求头中获取名为jwtConfig.getTokenHeader()的令牌。
 * 如果请求头中的令牌为空或无效，则返回false。
 * 使用jwtUtil.verify()方法验证令牌的合法性，如果验证失败，则返回false。
 * 最后，使用jwtUtil.isExpiration()方法检查令牌是否已过期，并返回检查结果。
 *@Author: xujia
 * @Date: 2024/8/29  22:30
 * @Param: [request]
 * @version: 1.0
 * @Return: boolean
 **/
	public boolean validate(HttpServletRequest request) {
		Map<String, String> userDetail = new HashMap<>(2);
		final String requestTokenHeader = request.getHeader(jwtConfig.getTokenHeader());
		if(StrUtil.isEmpty(requestTokenHeader)) {
			return false;
		}
		if(!jwtUtil.verify(requestTokenHeader)) {
			return false;
		}
		return jwtUtil.isExpiration(requestTokenHeader);
	}
/**
 * @Description:获取HTTP请求的POST数据体内容
 * 从请求中获取字符编码，若无则默认使用"UTF-8"。
 * 将字节数组转换为字符串并返回。
 * 若字符编码不支持，则记录错误日志并返回null。
 * @Author: xujia
 * @Date: 2024/8/29  22:35
 * @Param: [request]
 * @version: 1.0
 * @Return: java.lang.String
 **/
	public String getRequestPostStr(HttpServletRequest request) {
		byte buffer[] = getRequestPostBytes(request);
		String charEncoding = request.getCharacterEncoding();
		if (charEncoding == null) {
			charEncoding = "UTF-8";
		}
		try {
			return new String(buffer, charEncoding);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
/**
 * @Description:获取HTTP请求的内容长度
 * 若长度小于0，返回null。
 * 创建与内容长度相同大小的字节数组。
 * 通过输入流读取请求体内容，并填充到字节数组中。
 * 捕获并记录读取过程中的IO异常。最终返回字节数组。
 * @Author: xujia
 * @Date: 2024/8/29  22:37
 * @Param: [request]
 * @version: 1.0
 * @Return: byte[]
 **/
	public byte[] getRequestPostBytes(HttpServletRequest request) {
		int contentLength = request.getContentLength();
		if (contentLength < 0) {
			return null;
		}
		byte buffer[] = new byte[contentLength];
		try {
			for (int i = 0; i < contentLength;) {
				int readlen;
				readlen = request.getInputStream().read(buffer, i, contentLength - i);
				if (readlen == -1) {
					break;
				}
				i += readlen;
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return buffer;
	}
}
