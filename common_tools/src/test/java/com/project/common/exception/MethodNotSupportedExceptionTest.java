package com.project.common.exception;

import com.project.common.constant.StatusCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;


/**
 * @author Wang Zheng
 */
public class MethodNotSupportedExceptionTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testMethodNotSupportedException() {
        final String msg = "mock msg";
        final MethodNotSupportedException methodNotSupportedException = new MethodNotSupportedException(msg);
        assertThat(methodNotSupportedException.getCode()).isEqualTo(StatusCode.SC_405.val());
        assertThat(methodNotSupportedException.getMessage()).isEqualTo(msg);
    }
}