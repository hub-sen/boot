package com.shisen.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/3/11 12:25
 * </pre>
 */
@Data
public class User extends Model<User> {
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 电话
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 密码的盐值
     */
    private String salt;
}
