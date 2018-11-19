package com.project.common;

import com.project.common.advice.ExceptionHandlerAdviceTest;
import com.project.common.advice.ResponseConverterTest;
import com.project.common.constant.EncodingTest;
import com.project.common.constant.SeparatorTest;
import com.project.common.constant.StatusCodeTest;
import com.project.common.constant.SymbolTest;
import com.project.common.dto.ResponseWrapperTest;
import com.project.common.dto.converter.AbstractDtoConverterTest;
import com.project.common.dto.converter.DtoConverterHandlerTest;
import com.project.common.exception.*;
import com.project.common.util.MessageHelperTest;
import com.project.common.util.SpringHelperTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Wang Zheng
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SpringHelperTest.class,
        MessageHelperTest.class,
        ExceptionHandlerAdviceTest.class,
        ResponseConverterTest.class,
        EncodingTest.class,
        SeparatorTest.class,
        StatusCodeTest.class,
        SymbolTest.class,
        AbstractDtoConverterTest.class,
        DtoConverterHandlerTest.class,
        ResponseWrapperTest.class,
        MethodNotSupportedExceptionTest.class,
        ParamInvalidExceptionTest.class,
        ResourceNotFoundExceptionTest.class,
        RootExceptionTest.class,
        ServerExceptionTest.class,
        AuthExceptionTest.class
})
public class AllTestSuite {
    // the class remains empty,
    // used only as a holder for the above annotations
}
