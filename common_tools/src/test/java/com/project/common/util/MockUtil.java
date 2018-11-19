package com.project.common.util;

import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;

/**
 * @author Wang Zheng
 */
public class MockUtil {

    public static void mockGetMessage(String code) {
        PowerMockito.mockStatic(MessageHelper.class);
        // use 'any*' method of Matchers to mock the parameter
        PowerMockito.when(MessageHelper.getMessage(Matchers.anyString())).thenReturn(code);
        PowerMockito.when(MessageHelper.getMessage(Matchers.anyString(), Matchers.any(Object[].class))).thenReturn(code);
    }

    public static void mockSpringHelper_GetBean(Object returnValue) {
        PowerMockito.mockStatic(SpringHelper.class);

        PowerMockito.when(SpringHelper.getBean(Matchers.anyString(), Matchers.any(Class.class))).thenReturn(returnValue);
    }
}
