package com.project.common.exception;

import com.project.common.constant.StatusCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Wang Zheng
 */
public class AuthExceptionTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAuthException() {
        final String msg = "mock msg";
        final AuthException authException = new AuthException(msg);
        assertThat(authException.getCode()).isEqualTo(StatusCode.SC_405.val());
        assertThat(authException.getMessage()).isEqualTo(msg);
    }
}