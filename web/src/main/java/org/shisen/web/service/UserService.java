package org.shisen.web.service;

import org.shisen.web.models.dto.UserDto;
import org.shisen.web.models.vo.UserVo;

/**
 * <pre>
 * Description  用户服务相关
 * </pre>
 */
public interface UserService {
	/**
	 * 根据用户名查询用户
	 * @param userName
	 * @return
	 */
	UserVo getUser(String userName);

	/**
	 * 添加用户
	 * @param userDto
	 * @return  true:添加成功 else 添加失败
	 */
	Boolean addUser(UserDto userDto);

	/**
	 * 删除用户
	 * @param userName  用户名
	 * @return  true:删除成功 else 删除失败
	 */
	Boolean delUser(String userName);
}
