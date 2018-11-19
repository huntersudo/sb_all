package com.project.common.exception;

import com.project.common.constant.StatusCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Wang Zheng
 */
public class ResourceNotFoundExceptionTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testResourceNotFoundException() {
        final String msg = "mock msg";
        final ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException(msg);
        assertThat(resourceNotFoundException.getCode()).isEqualTo(StatusCode.SC_404.val());
        assertThat(resourceNotFoundException.getMessage()).isEqualTo(msg);
    }
}