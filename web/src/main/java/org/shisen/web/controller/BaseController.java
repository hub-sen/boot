package org.shisen.web.controller;

import org.springframework.http.ResponseEntity;

import java.util.function.Supplier;

/**
 * <pre>
 * Description
 * </pre>
 *
 * @author shishi
 */
public interface BaseController {

	/**
	 * 公共的返回方法
	 *
	 * @param supplier 获取结果集
	 * @return 封装后的结果集
	 */
	default <T> ResponseEntity<T> baseResponse(Supplier<T> supplier) {
		return ResponseEntity.ok(supplier.get());
	}
}
