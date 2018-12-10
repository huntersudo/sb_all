package com.project.seed.aop.log;

/**
 * @author tujingwei
 * @date 2018/5/7 17:39
 */
public enum AuditLogOperationDetail {
    MESSAGE_LIST("消息列表"),
    MESSAGE_DETAIL("消息详情");
    /**
     * the operation target
     */
    private String object;

    /**
     * The constructor.
     *
     * @param object the operation target, Chinese characters
     */
    AuditLogOperationDetail(String object) {
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
