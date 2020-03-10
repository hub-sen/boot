package org.shisen.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.shisen.web.entity.DishesClassification;
import org.shisen.web.models.dto.DishesClassificationDto;
import org.shisen.web.models.vo.DishesClassificationVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/3/9 17:26
 * </pre>
 */
@Api(tags = "菜品分类相关")
@RequestMapping("/classification")
public interface DishesClassificationApi {

    /**
     * 添加分类
     *
     * @param classification
     * @return
     */
    @ApiOperation("添加分类")
    @GetMapping("/add")
    ResponseEntity<Boolean> add(DishesClassificationDto classification);

    /**
     * 更新分类名称
     *
     * @param classification
     * @return
     */
    @ApiOperation("更新分类")
    @GetMapping("/update")
    ResponseEntity<Boolean> update(DishesClassificationDto classification) throws Exception;

    /**
     * 分类列表
     *
     * @return
     */
    @ApiOperation("分类列表")
    @GetMapping("/list")
    ResponseEntity<Page<DishesClassificationVo>> list(Page<DishesClassification> page);

}
