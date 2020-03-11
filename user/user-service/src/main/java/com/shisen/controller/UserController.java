package com.shisen.controller;

import com.boot.common.beans.WrapperBeanCopier;
import com.boot.user.pojo.User;
import com.shisen.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author shishi
 */
@Api(tags = "用户相关")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 实现用户数据的校验，主要包括对：手机号、用户名的唯一性校验。
	 *
	 * @param data
	 * @param type
	 * @return
	 */
	@ApiOperation("手机号、用户名、邮箱的唯一性校验。")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "data", value = "用户名,手机号码"),
			@ApiImplicitParam(name = "type", value = "校验类型,1: 用户名, 2: 手机号码", allowableValues = "1,2")
	})
	@GetMapping("/check/{data}/{type}")
	public ResponseEntity<Boolean> checkData(
			@PathVariable(value = "data") String data,
			@PathVariable(value = "type", required = false) Integer type) {
		if (type == null) {
			type = 1;
		}
		Boolean bool = userService.chckData(data, type);
		if (bool == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(bool);
	}

	/**
	 * 发送短信验证码
	 *
	 * @param phone
	 * @return
	 */
	@ApiOperation("获取短信验证码,直接在redis里查看")
	@ApiImplicitParam(name = "phone",value = "用于接收验证码的手机号码(注册手机号码)", required = true)
	@PostMapping("/code")
	public ResponseEntity<Void> sendCode(@RequestParam(value = "phone") String phone) {
		String regx = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
		if (phone.matches(regx)) {
			userService.sendCode(phone);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * 注册
	 *
	 * @param user
	 * @param code
	 * @return
	 */
	@ApiOperation("注册")
	@ApiImplicitParams({
		// @ApiImplicitParam(name = "user",value = "用户信息"),
		@ApiImplicitParam(name = "code",value = "验证码",required = true)
	})
	@PostMapping("/register")
	public ResponseEntity<Void> register(@Valid User user, @RequestParam("code") String code) {
		com.shisen.entity.User user1 = WrapperBeanCopier.convert(user, com.shisen.entity.User.class);
		boolean bool = userService.register(user1, code);
		if (!bool) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * 查询功能，根据参数中的用户名和密码查询指定用户
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	@ApiOperation("查询功能，根据参数中的用户名和密码查询指定用户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username",value = "用户名"),
			@ApiImplicitParam(name = "password",value = "密码")
	})
	@GetMapping("/query")
	public ResponseEntity<User> findUserByUsernameAndPassword(
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		com.shisen.entity.User user = userService.findUserByUsernameAndPassword(username, password);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(WrapperBeanCopier.convert(user, User.class));
	}
}
