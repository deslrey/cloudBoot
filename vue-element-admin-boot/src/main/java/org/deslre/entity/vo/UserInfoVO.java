package org.deslre.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.deslre.utils.DateUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: UserInfoVO
 * Description: 视图层用户相关实体类
 * Author: Deslrey
 * Date: 2024-08-31 21:07
 * Version: 1.0
 */
@Data
public class UserInfoVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    //	@Schema(description = "用户ID")
    private String userId;

    //	@Schema(description = "用户昵称")
    private String nickName;

    //	@Schema(description = "用户邮箱")
    private String email;

    //	@Schema(description = "头像")
    private String avatar;

    //	@Schema(description = "用户密码")
    private String password;

    //	@Schema(description = "注册时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date joinTime;

    //	@Schema(description = "最后登录时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date lastLoginTime;

    //	@Schema(description = "用户状态(0禁用)")
    private Integer status;

    //	@Schema(description = "使用空间")
    private Long useSpace;

    //	@Schema(description = "总空间")
    private Long totalSpace;


}
