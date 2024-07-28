package org.deslre.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.deslre.utils.DateUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: EmailCodeVO
 * Description: 邮箱验证码
 * Author: Deslrey
 * Date: 2024-07-28 20:08
 * Version: 1.0
 */
@Data
//@Schema(description = "邮箱验证码")
public class EmailCodeVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    //	@Schema(description = "邮箱")
    private String email;

    //	@Schema(description = "验证码")
    private String code;

    //	@Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date creatTime;

    //	@Schema(description = "0:未使用 1:已使用")
    private Integer status;


}
