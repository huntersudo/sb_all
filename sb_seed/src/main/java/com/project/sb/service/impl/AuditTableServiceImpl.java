package com.project.sb.service.impl;

import com.project.sb.dao.AuditTableMapper;
import com.project.sb.model.AuditTable;
import com.project.sb.service.AuditTableService;
import com.project.sb.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/10/25.
 */
@Service
@Transactional
public class AuditTableServiceImpl extends AbstractService<AuditTable> implements AuditTableService {
    @Resource
    private AuditTableMapper auditTableMapper;

}
