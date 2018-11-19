package com.project.common.constant;

/**
 * @author Wang Zheng
 */
public enum Symbol {
    BLANK(" "),
    COMMA(","),
    COLON(":"),
    SEMICOLON(";"),
    AT("@"),
    STAR("*"),
    PERCENT("%"),
    UNDERSCORE("_"),
    DASH("-"),
    AND("&"),
    SLASH("/"),
    BACKSLASH("\\"),
    VLINE("|"),
    NEWLINE("\n"),
    LEFT_SQUARE_BRACKETS("["),
    RIGHT_SQUARE_BRACKETS("]"),
    DOT("."),
    SHARP("#"),
    EQUAL("=");

    private String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    public String val() {
        return symbol;
    }
}
