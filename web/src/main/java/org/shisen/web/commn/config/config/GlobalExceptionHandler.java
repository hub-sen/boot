package org.shisen.web.commn.config.config;

import lombok.extern.slf4j.Slf4j;
import org.shisen.web.commn.config.exception.ExceptionEnum;
import org.shisen.web.commn.config.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ValidationException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 异常统一处理
 *
 * @author shishi
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 异常统一处理
     */
    @ExceptionHandler()
    @ResponseBody
    protected ExceptionResponse handelUncaughtException(Exception exception) {
        log.error(exception.getMessage(), exception);

        List<String> array = new ArrayList<>();
        Stream.of(exception.getStackTrace()).forEach(stackTraceElement ->
                array.add(stackTraceElement.toString()));

        if (exception instanceof SQLException) {
            return new ExceptionResponse(ExceptionEnum.SQL_EXCEPTION.getCode(), ExceptionEnum.SQL_EXCEPTION.getName(), array);
        }

        if (exception instanceof IllegalArgumentException) {
            return new ExceptionResponse(ExceptionEnum.ILLEGAL_ARGUMENT_EXCEPTION.getCode(), ExceptionEnum.ILLEGAL_ARGUMENT_EXCEPTION.getName(), array);
        }

        if (exception instanceof NullPointerException) {
            return new ExceptionResponse(ExceptionEnum.NULL_POINT_EXCEPTION.getCode(), ExceptionEnum.NULL_POINT_EXCEPTION.getName(), array);
        }
        return new ExceptionResponse(ExceptionEnum.EXCEPTION.getCode(), ExceptionEnum.EXCEPTION.getName(), array);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    protected ResponseEntity<ExceptionResponse> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ExceptionEnum.METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getCode(), ExceptionEnum.METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getName(),
                Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    protected ResponseEntity<ExceptionResponse> bindException(ValidationException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(500, ExceptionEnum.ILLEGAL_ARGUMENT_EXCEPTION.getName(),
                exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}