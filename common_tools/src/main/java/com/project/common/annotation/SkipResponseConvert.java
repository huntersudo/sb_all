package com.project.common.annotation;

import java.lang.annotation.*;

/**
 * @author Wang Zheng
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SkipResponseConvert {

}
