//package com.project.seed.dto.impl;
//
//import com.project.seed.dto.UserDto;
//import com.project.seed.model.User;
//import com.project.common.dto.converter.AbstractDtoConverter;
//import org.modelmapper.PropertyMap;
//import org.springframework.stereotype.Component;
//
//
///**
// * 实际底层调用的是modelMapper
//
// */
//@Component
//public class User2UserDtoConverter extends AbstractDtoConverter<User, UserDto> {
//
//    /**
//     * 在此
//     * 添加一些名字对不上的映射
//     * 或者说默认有问题的映射
//     */
//    public User2UserDtoConverter() {
//        this.dtoMap = new PropertyMap<User, UserDto>() {
//            @Override
//            protected void configure() {
//                map(source.getId(), destination.getId());
//                map(source.getNickName(), destination.getNickName());
//            }
//        };
//        this.modelMapper.addMappings(this.dtoMap);
//    }
//
//    @Override
//    public boolean support(Object object, Class[] destinationClass) {
//        boolean res = object instanceof User;
//        if (destinationClass.length > 0) {
//            res = res && destinationClass[0].equals(UserDto.class);
//        }
//        return res;
//    }
//
//    @Override
//    public UserDto convert(User user) {
//        final UserDto userDto = modelMapper.map(user, UserDto.class);
//
//        return userDto;
//    }
//}