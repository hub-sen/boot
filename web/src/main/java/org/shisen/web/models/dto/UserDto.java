package org.shisen.web.models.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.shisen.web.models.BaseModel;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * <pre>
 * Description  用户传输实体
 * </pre>
 *
 * @author shishi
 */
@Accessors(chain = true)
@Getter
@Setter
@ApiModel("用户实体 - 传输")
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"userId"})})
public class UserDto extends BaseModel implements BaseDto {
	@Id
	@ApiModelProperty("用户id")
	private Long userId;
	@ApiModelProperty("用户姓名")
	private String userName;
	@ApiModelProperty("年龄")
	private Integer age;
	@ApiModelProperty("密码")
	private String password;
}
