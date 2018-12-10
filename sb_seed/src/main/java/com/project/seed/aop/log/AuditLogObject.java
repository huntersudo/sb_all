package com.project.seed.aop.log;

/**
 * @author tujingwei
 * @date 2018/5/2 16:14
 */
public enum AuditLogObject {
    SYSTEM("系统"),
    USER("账号管理"),
//    PASSWORD("密码"),
    MESSAGE("消息模块"),
    MESSAGE_STATISTICS("消息统计"),
    WORK_ORDER("我的申请"),
    AREA_MANAGE("区域管理"),
    DATA_MAINTENANCE("数据维护"),
    OBJECTIVE_REGISTER("关注对象"),
    PERMISSION("权限管理"),
    SMS_MANAGE("短信配置"),
    CASE_MANAGE("事件标记"),
    OBJECT_MARKER("物件标记"),
    LOCATION_SEARCH("位置查询"),
    PERSON_IDENTITY("实名制查询"),
    PHONE_REGISTRATION("归属地查询"),
    CELL_QUERY("基站查询"),
    SHUTDOWN_SPACE_TIME("关机时空分析"),
    ABNORMAL_SHUTDOWN("异常关机人员"),
    ENTOURAGE("随行人员分析"),
    MULTI_PHONE("多机多号"),
    FAMILY_MEMBER("家庭成员"),
    CIRCLE_ABNORMAL("圈选异常人员"),
    SINGLE_CASE("个案析疑（个案）"),
    MULTI_CASE_ASSOCIATION("多案时空碰撞（串案）"),
    PATH_TRACK_PERSON("已知轨迹查人"),
    PERSON_IN_CASE("由人串案"),
    JUDGEMENT_MODEL("研判模型"),
    TASK_LIST("任务列表总览"),
    PERSON_CONTROL("人员管控"),
    POLICE_CALL("警情调度"),
    EMERGENCY_COMMAND("应急指挥");


    /**
     * the operation target
     */
    private String object;

    /**
     * The constructor.
     *
     * @param object the operation target, Chinese characters
     */
    AuditLogObject(String object) {
        this.object = object;
    }

    /**
     * Get the operation target
     *
     * @return the operation target
     */
    public String val() {
        return this.object;
    }
}
