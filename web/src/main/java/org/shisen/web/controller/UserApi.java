package org.shisen.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.shisen.web.models.dto.UserDto;
import org.shisen.web.models.vo.UserVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
	ResponseEntity<UserVo> getUser(@PathVariable("userName") String userName);

	/**
	 * 添加用户
	 *
	 * @param userDto 用户信息
	 * @return true: 添加成功 else 添加失败
	 */
	@ApiOperation("添加用户")
	@PostMapping("/add")
	ResponseEntity<Boolean> addUser(@RequestBody UserDto userDto);

	/**
	 * 根据用户名删除用户
	 *
	 * @param userName 用户名
	 * @return true:删除成功 else 删除失败
	 */
	@ApiOperation("删除用户")
	@DeleteMapping("/delete/{userName}")
	ResponseEntity<Boolean> delUser(@PathVariable("userName") String userName);
}
