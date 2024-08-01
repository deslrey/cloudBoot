package org.deslre.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.deslre.entity.po.FileInfo;

import java.util.List;

/**
 * ClassName: FileInfoMapper
 * Description: 文件信息表
 * Author: Deslrey
 * Date: 2024-07-28 19:40
 * Version: 1.0
 */
@Mapper
public interface FileInfoMapper extends BaseDao<FileInfo> {

    Long selectUseSpace(@Param("userId") String userId);

    void updateFileDelFlagBatch(@Param("bean") FileInfo fileInfo,
                                @Param("userId") String userId,
                                @Param("filePidList") List<String> filePidList,
                                @Param("fileIdList") List<String> fileIdList,
                                @Param("oldDelFlag") Integer oldDelFlag);

    Integer updateByFileIdAndUserId(@Param("bean") FileInfo t, @Param("fileId") String fileId, @Param("userId") String userId);

    void delFileBatch(@Param("userId") String userId,
                      @Param("filePidList") List<String> filePidList,
                      @Param("fileIdList") List<String> fileIdList,
                      @Param("oldDelFlag") Integer oldDelFlag);
}
