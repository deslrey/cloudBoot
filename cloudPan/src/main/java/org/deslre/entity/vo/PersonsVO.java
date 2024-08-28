package org.deslre.entity.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * ClassName: PersonsVO
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-08-01 23:20
 * Version: 1.0
 */
@Data
public class PersonsVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer age;

    private String gender;

    private String birthplace;

    private String idCard;

    private String description;

    private Boolean exist;


}