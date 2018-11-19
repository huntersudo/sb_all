package com.project.common.exception;

import com.project.common.constant.StatusCode;

/**
 * @author Wang Zheng
 */
public class ServerException extends RootException {
    public ServerException(final String s) {
        super(s, StatusCode.SC_500.val());
    }
}
