package com.project.common.advice;

import com.project.common.annotation.SkipResponseConvert;
import com.project.common.constant.StatusCode;
import com.project.common.constant.Symbol;
import com.project.common.dto.ResponseWrapper;
import com.project.common.exception.RootException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

/**
 * @author Wang Zheng
 */
@ControllerAdvice(annotations = {RestController.class, ControllerAdvice.class})
public class ResponseConverter implements ResponseBodyAdvice {

    private static final transient Logger LOG = LoggerFactory.getLogger(ResponseConverter.class);

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        Class returnClass = returnType.getMethod().getReturnType();

        if (returnType.getMethodAnnotation(SkipResponseConvert.class) != null) {
            LOG.debug("the method has 'SkipResponseConvert' annotation, so its return value will not be converted by this converter.");
            return false;
        }

        if (!returnClass.equals(Object.class)
                && (returnClass.isAssignableFrom(ResponseWrapper.class) || returnClass.isAssignableFrom(ResponseEntity.class))) {
            LOG.debug("the return value is a 'ResponseWrapper' or 'ResponseEntity', so it will not be converted by this converter.");
            return false;
        }

        LOG.debug("the return value will be converted by this converter.");
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        String query = request.getURI().getQuery();
        // do not wrap the result
        if (!isEnvelop(query)) {
            if (body instanceof RootException) {
                RootException exception = (RootException) body;
                ResponseWrapper.Meta meta = new ResponseWrapper.Meta(exception.getCode(), exception.getMessage());
                return meta;
            } else {
                return body;
            }
        }

        // wrap the result
        if (body instanceof RootException) {
            RootException exception = (RootException) body;
            return new ResponseWrapper().failure(exception.getCode(), exception.getMessage());
        } else {
            int code = HttpStatus.OK.value();
            Annotation[] annotations = returnType.getMethodAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(ResponseStatus.class)) {
                    ResponseStatus res = (ResponseStatus) annotation;
                    code = res.value().value();
                    break;
                }
            }

            ResponseWrapper responseWrapper = new ResponseWrapper().success(StatusCode.valueOf(code).val(), body);

            if (body instanceof String) {
                LOG.debug("the return type is string, so convert the response to string");
                try {
                    return new ObjectMapper().writeValueAsString(responseWrapper);
                } catch (JsonProcessingException e) {
                    LOG.warn(e.getMessage(), e);
                    return body;
                }
            } else {
                return responseWrapper;
            }
        }
    }

    /**
     * Check whether the query contains the 'envelope=false' string,
     * which means that the return value of the request should not be enveloped.
     *
     * @param query the request query string
     * @return true if the return value of the request should be enveloped, false otherwise
     */
    private boolean isEnvelop(String query) {
        if (query != null) {
            String[] queries = query.split(Symbol.AND.val());
            for (String s : queries) {
                if (s.trim().equalsIgnoreCase("envelope=false")) {
                    LOG.info("find envelope=false skip response convert");
                    return false;
                }
            }
        }
        return true;
    }
}
