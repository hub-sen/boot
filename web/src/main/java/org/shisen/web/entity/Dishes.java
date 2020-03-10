package org.shisen.web.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/3/9 17:19
 * </pre>
 */
@Data
public class Dishes {
    @TableId
    private Integer dishesId;
    private Integer classificationId;
    private String name;
    private BigDecimal price;
    private Integer margin;
}
