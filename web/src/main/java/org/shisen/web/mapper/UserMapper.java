package org.shisen.web.mapper;

import org.apache.ibatis.annotations.Param;
import org.shisen.web.models.dto.UserDto;
import tk.mybatis.mapper.common.Mapper;

/**
 * 用户相关 mapper
 * @author shishi
 */
public interface UserMapper extends Mapper<UserDto> {
	/**
	 * 获取用户
	 * @param userName  用户名
	 * @return  用户信息
	 */
	UserDto mySelect(@Param("userName") String userName);
}
