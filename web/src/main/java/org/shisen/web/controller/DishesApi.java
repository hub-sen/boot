package org.shisen.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.shisen.web.models.dto.DishesDto;
import org.shisen.web.models.vo.DishesVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/3/9 17:27
 * </pre>
 */
@Api(tags = "菜品相关")
@RequestMapping("/dishes")
public interface DishesApi {
    /**
     * 添加菜品
     *
     * @param dishes
     * @return
     */
    @ApiOperation("添加分类")
    @GetMapping("/add")
    ResponseEntity<Boolean> add(DishesDto dishes);

    /**
     * 更新菜品
     *
     * @param dishes
     * @return
     */
    @ApiOperation("更新菜品")
    @GetMapping("/update")
    ResponseEntity<Boolean> update(DishesDto dishes);

    /**
     * 菜品列表
     *
     * @return
     */
    @ApiOperation("菜品列表")
    @GetMapping("/update")
    ResponseEntity<DishesVo> list();

    /**
     * 菜品详情
     *
     * @param dishes
     * @return
     */
    @ApiOperation("菜品详情")
    @GetMapping("/detail")
    ResponseEntity<DishesVo> detail(DishesDto dishes);


}
