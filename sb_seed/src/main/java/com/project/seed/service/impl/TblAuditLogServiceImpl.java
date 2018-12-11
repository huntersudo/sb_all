package com.project.seed.service.impl;

import com.project.seed.persistence.TblAuditLogMapper;
import com.project.seed.model.TblAuditLog;
import com.project.seed.service.TblAuditLogService;
import com.project.seed.generator.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author author on 2018/12/11.
 */
@Service
@Transactional
public class TblAuditLogServiceImpl extends AbstractService<TblAuditLog> implements TblAuditLogService {
    @Resource
    private TblAuditLogMapper tblAuditLogMapper;

}
