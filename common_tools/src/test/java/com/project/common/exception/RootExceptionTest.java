package com.project.common.exception;

import com.project.common.constant.StatusCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Wang Zheng
 */
public class RootExceptionTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRootException() {
        final String msg = "mock msg";
        final RootException rootException = new RootException(msg, StatusCode.SC_404.val());
        assertThat(rootException.getCode()).isEqualTo(StatusCode.SC_404.val());
        assertThat(rootException.getMessage()).isEqualTo(msg);
    }
}