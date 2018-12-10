package com.project.seed.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserDto implements Serializable {

    private Integer id;

    private String username;

    private String password;


    private String nickName;

    private Integer sex;


    private Date registerDate;


}