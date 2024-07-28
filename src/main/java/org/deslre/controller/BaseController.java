package org.deslre.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.deslre.config.AppConfig;
import org.deslre.convert.FileInfoConvert;
import org.deslre.entity.dto.DownloadFileDto;
import org.deslre.entity.dto.SessionWebUserDto;
import org.deslre.entity.enums.FileCategoryEnums;
import org.deslre.entity.enums.FileFolderTypeEnums;
import org.deslre.entity.po.FileInfo;
import org.deslre.entity.vo.FileInfoVO;
import org.deslre.exception.DeslreException;
import org.deslre.query.FileInfoQuery;
import org.deslre.result.Constants;
import org.deslre.result.ResultCodeEnum;
import org.deslre.result.Results;
import org.deslre.service.FileInfoService;
import org.deslre.utils.RedisComponent;
import org.deslre.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * ClassName: BaseController
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-07-28 19:10
 * Version: 1.0
 */
public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/json;charset=UTF-8";

    @Resource
    protected AppConfig appConfig;

    @Resource
    protected FileInfoService fileInfoService;

    @Resource
    protected RedisComponent redisComponent;

    /**
     * 输出文件

     */
    protected void readFile(HttpServletResponse response, String filePath) {
        if (!StringUtil.pathIsOk(filePath)) {
            return;
        }
        OutputStream out = null;
        FileInputStream in = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return;
            }
            in = new FileInputStream(file);
            byte[] byteData = new byte[1024];
            out = response.getOutputStream();
            int len = 0;
            while ((len = in.read(byteData)) != -1) {
                out.write(byteData, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            log.error("读取文件异常", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.error("IO异常", e);
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("IO异常", e);
                }
            }
        }
    }

    /**
     * 解决输出默认头像失败问题

     */
    protected void printNoDefaultImage(HttpServletResponse response) {
        response.setHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE);
        response.setStatus(HttpStatus.OK.value());
        try (PrintWriter writer = response.getWriter()) {
            writer.print("请在头像目录下放置默认头像default_avatar.jpg");
        } catch (Exception e) {
            log.error("输出无默认图失败", e);
        }
    }

    protected SessionWebUserDto getUserInfoFromSession(HttpSession session) {
        SessionWebUserDto sessionWebUserDto = (SessionWebUserDto) session.getAttribute(Constants.SESSION_KEY);
        return sessionWebUserDto;
    }


    protected void getImage(HttpServletResponse response, String imageFolder, String imageName) {
        if (!StringUtil.pathIsOk(imageFolder) || !StringUtil.pathIsOk(imageName)) {
            return;
        }

        String imageSuffix = StringUtil.getFileSuffix(imageName);
        String filePath = appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE + imageFolder + "/" + imageName;
        imageSuffix = imageSuffix.replace(".", "");
        String contentType = "image/" + imageSuffix;
        response.setContentType(contentType);
        response.setHeader("Cache-Control", "max-age=2592000");
        readFile(response, filePath);
    }

    protected void getFile(HttpServletResponse response, String fileId, String userId) {
        QueryWrapper<FileInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        String filePath = null;

        if (fileId.endsWith(".ts")) {
            String[] tsArray = fileId.split("_");
            String realFileId = tsArray[0];
            wrapper.eq("file_id", realFileId);
            FileInfo fileInfo = fileInfoService.getOne(wrapper);
            if (null == fileInfo) {
                return;
            }
            String fileName = fileInfo.getFilePath();
            fileName = StringUtil.getFileNameNoSuffix(fileName) + "/" + fileId;
            filePath = appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE + fileName;
        } else {
            wrapper.eq("file_id", fileId);
            FileInfo fileInfo = fileInfoService.getOne(wrapper);
            if (null == fileInfo) {
                return;
            }
            if (FileCategoryEnums.VIDEO.getCategory().equals(fileInfo.getFileCategory())) {
                String fileNameNoSuffix = StringUtil.getFileNameNoSuffix(fileInfo.getFilePath());
                filePath = appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE + fileNameNoSuffix + "/" + Constants.M3U8_NAME;
            } else {
                filePath = appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE + fileInfo.getFilePath();
            }

            File file = new File(filePath);
            if (!file.exists()) {
                return;
            }
        }
        readFile(response, filePath);
    }

    protected Results<List<FileInfoVO>> getFolderInfo(String path, String userId) {
        String[] pathArray = path.split("/");
        String orderBy = "field(file_id,\"" + StringUtil.join(pathArray, "\",\"") + "\")";
        FileInfoQuery query = new FileInfoQuery();
        query.setUserId(userId);
        query.setFolderType(FileFolderTypeEnums.FOLDER.getType());
        query.setFileIdArray(pathArray);
        query.setOrderBy(orderBy);
        List<FileInfoVO> list = FileInfoConvert.INSTANCE.convertList(fileInfoService.list(query));

        return Results.ok(list);
    }

    protected Results<String> createDownloadUrl(String fileId, String userId) {
        QueryWrapper<FileInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("file_id", fileId);
        FileInfo fileInfo = fileInfoService.getOne(wrapper);
        if (fileInfo == null) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }
        // 不能下载文件夹
        if (FileFolderTypeEnums.FOLDER.getType().equals(fileInfo.getFolderType())) {
            throw new DeslreException(ResultCodeEnum.CODE_600);
        }
        // fileId正常，得到50位随机code
        String code = StringUtil.getRandomString(Constants.LENGTH_50);
        DownloadFileDto downloadFileDto = new DownloadFileDto();
        downloadFileDto.setDownloadCode(code);
        downloadFileDto.setFilePath(fileInfo.getFilePath());
        downloadFileDto.setFileName(fileInfo.getFileName());

        redisComponent.saveDownloadCode(code, downloadFileDto);

        return Results.ok(code);
    }

    protected void download(HttpServletRequest request, HttpServletResponse response, String code) throws Exception {
        DownloadFileDto downloadFileDto = redisComponent.getDownloadCode(code);
        if (downloadFileDto == null) {
            return;
        }
        String filePath = appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE
                + "/" + downloadFileDto.getFilePath();
        String fileName = downloadFileDto.getFileName();
        response.setContentType("application/x-download; charset=UTF-8");
        if (request.getHeader("User-Agent").toLowerCase().indexOf("msie") > 0) {//IE浏览器
            fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        } else {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
        }
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        readFile(response, filePath);
    }

}
