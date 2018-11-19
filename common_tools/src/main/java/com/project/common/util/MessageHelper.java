package com.project.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wang Zheng
 */
public class MessageHelper {
    private static final transient Logger LOG = LoggerFactory.getLogger(MessageHelper.class);

    private MessageHelper() {
    }

    /**
     * Try to resolve the message. Return null if no message was found.
     *
     * @param code the code to lookup up
     * @return the resolved message if the lookup was successful, otherwise null
     */
    public static String getMessage(final String code) {
        return getMessage(code, new String[0]);
    }

    /**
     * Try to resolve the message. Return null if no message was found.
     *
     * @param code the code to lookup up
     * @param args array of arguments that will be filled in for params within
     *             the message (params look like "{0}", "{1,date}", "{2,time}" within a message),
     *             or {@code null} if none. Every param will be treated as a string.
     * @return the resolved message if the lookup was successful, otherwise null
     */
    public static String getMessage(final String code, final Object... args) {
        final int length = args.length;
        final String[] strArray = new String[length];
        for (int i = 0; i < length; i++) {
            strArray[i] = String.valueOf(args[i]);
        }
        final String msg = SpringHelper.getApplicationContext().getMessage(code, strArray, null, null);

        LOG.trace("getMessage, message: " + msg);
        return msg;
    }

}
