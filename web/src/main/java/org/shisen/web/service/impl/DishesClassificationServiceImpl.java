package org.shisen.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.shisen.web.entity.DishesClassification;
import org.shisen.web.mapper.DishesClassificationMapper;
import org.shisen.web.service.DishesClassificationService;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/3/9 17:48
 * </pre>
 */
@Service
@Slf4j
public class DishesClassificationServiceImpl extends ServiceImpl<DishesClassificationMapper, DishesClassification> implements DishesClassificationService {
}
