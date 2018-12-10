package com.project.seed.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tbl_audit_log")
public class TblAuditLog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String operator;

    @Column(name = "login_ip")
    private String loginIp;

    @Column(name = "api_module")
    private String apiModule;

    private String operation;

    private String params;

    /**
     * 时间(毫秒级别)
     */
    @Column(name = "operation_time")
    private Date operationTime;

    @Column(name = "log_type")
    private Integer logType;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * @return login_ip
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * @param loginIp
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * @return api_module
     */
    public String getApiModule() {
        return apiModule;
    }

    /**
     * @param apiModule
     */
    public void setApiModule(String apiModule) {
        this.apiModule = apiModule;
    }

    /**
     * @return operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * @return params
     */
    public String getParams() {
        return params;
    }

    /**
     * @param params
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * 获取时间(毫秒级别)
     *
     * @return operation_time - 时间(毫秒级别)
     */
    public Date getOperationTime() {
        return operationTime;
    }

    /**
     * 设置时间(毫秒级别)
     *
     * @param operationTime 时间(毫秒级别)
     */
    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    /**
     * @return log_type
     */
    public Integer getLogType() {
        return logType;
    }

    /**
     * @param logType
     */
    public void setLogType(Integer logType) {
        this.logType = logType;
    }
}