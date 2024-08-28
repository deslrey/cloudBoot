package org.deslre.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * ClassName: UserInfo
 * Description: 用户CRUD
 * Author: Deslrey
 * Date: 2024-07-28 18:20
 * Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("user_info")
public class UserInfo {

    /**
     * 用户ID
     */
    @TableId("user_id")
    private String userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户邮箱
     */
    private String email;


    /**
     * qq头像
     */
    private String avatar;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 注册时间
     */
    private Date joinTime;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 用户状态(0禁用)
     */
    private Integer status;

    /**
     * 使用空间
     */
    private Long useSpace;

    /**
     * 总空间
     */
    private Long totalSpace;

}
