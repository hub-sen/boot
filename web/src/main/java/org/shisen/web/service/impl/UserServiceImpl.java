package org.shisen.web.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.shisen.web.mapper.UserMapper;
import org.shisen.web.models.dto.UserDto;
import org.shisen.web.models.vo.UserVo;
import org.shisen.web.service.UserService;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.shisen.web.commn.utils.ClazzConver.conversion;

/**
 * <pre>
 * Description  用户相关处理
 * </pre>
 *
 * @author shishi
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService  {

	private final UserMapper userMapper;

	public UserServiceImpl(UserMapper userMapper) {this.userMapper = userMapper;}

	@Override
	public UserVo getUser(String userName) {
		UserDto userDto = new UserDto().setUserName(userName);

		userDto = userMapper.selectOne(userDto);

		UserVo userVo;
		try {
			userVo = conversion(userDto, UserVo.class);
		} catch (IOException e) {
			log.warn("conversion {} to {} failure, Start manual conversion", UserDto.class, UserVo.class);
			userVo = UserVo.builder().userName(userDto.getUserName())
					.age(userDto.getAge())
					.build();

			userVo.setCreatedTime(userDto.getCreatedTime())
					.setUpdatedTime(userDto.getUpdatedTime());
			log.info("Manual conversion succeeded");
		}
		return userVo;
	}

	@Override
	public Boolean addUser(UserDto userDto) {
		int insert = userMapper.insertSelective(userDto);
		return insert > 0;
	}

	@Override
	public Boolean delUser(String userName) {
		UserDto userDto = new UserDto().setUserName(userName);
		int delete = userMapper.delete(userDto);
		return delete > 0;
	}
}
