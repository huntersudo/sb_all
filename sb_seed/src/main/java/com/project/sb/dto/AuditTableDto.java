package com.project.sb.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="对象名",description="描述")
@Data
public class AuditTableDto implements Serializable {

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

    private String zipfile;

    /**
     * 数据时间
     */

    private String dataTime;

    /**
     * dataFile文件名
     */
    private String datafile;

    /**
     * md5值
     */
    private String md5value;

    /**
     * 1-true,0-false
     */
    private String check;

    /**
     * 错误详情，后续扩展用
     */
    private String errordetail;

    /**
     * 记录创建时间
     */

    private Date createtime;

    /**
     *  记录更新时间
     */

    private Date updatetime;

}