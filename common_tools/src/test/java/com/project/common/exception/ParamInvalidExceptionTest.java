package com.project.common.exception;

import com.project.common.constant.StatusCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Wang Zheng
 */
public class ParamInvalidExceptionTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testParamInvalidException() {
        final String msg = "mock msg";
        final ParamInvalidException paramInvalidException = new ParamInvalidException(msg);
        assertThat(paramInvalidException.getCode()).isEqualTo(StatusCode.SC_400.val());
        assertThat(paramInvalidException.getMessage()).isEqualTo(msg);
    }
}