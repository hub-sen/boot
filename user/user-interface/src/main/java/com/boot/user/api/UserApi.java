package com.boot.user.api;

import com.boot.user.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <pre>
 * Description
 * </pre>
 * @author shishi
 */
public interface UserApi {

	/**
	 * 通过用户名密码查询用户
	 * @param username
	 * @param password
	 * @return
	 */
	@GetMapping("/user/query")
	User findUserByUsernameAndPassword(
			@RequestParam("username")String username,
			@RequestParam("password")String password);
}
