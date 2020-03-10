package org.shisen.web.beanCopier;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/3/10 11:16
 * </pre>
 */
@Data
public class Account {
    private int id;
    private Date createTime;
    private BigDecimal balance;
}
