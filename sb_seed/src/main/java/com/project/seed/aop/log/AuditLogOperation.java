package com.project.seed.aop.log;

/**
 * @author tujingwei
 * @date 2018/5/2 16:15
 */
public enum AuditLogOperation {
    ADD("新增"),
    IMPORT("导入"),
    EXPORT("导出"),
    TEMPLATE("下载模板文件"),
    ERROR_FILE("下载上传的无效数据"),
    DELETE("删除"),
    UPDATE("更新"),
    UPDATE_PASSWORD("更新密码"),
    LOGIN("登录"),
    LOGOUT("登出"),
    EXTRACT("提取"),
    SEND("发送"),
    REQUEST_RESET("请求重置"),
    RESET("重置"),
    RESET_PASSWORD("重置密码"),
    QUERY("查看"),
    DISABLE("停用"),
    ENABLE("启用"),
    CODE("验证码"),
    STATISTICS("统计"),
    OPERATION("操作"),
    UPDATE_COMMENT("修改备注"),
    ANALYZE("分析"),
    RETRY("重试"),
    DETAIL("查看详情"),
    TASK_RESULT("查看任务结果"),
    IDENTITY_ACCESS("查看实名制资料"),
    TRACE_ACCESS("个人轨迹"),
    TASK_INFO("任务信息"),
    UPLOAD("上传数据"),
    HISTORY("历史操作"),
    DOWNLOAD("下载上传的数据"),
    DOWNLOAD_SEARCH("下载搜索数据"),
    NAME_QUERY("根据名称查询"),
    LAC_CELL_QUERY("根据lac/cell查询"),
    LAT_LNG_QUERY("根据lat/lng查询"),
    MARK_ENABLE("纳入监控"),
    MARK_DISABLE("取消监控"),
    TRACE_QUERY("获取轨迹"),
    TAIL_ENABLE("开始跟踪"),
    TAIL_DISABLE("取消跟踪"),
    SEND_MESSAGE("发送短信"),
    LOCATION_PUSH("位置推送"),
    GET_REAL_TIME_POLICE_CALL("获取实时"),
    GET_HISTORY_POLICE_CALL("获取历史"),
    GET_FAVORITE_POLICE_CALL("获取收藏"),
    FAVORITE_ENABLE("加入收藏"),
    FAVORITE_DISABLE("取消收藏"),
    POLICE_SCHEDULE("警力调度"),
    POLICE_WARN("警力提醒");

    /**
     * the operation
     */
    private String operation;

    /**
     * The constructor.
     *
     * @param operation the operation, lowercase letters
     */
    AuditLogOperation(String operation) {
        this.operation = operation;
    }

    /**
     * Get the operation.
     *
     * @return the operation
     */
    public String val() {
        return this.operation;
    }
}
