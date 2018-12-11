package com.project.seed.service.impl;

import com.project.seed.persistence.MftJournalTableMapper;
import com.project.seed.model.MftJournalTable;
import com.project.seed.service.MftJournalTableService;
import com.project.seed.generator.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author author on 2018/12/11.
 */
@Service
@Transactional
public class MftJournalTableServiceImpl extends AbstractService<MftJournalTable> implements MftJournalTableService {
    @Resource
    private MftJournalTableMapper mftJournalTableMapper;

}
