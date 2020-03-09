package org.shisen.web.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/3/9 15:08
 * </pre>
 */
@Accessors(chain = true)
@Getter
@Setter
public class User{
    /**
     * 用户编号
     */
    @TableId
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 年龄
     */
    private Integer age;

}
