package com.project.common.dto;

import com.project.common.constant.StatusCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Wang Zheng
 */
public class ResponseWrapperTest {

    private ResponseWrapper rw;

    @Before
    public void setUp() throws Exception {
        rw = new ResponseWrapper();
    }

    @After
    public void tearDown() throws Exception {
        rw = null;
    }

    @Test
    public void testSuccess() throws Exception {
        final ResponseWrapper success = rw.success(StatusCode.SC_200.val());
        assertThat(success.getMeta().getCode()).isEqualTo(StatusCode.SC_200.val());
        assertThat(success.getData()).isEqualTo(new HashMap<>());
    }

    @Test
    public void testSuccess1() throws Exception {
        final ResponseWrapper success = rw.success(StatusCode.SC_200.val(), null);
        assertThat(success.getMeta().getCode()).isEqualTo(StatusCode.SC_200.val());
        assertThat(success.getData()).isEqualTo(new HashMap<>());
    }

    @Test
    public void testSuccessRespForBool() throws Exception {
        final ResponseWrapper success = ResponseWrapper.successRespForBool(false);
        assertThat(success.getMeta().getCode()).isEqualTo(StatusCode.SC_200.val());
        assertThat(((HashMap) success.getData()).get("result")).isEqualTo(false);
    }

    @Test
    public void testSuccessRespForVoid() throws Exception {
        final ResponseWrapper success = ResponseWrapper.successRespForVoid();
        assertThat(success.getMeta().getCode()).isEqualTo(StatusCode.SC_200.val());
        assertThat(success.getData()).isEqualTo(new HashMap<>());
    }

    @Test
    public void testFailure() throws Exception {
        final ResponseWrapper success = rw.failure(StatusCode.SC_404.val());
        assertThat(success.getMeta().getCode()).isEqualTo(StatusCode.SC_404.val());
        assertThat(success.getMeta().getMessage()).isEqualTo("Error");
        assertThat(success.getData()).isEqualTo(new HashMap<>());
    }

    @Test
    public void testFailure1() throws Exception {
        final String message = "error msg";
        final ResponseWrapper success = rw.failure(StatusCode.SC_404.val(), message);
        assertThat(success.getMeta().getCode()).isEqualTo(StatusCode.SC_404.val());
        assertThat(success.getMeta().getMessage()).isEqualTo(message);
        assertThat(success.getData()).isEqualTo(new HashMap<>());
    }

    @Test
    public void testMetaConstructor() throws Exception {
        final ResponseWrapper.Meta meta = new ResponseWrapper.Meta(StatusCode.SC_200.val());
        assertThat(meta.getCode()).isEqualTo(StatusCode.SC_200.val());
    }
}