package org.shisen.web.models.dto;

import com.boot.common.pojo.PageNation;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/3/9 17:18
 * </pre>
 */
@Data
@ApiModel("菜品分类Dto")
public class DishesClassificationDto extends PageNation {
    private Integer classificationId;
    private String classification;
}
