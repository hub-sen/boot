package org.shisen.web.models.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/3/9 17:21
 * </pre>
 */
@Data
public class SalesVo {
    @TableId
    private Integer salesId;
    private Integer dishesId;
    private String dishesName;
    private BigDecimal dishesPrice;
    private Integer append;
    private Date salesTime;
}
