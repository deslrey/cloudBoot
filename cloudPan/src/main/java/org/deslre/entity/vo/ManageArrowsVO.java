package org.deslre.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2024-09-19
 */
@Data
public class ManageArrowsVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String arrowName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean exist;

    private String createUser;


}
