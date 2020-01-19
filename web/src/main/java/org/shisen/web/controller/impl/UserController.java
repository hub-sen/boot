package org.shisen.web.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.shisen.web.controller.UserApi;
import org.shisen.web.models.dto.UserDto;
import org.shisen.web.models.vo.UserVo;
import org.shisen.web.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author shishi
 */
@RestController
@Slf4j
public class UserController implements UserApi {

	private final UserService userService;

	public UserController(UserService userService) {this.userService = userService;}

	@Override
	public ResponseEntity<UserVo> getUser(String userName) {
		return baseResponse(() -> userService.getUser(userName));
	}

	@Override
	public ResponseEntity<Boolean> addUser(@RequestBody @Valid UserDto userDto) {
		return baseResponse(() -> userService.addUser(userDto));
	}

	@Override
	public ResponseEntity<Boolean> delUser(@Min(value = 6,message = "长度不能小于 6") @Valid String userName) {
		return baseResponse(() -> userService.delUser(userName));
	}


}
