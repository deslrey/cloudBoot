package org.deslre.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.deslre.result.ResultCodeEnum;

import java.io.Serial;

/**
 * ClassName: DeslreException
 * Description: 自定义异常
 * Author: Deslrey
 * Date: 2024-07-28 20:13
 * Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeslreException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Integer code;
    private final String msg;

    public DeslreException(String msg) {
        super(msg);
        this.code = ResultCodeEnum.CODE_500.getCode();
        this.msg = msg;
    }

    public DeslreException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMessage();
    }

    public DeslreException(String msg, Throwable e) {
        super(msg, e);
        this.code = ResultCodeEnum.CODE_500.getCode();
        this.msg = msg;
    }


}