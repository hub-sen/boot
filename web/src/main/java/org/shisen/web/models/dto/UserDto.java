package org.shisen.web.models.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.shisen.web.models.BaseModel;

/**
 * @author shishi
 * @date 2019-07-17 17:03:56
 */

@Accessors(chain = true)
@Getter
@Setter
public class UserDto extends BaseModel implements BaseDto {

	/**
	 * 用户编号
	 */
	private Long userId;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 年龄, 异常为0
	 */
	private Integer age;

	/**
	 * 密码
	 */
	private String password;

}
