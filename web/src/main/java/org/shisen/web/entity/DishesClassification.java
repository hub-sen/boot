package org.shisen.web.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/3/9 17:18
 * </pre>
 */
@Data
public class DishesClassification {
    @TableId
    private Integer classificationId;
    private String classification;
}
