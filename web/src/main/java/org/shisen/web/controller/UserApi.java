package org.shisen.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.shisen.web.models.vo.UserVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * 用户
 *
 * @author shishi
 */
@Api(tags = "用户相关的请求")
@RequestMapping("/user")
public interface UserApi extends BaseController {

	/**
	 * 根据用户名获取用户信息
	 *
	 * @param userName 用户名
	 * @return 用户
	 */
	@ApiOperation("获取用户信息")
	@ApiImplicitParam(name = "userName", value = "用户名", defaultValue = "施森")
	@GetMapping("/query/{userName}")
	ResponseEntity<UserVo> getUser(@PathVariable("userName") String userName) throws IOException;

}
