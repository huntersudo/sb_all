package com.project.seed.service.impl;

import com.project.seed.persistence.UserMapper;
import com.project.seed.model.User;
import com.project.seed.service.UserService;
import com.project.seed.generator.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author author on 2018/12/10.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
