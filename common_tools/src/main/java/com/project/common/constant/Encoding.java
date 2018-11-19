package com.project.common.constant;

/**
 * Created by Wang Zheng on 2017/01/19.
 */
public enum Encoding {
    UTF8("UTF-8"),
    GBK("GBK"),
    GB2312("GB2312"),
    ISO88591("ISO-8859-1");

    /**
     * the encoding
     */
    private String encoding;

    /**
     * The constructor.
     *
     * @param encoding the encoding
     */
    Encoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * Get the encoding
     *
     * @return the encoding
     */
    public String val() {
        return this.encoding;
    }
}
