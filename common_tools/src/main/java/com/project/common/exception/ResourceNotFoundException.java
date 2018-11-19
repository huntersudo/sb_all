package com.project.common.exception;

import com.project.common.constant.StatusCode;

/**
 * @author Wang Zheng
 */
public class ResourceNotFoundException extends RootException {

    public ResourceNotFoundException(final String s) {
        super(s, StatusCode.SC_404.val());
    }

}
