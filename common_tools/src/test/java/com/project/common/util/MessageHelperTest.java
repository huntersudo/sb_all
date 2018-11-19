package com.project.common.util;

import org.assertj.core.api.Assertions;
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
import org.springframework.context.ApplicationContext;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * @author Wang Zheng
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SpringHelper.class})
@PowerMockIgnore("javax.management.*")
public class MessageHelperTest {

    @Before
    public void setUp() throws Exception {
        mockStatic(SpringHelper.class);
        final ApplicationContext ac = mock(ApplicationContext.class);
        when(ac.getMessage(Matchers.anyString(), Matchers.any(Object[].class), Matchers.anyString(), Matchers.any(Locale.class))).then(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return ((String) invocation.getArguments()[0]);
            }
        });
        when(SpringHelper.getApplicationContext()).thenReturn(ac);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPrivateConstructor() throws Exception {
        final MessageHelper messageHelper = Whitebox.invokeConstructor(MessageHelper.class);
        Assertions.assertThat(messageHelper).isNotNull();
    }

    @Test
    public void testGetMessage() throws Exception {
        final String code = "MSG000001";
        assertThat(code).isEqualTo(MessageHelper.getMessage(code));
    }

    @Test
    public void testGetMessage2() throws Exception {
        final String code = "MSG000002";
        assertThat(code).isEqualTo(MessageHelper.getMessage(code, new String[]{"1"}));
    }
}