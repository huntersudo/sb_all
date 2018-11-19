package com.project.sb.aop.valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.*;

/**
 *在web开发时，对于请求参数，一般上都需要进行参数合法性校验的，原先的写法时一个个字段一个个去判断，
 * 这种方式太不通用了，所以java的JSR 303: Bean Validation规范就是解决这个问题的。
 *
 * JSR 303只是个规范，并没有具体的实现，目前通常都是才有hibernate-validator进行统一参数校验。
 *
 * 然后在控制层方法里，加入@Valid即可，这样在访问前，会对请求参数进行检验。
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoReq {
     
    @NotBlank(message="code不能为空")
    String code;
     
    @Length(max=10,message="name长度不能超过10")
    String name;

//    /**
//     * 被注释的元素必须不为null
//     */
//    @NotNull
//    private String department;
//
//    @NotBlank(message="年龄不能为空")
//    @Pattern(regexp="^[0-9]{1,2}$",message="年龄不正确")
//    private String age1;
//
//    @AssertFalse(message = "必须为false")
//    private Boolean isFalse;
//
//    /**
//     * 如果是空，则不校验，如果不为空，则校验
//     */
//    @Pattern(regexp="^[0-9]{4}-[0-9]{2}-[0-9]{2}$",message="出生日期格式不正确")
//    private String birthday;


}