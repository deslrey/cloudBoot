package org.deslre.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * ClassName: EmailCode
 * Description: 邮箱验证码
 * Author: Deslrey
 * Date: 2024-07-28 20:11
 * Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("email_code")
public class EmailCode {
    /**
     * 邮箱
     */
    @TableId("email")
    private String email;

    /**
     * 验证码
     */
    private String code;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 0:未使用 1:已使用
     */
    private Integer status;

}
