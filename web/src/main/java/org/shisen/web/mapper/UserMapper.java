package org.shisen.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.shisen.web.entity.User;

/**
 * 用户相关 mapper
 * @author shishi
 */
public interface UserMapper extends BaseMapper<User> {
	/**
	 * 获取用户
	 * @param userName  用户名
	 * @return  用户信息
	 */
	User mySelect(@Param("userName") String userName);
}
