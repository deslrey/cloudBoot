package org.deslre.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.deslre.service.BaseService;

/**
 * ClassName: BaseServiceImpl
 * Description: 基础服务类
 * Author: Deslrey
 * Date: 2024-08-31 20:55
 * Version: 1.0
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
}
