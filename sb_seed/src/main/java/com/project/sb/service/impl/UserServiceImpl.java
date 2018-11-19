package com.project.sb.service.impl;

import com.project.sb.dao.UserMapper;
import com.project.sb.model.User;
import com.project.sb.service.UserService;
import com.project.sb.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/10/25.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;



}
