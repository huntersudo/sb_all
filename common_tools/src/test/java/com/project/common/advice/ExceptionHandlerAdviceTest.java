package com.project.common.advice;

import com.project.common.constant.Separator;
import com.project.common.exception.ParamInvalidException;
import com.project.common.exception.ResourceNotFoundException;
import com.project.common.exception.ServerException;
import com.project.common.util.MessageHelper;
import com.project.common.util.MockUtil;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * @author Wang Zheng
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ExceptionHandlerAdvice.class, MessageHelper.class})
@PowerMockIgnore("javax.management.*")
public class ExceptionHandlerAdviceTest {

    private ExceptionHandlerAdvice eha;

    @Before
    public void setUp() throws Exception {
        eha = new ExceptionHandlerAdvice();
    }

    @After
    public void tearDown() throws Exception {
        eha = null;
    }

    @Test
    public void testHandleNoHandlerFoundException() throws Exception {
        final String code = "MSG999994";
        MockUtil.mockGetMessage(code);

        final NoHandlerFoundException e = new NoHandlerFoundException("mock no handler exception", null, null);
        try {
            eha.handleNoHandlerFoundException(e);
        } catch (Exception e1) {
            assertThat(code).isEqualTo(e1.getMessage());
        }
    }

    @Test
    public void testHandleHttpRequestMethodNotSupportedException() throws Exception {
        final String code = "MSG999993";
        MockUtil.mockGetMessage(code);

        try {
            eha.handleHttpRequestMethodNotSupportedException(new HttpRequestMethodNotSupportedException("mock method not support exception"));
        } catch (Exception e1) {
            assertThat(code).isEqualTo(e1.getMessage());
        }
    }

    @Test
    public void testHandleMethodArgumentNotValidException() throws Exception {
        final String name = "mock name";
        final String msg = "mock message";
        final ObjectError oe = new ObjectError(name, msg);

        final MethodArgumentNotValidException e = mock(MethodArgumentNotValidException.class);
        final BindingResult b = mock(BindingResult.class);
        when(b.hasErrors()).thenReturn(true, false);
        when(b.getAllErrors()).thenReturn(new ArrayList<ObjectError>() {{
            this.add(oe);
        }});

        when(e.getBindingResult()).thenReturn(b);

        try {
            // case 1
            eha.handleMethodArgumentNotValidException(e);

            // case2
            eha.handleMethodArgumentNotValidException(e);
        } catch (Exception e1) {
            Assertions.assertThat((msg + Separator.LINE.val())).isEqualTo(e1.getMessage());
        }
    }

    @Test
    public void testHandleServletRequestBindingException() throws Exception {
        final String code = "MSG999995";
        MockUtil.mockGetMessage(code);

        try {
            eha.handleServletRequestBindingException(new ServletRequestBindingException("mock binding exception"));
        } catch (Exception e) {
            assertThat(code).isEqualTo(e.getMessage());
        }
    }

    @Test
    public void testHandleHttpMessageNotReadableException() throws Exception {
        final String code = "MSG999997";
        MockUtil.mockGetMessage(code);

        try {
            eha.handleHttpMessageNotReadableException(new HttpMessageNotReadableException("mock http message exception"));
        } catch (Exception e) {
            assertThat(code).isEqualTo(e.getMessage());
        }
    }

    @Test
    public void testHandleResourceNotFoundException() throws Exception {
        final String msg = "mock exception";
        final ResourceNotFoundException e = new ResourceNotFoundException(msg);
        try {
            eha.handleResourceNotFoundException(e);
        } catch (Exception e1) {
            assertThat(msg).isEqualTo(e1.getMessage());
        }
    }

    @Test
    public void testHandleBadRequestException() throws Exception {
        final String msg = "mock exception";
        final ParamInvalidException e = new ParamInvalidException(msg);
        try {
            eha.handleBadRequestException(e);
        } catch (Exception e1) {
            assertThat(msg).isEqualTo(e1.getMessage());
        }
    }

    @Test
    public void testHandleInternalServerErrorException() throws Exception {
        final String msg = "mock exception";
        final ServerException e = new ServerException(msg);
        try {
            eha.handleInternalServerErrorException(e);
        } catch (Exception e1) {
            assertThat(msg).isEqualTo(e1.getMessage());
        }
    }

    @Test
    public void testHandleSQLException() throws Exception {
        final String code = "MSG999998";
        MockUtil.mockGetMessage(code);
        try {
            eha.handleSQLException(new Exception("mock SQL exception"));
        } catch (Exception e) {
            assertThat(code).isEqualTo(e.getMessage());
        }
    }

    @Test
    public void testHandleSystemException() throws Exception {
        final String code = "MSG999999";
        MockUtil.mockGetMessage(code);
        try {
            eha.handleSystemException(new Exception("mock exception"));
        } catch (Exception e) {
            assertThat(code).isEqualTo(e.getMessage());
        }
    }
}