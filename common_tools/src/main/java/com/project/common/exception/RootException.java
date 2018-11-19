package com.project.common.exception;

/**
 * @author Wang Zheng
 */
public class RootException extends RuntimeException {

    protected String code;

    public RootException(String s, String code) {
        super(s);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
