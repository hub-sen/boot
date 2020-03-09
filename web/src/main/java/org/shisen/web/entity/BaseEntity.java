package org.shisen.web.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * <pre>
 * Description 基础实体类 用于实现*MybatisPlus*自动填充功能
 *  若实体对应的表中不存在以下所有字段，则不要继承此实体，否则会报错！！！
 * @author shishi
 * 2020/2/6 21:44
 * </pre>
 */
@Data
public class BaseEntity {

	/**
	 * 删除标记.
	 */
	@TableField(fill = FieldFill.INSERT)
	@TableLogic
	private Byte deleted;
	/**
	 * 创建人.
	 */
	@TableField(fill = FieldFill.INSERT)
	private Integer createdBy;
	/**
	 * 创建人姓名.
	 */
	@TableField(fill = FieldFill.INSERT)
	private String createdByName;
	/**
	 * 创建时间.
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createdTime;
	/**
	 * 更新人.
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Integer updatedBy;
	/**
	 * 最后更新人姓名.
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String updatedByName;
	/**
	 * 更新时间.
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updatedTime;
	/**
	 * 版本.
	 */
	@TableField(fill = FieldFill.INSERT)
	private Integer version;

}
