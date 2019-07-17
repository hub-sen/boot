package org.shisen.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <pre>
 * Description  实体类基类
 * </pre>
 */
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseModel {
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private LocalDateTime createdTime;
	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	private LocalDateTime updatedTime;
	/**
	 * 是否删除
	 */
	@ApiModelProperty(value = "是否删除", allowableValues = "0,1")
	@JsonIgnore
	private Boolean deleted;
}
