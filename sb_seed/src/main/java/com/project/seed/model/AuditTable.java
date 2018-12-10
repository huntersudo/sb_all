package com.project.seed.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "audit_table")
public class AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 数据流向
     */
    private String direction;

    /**
     * 服务名
     */
    private String service;

    /**
     * zipFile文件名
     */
    @Column(name = "zipFile")
    private String zipfile;

    /**
     * 数据时间
     */
    @Column(name = "data_time")
    private String dataTime;

    /**
     * dataFile文件名
     */
    @Column(name = "dataFile")
    private String datafile;

    /**
     * md5值
     */
    @Column(name = "md5Value")
    private String md5value;

    /**
     * 1-true,0-false
     */
    private String check;

    /**
     * 错误详情，后续扩展用
     */
    @Column(name = "errorDetail")
    private String errordetail;

    /**
     * 记录创建时间
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     *  记录更新时间
     */
    @Column(name = "updateTime")
    private Date updatetime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取数据流向
     *
     * @return direction - 数据流向
     */
    public String getDirection() {
        return direction;
    }

    /**
     * 设置数据流向
     *
     * @param direction 数据流向
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * 获取服务名
     *
     * @return service - 服务名
     */
    public String getService() {
        return service;
    }

    /**
     * 设置服务名
     *
     * @param service 服务名
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * 获取zipFile文件名
     *
     * @return zipFile - zipFile文件名
     */
    public String getZipfile() {
        return zipfile;
    }

    /**
     * 设置zipFile文件名
     *
     * @param zipfile zipFile文件名
     */
    public void setZipfile(String zipfile) {
        this.zipfile = zipfile;
    }

    /**
     * 获取数据时间
     *
     * @return data_time - 数据时间
     */
    public String getDataTime() {
        return dataTime;
    }

    /**
     * 设置数据时间
     *
     * @param dataTime 数据时间
     */
    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    /**
     * 获取dataFile文件名
     *
     * @return dataFile - dataFile文件名
     */
    public String getDatafile() {
        return datafile;
    }

    /**
     * 设置dataFile文件名
     *
     * @param datafile dataFile文件名
     */
    public void setDatafile(String datafile) {
        this.datafile = datafile;
    }

    /**
     * 获取md5值
     *
     * @return md5Value - md5值
     */
    public String getMd5value() {
        return md5value;
    }

    /**
     * 设置md5值
     *
     * @param md5value md5值
     */
    public void setMd5value(String md5value) {
        this.md5value = md5value;
    }

    /**
     * 获取1-true,0-false
     *
     * @return check - 1-true,0-false
     */
    public String getCheck() {
        return check;
    }

    /**
     * 设置1-true,0-false
     *
     * @param check 1-true,0-false
     */
    public void setCheck(String check) {
        this.check = check;
    }

    /**
     * 获取错误详情，后续扩展用
     *
     * @return errorDetail - 错误详情，后续扩展用
     */
    public String getErrordetail() {
        return errordetail;
    }

    /**
     * 设置错误详情，后续扩展用
     *
     * @param errordetail 错误详情，后续扩展用
     */
    public void setErrordetail(String errordetail) {
        this.errordetail = errordetail;
    }

    /**
     * 获取记录创建时间
     *
     * @return createTime - 记录创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置记录创建时间
     *
     * @param createtime 记录创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取 记录更新时间
     *
     * @return updateTime -  记录更新时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置 记录更新时间
     *
     * @param updatetime  记录更新时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}