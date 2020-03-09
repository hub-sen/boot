package org.shisen.web.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.shisen.web.commn.utils.ClazzConver;
import org.shisen.web.controller.UserApi;
import org.shisen.web.entity.User;
import org.shisen.web.models.vo.UserVo;
import org.shisen.web.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author shishi
 */
@RestController
@Slf4j
public class UserController implements UserApi {

	private final UserService userService;

	public UserController(UserService userService) {this.userService = userService;}

	@Override
	public ResponseEntity<UserVo> getUser(String userName) throws IOException {
		User user = userService.getById(userName);
		UserVo conversion = ClazzConver.conversion(user, UserVo.class);
		return baseResponse(()->conversion);
	}


}
