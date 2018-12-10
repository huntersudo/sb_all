package com.project.seed.aop.log;

import java.lang.annotation.*;

/**
 * @author tujingwei
 * @date 2018/5/2 16:11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuditLog {
    /**
     * the operation target
     */
    AuditLogObject obj();

    /**
     * the operation
     */
    AuditLogOperation op();
}
