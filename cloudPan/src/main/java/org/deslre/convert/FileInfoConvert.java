package org.deslre.convert;


import org.deslre.entity.po.FileInfo;
import org.deslre.entity.vo.FileInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * ClassName: FileInfoConvert
 * Description: 文件信息表
 * Author: Deslrey
 * Date: 2024-07-28 20:09
 * Version: 1.0
 */
@Mapper(componentModel = "spring")
public interface FileInfoConvert {
    FileInfoConvert INSTANCE = Mappers.getMapper(FileInfoConvert.class);

    FileInfo convert(FileInfoVO vo);

    FileInfoVO convert(FileInfo entity);

    List<FileInfoVO> convertList(List<FileInfo> list);

}
