package org.example.ie.common.http;

import lombok.Data;

import java.util.Map;

/**
 * 定义了一个名为 ResponseResult 的 Java 类，它是一个泛型类。泛型类允许你在定义类时指定一个或多个类型参数，这样在创建类的实例时可以指定具体的类型，
 * 从而提高代码的灵活性和可重用性。 具体来说，这段代码的含义如下：
 * 泛型参数：<T> 表示这是一个泛型类，T 是类型参数，可以在创建类的实例时指定具体的类型。
 * 这个类通常用于封装服务器返回的数据，包括状态码、消息和具体的数据。使用泛型可以使得这个类能够处理不同类型的数据，而不需要为每种数据类型定义不同的类。
 */
@Data
public class ResponseResult<T> {

	//	存储操作结果的数据
	private T result;
	/**
	 * 表示操作的状态码
	 * C200:正常
	 * C401:认证不通过
	 * C404:未找到资源
	 * C405:权限不足
	 * C500:服务异常
	 */
	private StatusCode statusCode;
	/**
	 * 消息的唯一标识符
	 * msgId类型
	 * A001: 成功
	 * A002: 失败
	 * A003:不存在
	 */
	private String msgId;
	//	存储消息参数的键值对映射
	private Map<String, String> msgParams;
	//	表示结果类型,主要用于反馈操作结果及状态信息
	private ResultType resultType;


	public ResponseResult() {
	}


	public ResponseResult(T result, String msgId, ResultType rt, StatusCode statusCode, Map<String,String> msgParams) {
		super();
		this.result = result;
		this.msgId = msgId;
		this.resultType = rt;
		this.statusCode = statusCode;
		this.msgParams = msgParams;
	}
	//重载ResponseResult方法

	public static <T> ResponseResult<T> getSuccessResult(T res) {
		return new ResponseResult<T>(res, null, ResultType.SUCCESS, StatusCode.C200, null);
	}

	public static <T> ResponseResult<T> getSuccessResult(T res, String msgId, Map<String,String> msgParams) {
		return new ResponseResult<T>(res, msgId, ResultType.SUCCESS, StatusCode.C200, msgParams);
	}

	public static <T> ResponseResult<T> getMessageResult(T res, String msgId, StatusCode statusCode) {
		return new ResponseResult<T>(res, msgId, ResultType.MESSAGE, statusCode, null);
	}

	/**
	 * @Description: //TODO
	 * @Author: xujia
	 * @Date: 2024/9/3  22:23
	 * @Param: [res, msgId]
	 * @version: 1.0
	 * @Return: com.ie.common.http.ResponseResult<T>
	 **/
	public static <T> ResponseResult<T> getMessageResult(T res, String msgId) {
		return new ResponseResult<T>(res, msgId, ResultType.MESSAGE, StatusCode.C200, null);
	}

	public static <T> ResponseResult<T> getMessageResult(T res, String msgId, Map<String,String> msgParams) {
		return new ResponseResult<T>(res, msgId, ResultType.MESSAGE, StatusCode.C200, msgParams);
	}

	public static <T> ResponseResult<T> getMessageResult(T res, String msgId, Map<String,String> msgParams, StatusCode statusCode) {
		return new ResponseResult<T>(res, msgId, ResultType.MESSAGE, statusCode, msgParams);
	}

	public static <T> ResponseResult<T> getErrorResult(String msgId) {
		return new ResponseResult<T>(null, msgId, ResultType.ERROR, StatusCode.C500, null);
	}

	public static <T> ResponseResult<T> getNotFoundResult() {
		return new ResponseResult<T>(null, null, ResultType.NOT_FOUND, StatusCode.C500, null);
	}
}
