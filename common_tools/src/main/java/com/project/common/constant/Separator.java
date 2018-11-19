package com.project.common.constant;

import java.io.File;

/**
 * @author Wang Zheng
 */
public enum Separator {
    LINE(System.getProperty("line.separator") != null ? System.getProperty("line.separator") : "\n"),
    FILE(File.separator);

    /**
     * the separator
     */
    private String separator;

    /**
     * The constructor.
     *
     * @param separator the separator
     */
    Separator(String separator) {
        this.separator = separator;
    }

    /**
     * Get the separator.
     *
     * @return the separator
     */
    public String val() {
        return this.separator;
    }
}
