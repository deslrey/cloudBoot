package org.deslre.exception;

import lombok.extern.slf4j.Slf4j;
import org.deslre.result.ResultCodeEnum;
import org.deslre.result.Results;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * ClassName: DeslreExceptionHandler
 * Description: 异常处理器
 * Author: Deslrey
 * Date: 2024-07-28 20:16
 * Version: 1.0
 */
@Slf4j
@RestControllerAdvice
public class DeslreExceptionHandler {

    protected static final String STATUS_SUCCESS = "success";

    protected static final String STATUS_ERROR = "error";

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(DeslreException.class)
    public Results<String> handleRenException(DeslreException ex){

        return Results.fail(ex.getCode(), ex.getMsg());
    }

    /**
     * SpringMVC参数绑定，Validator校验不正确
     */
    @ExceptionHandler(BindException.class)
    public Results<String> bindException(BindException ex) {
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null;
        return Results.fail(fieldError.getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public Results<String> handleException(Exception e){
        log.error(e.getMessage(), e);
        Results<String> ajaxResponse = new Results<>();
        //404
        if (e instanceof NoHandlerFoundException) {
            ajaxResponse.setCode(ResultCodeEnum.CODE_404.getCode());
            ajaxResponse.setMessage(ResultCodeEnum.CODE_404.getMessage());
            ajaxResponse.setData(STATUS_ERROR);
        } else if (e instanceof DeslreException deslre) {
            //业务错误
            ajaxResponse.setCode(deslre.getCode() == null ? ResultCodeEnum.CODE_600.getCode() : deslre.getCode());
            ajaxResponse.setMessage(deslre.getMessage());
            ajaxResponse.setData(STATUS_ERROR);
        } else if (e instanceof BindException|| e instanceof MethodArgumentTypeMismatchException) {
            //参数类型错误
            ajaxResponse.setCode(ResultCodeEnum.CODE_600.getCode());
            ajaxResponse.setMessage(ResultCodeEnum.CODE_600.getMessage());
            ajaxResponse.setData(STATUS_ERROR);
        } else if (e instanceof DuplicateKeyException) {
            //主键冲突
            ajaxResponse.setCode(ResultCodeEnum.CODE_601.getCode());
            ajaxResponse.setMessage(ResultCodeEnum.CODE_601.getMessage());
            ajaxResponse.setData(STATUS_ERROR);
        } else {
            ajaxResponse.setCode(ResultCodeEnum.CODE_500.getCode());
            ajaxResponse.setMessage(ResultCodeEnum.CODE_500.getMessage());
            ajaxResponse.setData(STATUS_ERROR);
        }
        return ajaxResponse;
    }
}