package org.shisen.web.models.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.shisen.web.models.BaseModel;

/**
 * <pre>
 * Description
 * </pre>
 * @author shishi
 */
@Accessors(chain = true)
@Getter
@Setter
@Builder
@ApiModel("用户实体 - 展示")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVo extends BaseModel implements BaseVo {
	@ApiModelProperty("用户Id")
	private Long userId;

	@ApiModelProperty("用户名")
	private String userName;

	@ApiModelProperty("年龄")
	private Integer age;

	@ApiModelProperty("密码")
	@JsonIgnore
	private String password;
}
