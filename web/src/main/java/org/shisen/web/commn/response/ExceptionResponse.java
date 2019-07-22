package org.shisen.web.commn.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author shishi
 */
@Setter
@Getter
@ToString
public class ExceptionResponse {

    private int code;

    private String name;

    private Object exceptionDetail;

    public ExceptionResponse(int code, String name, Object exceptionDetail) {
        this.code = code;
        this.name = name;
        this.exceptionDetail = exceptionDetail;
    }
}