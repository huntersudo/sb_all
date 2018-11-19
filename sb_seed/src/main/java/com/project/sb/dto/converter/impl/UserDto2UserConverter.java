package com.project.sb.dto.converter.impl;

import com.project.common.dto.converter.AbstractDtoConverter;
import com.project.sb.dto.UserDto;
import com.project.sb.model.User;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;


/**
 * 实际底层调用的是modelMapper

 */
@Component
public class UserDto2UserConverter extends AbstractDtoConverter<UserDto, User> {

    /**
     * 在此
     * 添加一些名字对不上的映射
     * 或者说默认有问题的映射
     */
    public UserDto2UserConverter() {
        this.dtoMap = new PropertyMap<UserDto, User>() {
            @Override
            protected void configure() {
                map(source.getId(), destination.getId());
                map(source.getNickName(), destination.getNickName());
            }
        };
        this.modelMapper.addMappings(this.dtoMap);
    }

    @Override
    public boolean support(Object object, Class[] destinationClass) {
        boolean res = object instanceof User;
        if (destinationClass.length > 0) {
            res = res && destinationClass[0].equals(UserDto.class);
        }
        return res;
    }

    @Override
    public User convert(UserDto userDto) {
        final User user = modelMapper.map(userDto, User.class);

        return user;
    }
}