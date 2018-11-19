package com.project.common.advice;

import com.project.common.constant.Separator;
import com.project.common.exception.*;
import com.project.common.util.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Wang Zheng
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    private static final transient Logger LOG = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResourceNotFoundException handleNoHandlerFoundException(final NoHandlerFoundException ex) {
        LOG.error("throw NoHandlerFoundException:", ex);
        final String msg = MessageHelper.getMessage("MSG999994", ex.getRequestURL());
        return new ResourceNotFoundException(msg);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public MethodNotSupportedException handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException ex) {
        LOG.error("throw HttpRequestMethodNotSupportedException:", ex);
        final String msg = MessageHelper.getMessage("MSG999993", ex.getMethod());
        return new MethodNotSupportedException(msg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ParamInvalidException handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        final StringBuffer sb = new StringBuffer();
        final BindingResult result = ex.getBindingResult();
        if (result.hasErrors()) {
            final List<ObjectError> errorList = result.getAllErrors();
            for (ObjectError error : errorList) {
                sb.append(error.getDefaultMessage()).append(Separator.LINE.val());
            }
        }
        return new ParamInvalidException(sb.toString());
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ParamInvalidException handleServletRequestBindingException(final ServletRequestBindingException ex) {
        LOG.error("throw ServletRequestBindingException:", ex);
        final String msg = MessageHelper.getMessage("MSG999995", ex.getMessage());
        return new ParamInvalidException(msg);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RootException handleHttpMessageNotReadableException(final HttpMessageNotReadableException ex) {
        LOG.error("throw HttpMessageNotReadableException:", ex);
        final String msg = MessageHelper.getMessage("MSG999997");
        return new ParamInvalidException(msg);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResourceNotFoundException handleResourceNotFoundException(final ResourceNotFoundException ex) {
        return ex;
    }

    @ExceptionHandler({ParamInvalidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RootException handleBadRequestException(final RootException ex) {
        return ex;
    }

    @ExceptionHandler({ServerException.class, RootException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RootException handleInternalServerErrorException(final RootException ex) {
        return ex;
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RootException handleSQLException(final Exception ex) {
        LOG.error("throw SQLException:", ex);
        final String msg = MessageHelper.getMessage("MSG999998");
        return new ServerException(msg);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RootException handleSystemException(final Exception ex) {
        LOG.error("throw Exception:", ex);
        final String msg = MessageHelper.getMessage("MSG999999");
        return new ServerException(msg);
    }
}
