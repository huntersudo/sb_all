package com.project.common.util;

import com.project.common.dto.ResponseWrapper;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.context.ApplicationContext;

import static org.powermock.api.mockito.PowerMockito.*;

/**
 * @author Wang Zheng
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SpringHelper.class})
@PowerMockIgnore("javax.management.*")
public class SpringHelperTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPrivateConstructor() throws Exception {
        final SpringHelper springHelper = Whitebox.invokeConstructor(SpringHelper.class);
        Assertions.assertThat(springHelper).isNotNull();
    }

    @Test
    public void testSetAndGetApplicationContext() throws Exception {
        final SpringHelper sh = Whitebox.invokeConstructor(SpringHelper.class);
        sh.setApplicationContext(null);
        Assertions.assertThat(sh.getApplicationContext()).isNull();
    }

    @Test
    public void testGetBean() throws Exception {
        final ApplicationContext ac = mockAC();
        when(ac.getBean(Matchers.anyString())).thenReturn(null);

        Assertions.assertThat(SpringHelper.getBean("bean name")).isNull();
    }

    @Test
    public void testGetBean1() throws Exception {
        final ApplicationContext ac = mockAC();
        when(ac.getBean(Matchers.any(Class.class))).thenReturn(null);

        Assertions.assertThat(SpringHelper.getBean(ResponseWrapper.class)).isNull();
    }

    @Test
    public void testGetBean2() throws Exception {
        final ApplicationContext ac = mockAC();
        when(ac.getBean(Matchers.anyString(), Matchers.any(Class.class))).thenReturn(new ResponseWrapper());

        Assertions.assertThat(SpringHelper.getBean("response wrapper", ResponseWrapper.class)).isInstanceOf(ResponseWrapper.class);
    }

    private ApplicationContext mockAC() {
        final ApplicationContext ac = mock(ApplicationContext.class);
        Whitebox.setInternalState(SpringHelper.class, "ac", ac);
        return ac;
    }
}