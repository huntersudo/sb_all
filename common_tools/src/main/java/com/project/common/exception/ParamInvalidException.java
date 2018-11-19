package com.project.common.exception;

import com.project.common.constant.StatusCode;

/**
 * @author Wang Zheng
 */
public class ParamInvalidException extends RootException {

    public ParamInvalidException(final String s) {
        super(s, StatusCode.SC_400.val());
    }

}
