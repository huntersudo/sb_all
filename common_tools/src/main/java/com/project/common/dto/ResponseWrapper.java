package com.project.common.dto;

import com.project.common.constant.StatusCode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wang Zheng
 */
public class ResponseWrapper implements Serializable {
    /**
     * default message for successful REST
     */
    private static final String OK = "OK";

    /**
     * default message for failed REST
     */
    private static final String ERROR = "Error";

    /**
     * the key for the boolean value
     */
    private static final String BOOL_RES_KEY = "result";

    /**
     * an object which contains the meta data of a REST response
     */
    private Meta meta;

    /**
     * an object which contains the data of a REST response
     */
    private Object data;

    /**
     * Set the success code for this response, with an empty data.
     *
     * @param code the success code
     * @return this response
     */
    public ResponseWrapper success(String code) {
        return this.success(code, new HashMap<>(0));
    }

    /**
     * Set the success code and data for this response.
     *
     * @param code the success code
     * @param data the data
     * @return this response
     */
    public ResponseWrapper success(String code, Object data) {
        this.meta = new Meta(code, OK);
        this.data = (data != null ? data : new HashMap<>(0));
        return this;
    }

    /**
     * A convenient way to build a success response for a successful REST, generally a 'GET' REST,
     * which wants to return a boolean value.
     *
     * @param result the boolean to return
     * @return a success response
     */
    public static ResponseWrapper successRespForBool(boolean result) {
        ResponseWrapper dr = new ResponseWrapper();

        Map<String, Boolean> res = new HashMap<>();
        res.put(BOOL_RES_KEY, result);

        dr.success(StatusCode.SC_200.val(), res);
        return dr;
    }

    /**
     * A convenient way to build a success response for a successful REST which wants to return 'void'.
     *
     * @return a success response
     */
    public static ResponseWrapper successRespForVoid() {
        ResponseWrapper dr = new ResponseWrapper();
        dr.success(StatusCode.SC_200.val());
        return dr;
    }

    /**
     * Set the failure code for this response, with the default failure message.
     *
     * @param code the failure code
     * @return this response
     */
    public ResponseWrapper failure(String code) {
        return this.failure(code, ERROR);
    }

    /**
     * Set the failure code and failure message for this response.
     *
     * @param code    the failure code
     * @param message the message
     * @return this response
     */
    public ResponseWrapper failure(String code, String message) {
        this.meta = new Meta(code, message);
        this.data = new HashMap<>(0);
        return this;
    }

    /**
     * Get the meta.
     *
     * @return the meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * Get the data.
     *
     * @return the data
     */
    public Object getData() {
        return data;
    }

    public static class Meta implements Serializable {

        /**
         * the code
         */
        private String code;

        /**
         * the message
         */
        private String message;

        /**
         * Instantiates a new AssetMeta.
         *
         * @param code the code
         */
        public Meta(String code) {
            this(code, "");
        }

        /**
         * Instantiates a new AssetMeta.
         *
         * @param code    the code
         * @param message the message
         */
        public Meta(String code, String message) {
            this.code = code;
            this.message = message;
        }

        /**
         * Get the code.
         *
         * @return the code
         */
        public String getCode() {
            return code;
        }

        /**
         * Get the message.
         *
         * @return the message
         */
        public String getMessage() {
            return message;
        }
    }
}
