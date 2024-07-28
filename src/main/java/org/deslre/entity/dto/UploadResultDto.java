package org.deslre.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: UploadResultDto
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-07-28 19:56
 * Version: 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadResultDto implements Serializable {

    private String fileId;

    private String status;
}