package com.project.seed.aop.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sml
 * @date 2018/5/2 16:14
 */

@AllArgsConstructor
@Getter
public enum AuditLogModule {
    /**
     * 日志按照各种模块来划分
     */
    SYSTEM(0, ModuleConstant.SYSTEM_MODULE, "系统管理"),
    USER(1, ModuleConstant.USER_MODULE, "用户管理"),
    MFT_DATA(2, ModuleConstant.MFT_MODULE, "MFT数据");

    private int code;
    private String nameEn;
    private String nameCh;

}


