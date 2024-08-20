package org.deslre.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * ClassName: FieldMetaObjectHandler
 * Description: mybatis-plus 自动填充字段
 * Author: Deslrey
 * Date: 2024-07-28 20:13
 * Version: 1.0
 */
public class FieldMetaObjectHandler implements MetaObjectHandler {
    private final static String CREATE_TIME = "createTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建时间
        strictInsertFill(metaObject, CREATE_TIME, Date.class, new Date());

    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
