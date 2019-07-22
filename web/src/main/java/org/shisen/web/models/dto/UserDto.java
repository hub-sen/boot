package org.shisen.web.models.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.shisen.web.models.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author shishi
 * @date 2019-07-17 17:03:56
 */

@Accessors(chain = true)
@Getter
@Setter
@Table(name = "user")
public class UserDto extends BaseModel implements BaseDto {

	/**
	 * 用户编号
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	/**
	 * 用户名
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 年龄, 异常为0
	 */
	@Column(name = "age")
	private Integer age;

	/**
	 * 密码
	 */
	@Column(name = "password")
	private String password;

}
