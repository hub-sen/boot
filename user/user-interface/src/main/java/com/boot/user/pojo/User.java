package com.boot.user.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * <pre>
 * Description
 * </pre>
 * @author shishi
 */
@ApiModel("用户实体")
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	@ApiModelProperty(value = "用户id",hidden = true)
	private Long id;

	/**
	 * 用户名
	 */
	@ApiModelProperty("用户名")
	@Length(min = 2,max = 12,message = "用户名不符合规范2-12")
	@Column(name = "user_name")
	private String username;

	/**
	 * 电话
	 */
	@ApiModelProperty("电话")
	@Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$")
	private String phone;

	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	@JsonIgnore
	@Length(min = 6,max = 16,message = "密码不符合规范6-16")
	@Column(name = "password")
	private String password;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(hidden = true)
	private Date createdTime;

	/**
	 * 密码的盐值
	 */
	@JsonIgnore
	@ApiModelProperty(hidden = true)
	private String salt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
