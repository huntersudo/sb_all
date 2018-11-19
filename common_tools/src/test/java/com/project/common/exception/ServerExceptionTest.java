package com.project.common.exception;

import com.project.common.constant.StatusCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Wang Zheng
 */
public class ServerExceptionTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testServerException() {
        final String msg = "mock msg";
        final ServerException serverException = new ServerException(msg);
        assertThat(serverException.getCode()).isEqualTo(StatusCode.SC_500.val());
        assertThat(serverException.getMessage()).isEqualTo(msg);
    }
}