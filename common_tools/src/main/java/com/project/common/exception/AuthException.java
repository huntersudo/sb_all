package com.project.common.exception;

import com.project.common.constant.StatusCode;

/**
 * @author Wang Zheng
 */
public class AuthException extends RootException {

    public AuthException(String s) {
        super(s, StatusCode.SC_405.val());
    }

}
