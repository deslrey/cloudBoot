package org.deslre.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: UserSpaceDto
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-07-28 19:31
 * Version: 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSpaceDto implements Serializable {

    private Long useSpace;

    private Long totalSpace;
}
