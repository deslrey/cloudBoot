package org.deslre.entity.dto;

import lombok.Data;

/**
 * ClassName: DownloadFileDto
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-07-28 19:30
 * Version: 1.0
 */
@Data
public class DownloadFileDto {
    private String downloadCode;
    private String fileName;
    private String filePath;
}