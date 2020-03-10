package org.shisen.web.controller.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.shisen.web.controller.DishesClassificationApi;
import org.shisen.web.entity.DishesClassification;
import org.shisen.web.models.dto.DishesClassificationDto;
import org.shisen.web.models.vo.DishesClassificationVo;
import org.shisen.web.service.DishesClassificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/3/9 17:45
 * </pre>
 */
@RestController
@Slf4j
public class DishesClassificationController implements DishesClassificationApi {

    @Autowired
    private DishesClassificationService dishesClassificationService;

    @Override
    public ResponseEntity<Boolean> add(DishesClassificationDto classification) {

        DishesClassification dishesClassification = new DishesClassification();
        BeanUtils.copyProperties(classification, dishesClassification);

        boolean saved = dishesClassificationService.save(dishesClassification);

        return ResponseEntity.ok(saved);
    }

    @Override
    public ResponseEntity<Boolean> update(DishesClassificationDto classification) throws Exception {
        if (Objects.isNull(classification.getClassificationId())) {
            throw new Exception("分类ID 不能为空");
        }

        DishesClassification dishesClassification = new DishesClassification();
        BeanUtils.copyProperties(classification, dishesClassification);

        boolean updated = dishesClassificationService.updateById(dishesClassification);

        return ResponseEntity.ok(updated);
    }

    @Override
    public ResponseEntity<Page<DishesClassificationVo>> list(Page<DishesClassification> page) {

        Page<DishesClassification> pageList = dishesClassificationService.page(page);

        Page<DishesClassificationVo> classificationVoPage = new Page<>();

        List<DishesClassification> records = pageList.getRecords();

        List<DishesClassificationVo> collect = records.stream().map(x -> {
            DishesClassificationVo dishesClassificationVo = new DishesClassificationVo();
            BeanUtils.copyProperties(x, dishesClassificationVo);
            return dishesClassificationVo;
        }).collect(Collectors.toList());

        classificationVoPage.setRecords(collect);
        BeanUtils.copyProperties(pageList,classificationVoPage,"records");



        return ResponseEntity.ok(classificationVoPage);
    }
}
