package org.shisen.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.shisen.web.entity.Sales;
import org.shisen.web.mapper.SalesMapper;
import org.shisen.web.service.SalesService;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/3/9 17:51
 * </pre>
 */
@Service
@Slf4j
public class SalesServiceImpl extends ServiceImpl<SalesMapper, Sales> implements SalesService {
}
