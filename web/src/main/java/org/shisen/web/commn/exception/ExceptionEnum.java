package org.shisen.web.commn.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shishi
 */

@Getter
public enum ExceptionEnum {

    /**
     * 致命异常
     */
    EXCEPTION(500,"EXCEPTION"),

    /**
     * SQL异常
     */
    SQL_EXCEPTION(500,"SQL_EXCEPTION"),

    /**
     * 空指针异常
     */
    NULL_POINT_EXCEPTION(500,"NULL_POINT_EXCEPTION"),

    /**
     * 非法参数异常
     */
    ILLEGAL_ARGUMENT_EXCEPTION(500,"ILLEGAL_ARGUMENT_EXCEPTION"),

    /**
     * 方法参数非法异常
     */
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION(500,"METHOD_ARGUMENT_NOT_VALID_EXCEPTION");

    static Map<Integer, String> map = new HashMap<>();

    static {
        Arrays.stream(ExceptionEnum.values()).forEach(item -> map.put(item.code, item.name));
    }

    private int code;
    private String name;

    ExceptionEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}