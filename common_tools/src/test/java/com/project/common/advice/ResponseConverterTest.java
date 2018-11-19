package com.project.common.advice;

import com.project.common.annotation.SkipResponseConvert;
import com.project.common.constant.StatusCode;
import com.project.common.dto.ResponseWrapper;
import com.project.common.exception.RootException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;
import static org.powermock.api.mockito.PowerMockito.*;


/**
 * @author Wang Zheng
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ResponseConverter.class})
@PowerMockIgnore("javax.management.*")
public class ResponseConverterTest {

    private ResponseConverter rc;

    @Before
    public void setUp() throws Exception {
        rc = new ResponseConverter();
    }

    @After
    public void tearDown() throws Exception {
        rc = null;
    }

    @Test
    public void testSupports() throws Exception {
        final MethodParameter mp = mock(MethodParameter.class);
        final Method m1 = MockClass.class.getMethod("method1");
        final Method m2 = MockClass.class.getMethod("method2");
        final Method m3 = MockClass.class.getMethod("method3");

        when(mp.getMethod()).thenReturn(m1, m2, m3);

        // case 1
        when(mp.getMethodAnnotation(Matchers.any(Class.class))).thenReturn(new SkipResponseConvert() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return SkipResponseConvert.class;
            }
        }, null);

        assertThat(rc.supports(mp, Object.class)).isFalse();
        assertThat(rc.supports(mp, Object.class)).isFalse();
        assertThat(rc.supports(mp, Object.class)).isTrue();
    }

    private class MockClass {
        @SkipResponseConvert
        public void method1() {

        }

        public ResponseWrapper method2() {
            return ResponseWrapper.successRespForBool(false);
        }

        public MockDto method3() {
            return new MockDto();
        }
    }

    private class MockDto {
    }

    @Test
    public void testBeforeBodyWrite() throws Exception {
        final String msg = "mock exception";
        final String code = StatusCode.SC_400.val();
        final RootException ex = new RootException(msg, code);

        final MethodParameter returnType = mock(MethodParameter.class);
        final MediaType selectedContentType = null;
        final Class selectedConverterType = null;
        final ServerHttpRequest request = mock(ServerHttpRequest.class);
        final ServerHttpResponse httpResponse = null;

        final URI uri = mock(URI.class);
        when(request.getURI()).thenReturn(uri);
        when(uri.getQuery()).thenReturn("");

        rc = spy(rc);
        doReturn(false, false, true).when(rc, "isEnvelop", Matchers.anyString());

        // case 1
        final Object result = rc.beforeBodyWrite(ex, returnType, selectedContentType, selectedConverterType, request, httpResponse);

        assertThat(result instanceof ResponseWrapper.Meta).isTrue();
        assertThat(msg).isEqualTo(((ResponseWrapper.Meta) result).getMessage());
        assertThat(code).isEqualTo(((ResponseWrapper.Meta) result).getCode());

        // case 2
        final Object body = new MockDto();
        final Object result2 = rc.beforeBodyWrite(body, returnType, selectedContentType, selectedConverterType, request, httpResponse);

        assertThat(body).isEqualTo(result2);

        // case 3
        final ResponseWrapper response = (ResponseWrapper) rc.beforeBodyWrite(ex, returnType, selectedContentType, selectedConverterType, request, httpResponse);

        assertThat(code).isEqualTo(response.getMeta().getCode());
        assertThat(msg).isEqualTo(response.getMeta().getMessage());

        when(returnType.getMethodAnnotations()).thenAnswer(new Answer<Object>() {
            private int i = 0;

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return new ArrayList<Annotation>() {{
                    this.add(mockAnnotation(i++));
                }}.toArray(new Annotation[]{});
            }

            private ResponseStatus mockAnnotation(final int idx) {
                return new ResponseStatus() {

                    @Override
                    public Class<? extends Annotation> annotationType() {
                        if (idx < 4) {
                            return ResponseStatus.class;

                        } else {
                            return Documented.class;
                        }
                    }

                    @Override
                    public HttpStatus value() {
                        if (idx < 3) {
                            return HttpStatus.OK;
                        } else {
                            return HttpStatus.CREATED;
                        }
                    }

                    @Override
                    public HttpStatus code() {
                        return null;
                    }

                    @Override
                    public String reason() {
                        return null;
                    }
                };
            }

        });

        // case 4
        final MockDto mockDto = new MockDto();
        final ResponseWrapper response2 = (ResponseWrapper) rc.beforeBodyWrite(mockDto, returnType, selectedContentType, selectedConverterType, request, httpResponse);

        assertThat(StatusCode.SC_200.val()).isEqualTo(response2.getMeta().getCode());
        assertThat(mockDto).isEqualTo(response2.getData());

        // case 5
        final String str = "mock string body";
        final ObjectMapper mapper = new ObjectMapper();
        final ResponseWrapper response1 = new ResponseWrapper().success(StatusCode.SC_200.val(), str);
        assertThat(mapper.writeValueAsString(response1)).isEqualTo(rc.beforeBodyWrite(str, returnType, selectedContentType, selectedConverterType, request, httpResponse));

        // case 6
        final ObjectMapper tmpMapper = mock(ObjectMapper.class);
        final JsonProcessingException jpe = mock(JsonProcessingException.class);
        whenNew(ObjectMapper.class).withNoArguments().thenReturn(tmpMapper);
        when(tmpMapper.writeValueAsString(Matchers.anyObject())).thenThrow(jpe);

        assertThat(str).isEqualTo(rc.beforeBodyWrite(str, returnType, selectedContentType, selectedConverterType, request, httpResponse));

        assertThat(str).isEqualTo(rc.beforeBodyWrite(str, returnType, selectedContentType, selectedConverterType, request, httpResponse));
        
        assertThat(str).isEqualTo(rc.beforeBodyWrite(str, returnType, selectedContentType, selectedConverterType, request, httpResponse));
    }

    @Test
    public void testIsEnvelop() throws Exception {
        boolean result = Whitebox.invokeMethod(rc, "isEnvelop", null);
        assertThat(result).isTrue();

        result = Whitebox.invokeMethod(rc, "isEnvelop", "name=1& envelope=false ");
        assertThat(result).isFalse();
    }
}