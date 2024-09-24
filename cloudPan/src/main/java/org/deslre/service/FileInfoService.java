package org.deslre.service;

import org.deslre.entity.dto.SessionWebUserDto;
import org.deslre.entity.dto.UploadResultDto;
import org.deslre.entity.po.FileInfo;
import org.deslre.entity.vo.FileInfoVO;
import org.deslre.page.PageResult;
import org.deslre.query.FileInfoQuery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName: FileInfoService
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-07-28 19:50
 * Version: 1.0
 */
public interface FileInfoService  extends BaseService<FileInfo>{

    PageResult<FileInfoVO> page(FileInfoQuery query);

    void save(FileInfoVO vo);

    void update(FileInfoVO vo);

    void delete(List<Long> idList);

    UploadResultDto uploadFile(SessionWebUserDto sessionWebUserDto, String fileId, MultipartFile file, String fileName, String filePid, String fileMd5, Integer chunkIndex, Integer chunks);

    void removeFile2RecycleBatch(String userId, String fileIds);

    FileInfo newFolder(String filePid, String userId, String fileName);

    List<FileInfo> list(FileInfoQuery query);

    FileInfoVO rename(String fileId, String userId, String fileName);

    void changeFileFolder(String fileIds, String filePid, String userId);

    void recoverFileBatch(String userId, String fileIds);

    void delFileBatch(String userId, String fileIds, boolean b);

    void saveShare(String shareRootFilePid, String shareFileIds, String myFolderId, String shareUserId, String currentUserId);
}
