package org.deslre.utils;

import org.deslre.entity.dto.DownloadFileDto;
import org.deslre.entity.dto.SysSettingDto;
import org.deslre.entity.dto.UserSpaceDto;
import org.deslre.mapper.FileInfoMapper;
import org.deslre.result.Constants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * ClassName: RedisComponent
 * Description: TODO
 * Author: Deslrey
 * Date: 2024-07-28 19:32
 * Version: 1.0
 */
@Component
public class RedisComponent {
    @Resource
    private RedisUtils redisUtils;

    @Resource
    private FileInfoMapper fileInfoMapper;


    public SysSettingDto getSysSettingDto() {
        SysSettingDto sysSettingDto = (SysSettingDto) redisUtils.get(Constants.REDIS_KEY_SYS_SETTING);
        if (sysSettingDto == null) {
            sysSettingDto = new SysSettingDto();
//            redisUtils.set(Constants.REDIS_KEY_SYS_SETTING, sysSettingDto);
            redisUtils.setex(Constants.REDIS_KEY_SYS_SETTING, sysSettingDto, 15L);
        }

        return sysSettingDto;
    }

    public void saveUserUseSpace(String userId, UserSpaceDto userSpaceDto) {
        redisUtils.setex(Constants.REDIS_KEY_USER_SPACE_USE + userId, userSpaceDto, Constants.REDIS_KEY_EXPIRES_DAY);
    }

    public UserSpaceDto getUserUseSpace(String userId) {
        UserSpaceDto userSpaceDto = (UserSpaceDto) redisUtils.get(Constants.REDIS_KEY_USER_SPACE_USE + userId);

        if (userSpaceDto == null) {
            userSpaceDto = new UserSpaceDto();
            // 查询当前用户使用空间
            userSpaceDto.setUseSpace(fileInfoMapper.selectUseSpace(userId));
            userSpaceDto.setTotalSpace(getSysSettingDto().getUserInitUseSpace() * Constants.MB);
            saveUserUseSpace(userId, userSpaceDto);
        }
        return userSpaceDto;
    }

    //获取临时文件大小
    public Long getFileTempSize(String userId, String fileId) {

        return getFileSizeFromRedis(Constants.REDIS_KEY_USER_FILE_TEMP_SIZE + userId + fileId);
    }

    public void saveFileTempSize(String userId, String fileId, Long fileSize) {
        Long currentSize = getFileTempSize(userId, fileId);
        redisUtils.setex(Constants.REDIS_KEY_USER_FILE_TEMP_SIZE + userId + fileId, currentSize + fileSize, Constants.REDIS_KEY_EXPIRES_THREE_HOURS);
    }

    public Long getFileSizeFromRedis(String key) {
        Object sizeObj = redisUtils.get(key);
        if (sizeObj == null) {
            return 0L;
        }
        if (sizeObj instanceof Integer) {
            return ((Integer) sizeObj).longValue();
        } else if (sizeObj instanceof Long) {
            return (Long) sizeObj;
        }
        return 0L;
    }

    public void saveDownloadCode(String code, DownloadFileDto downloadFileDto) {
        redisUtils.setex(Constants.REDIS_KEY_DOWNLOAD + code,
                downloadFileDto, Constants.REDIS_KEY_EXPIRES_FIVE_MIN);
    }

    public DownloadFileDto getDownloadCode(String code) {
        return (DownloadFileDto) redisUtils.get(Constants.REDIS_KEY_DOWNLOAD + code);
    }
}
