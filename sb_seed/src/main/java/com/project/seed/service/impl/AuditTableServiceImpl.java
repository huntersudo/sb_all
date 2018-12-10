package com.project.seed.service.impl;

import com.project.seed.persistence.AuditTableMapper;
import com.project.seed.model.AuditTable;
import com.project.seed.service.AuditTableService;
import com.project.seed.generator.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author author on 2018/12/10.
 */
@Service
@Transactional
public class AuditTableServiceImpl extends AbstractService<AuditTable> implements AuditTableService {
    @Resource
    private AuditTableMapper auditTableMapper;

}
