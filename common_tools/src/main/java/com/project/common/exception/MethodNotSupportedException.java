package com.project.common.exception;

import com.project.common.constant.StatusCode;

/**
 * @author Wang Zheng
 */
public class MethodNotSupportedException extends RootException {

    public MethodNotSupportedException(final String s) {
        super(s, StatusCode.SC_405.val());
    }
}
