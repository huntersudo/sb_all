package com.project.seed.aop.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sml
 * @date 2018/5/2 16:15
 */
@AllArgsConstructor
@Getter
public enum AuditLogOperation {

    /**
     * 审计方法上的一些操作
     */
    CREATE("新增"),
    QUERY("查看"),
    UPDATE("更新"),
    DELETE("删除"),
    DISABLE("停用"),
    ENABLE("启用"),
    STATISTICS("统计"),
    DETAIL("查看详情"),
    LOGIN("登录"),
    LOGOUT("登出"),
    IMPORT("导入"),
    EXPORT("导出");


    private String operation;

}
