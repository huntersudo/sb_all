package com.project.common.dto.converter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.*;
import org.modelmapper.spi.Mapping;
import org.modelmapper.spi.PropertyInfo;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Wang Zheng
 */
public class AbstractDtoConverterTest {

    private MockConverter mockConverter;

    @Before
    public void setUp() throws Exception {
        mockConverter = new MockConverter();
    }

    @After
    public void tearDown() throws Exception {
        mockConverter = null;
    }

    private class MockConverter extends AbstractDtoConverter<String, String> {

        @Override
        public boolean support(final Object object, final Class<?>... destinationClass) {

            boolean res = object instanceof String;
            if (destinationClass.length > 0) {
                res = destinationClass[0].equals(String.class);
            }

            return res;
        }

        @Override
        public String convert(final String s) {
            dtoMap = new PropertyMap<String, String>() {
                @Override
                protected void configure() {

                }
            };

            typeMap = new TypeMap<String, String>() {
                @Override
                public void addMappings(final PropertyMap<String, String> propertyMap) {

                }

                @Override
                public Condition<?, ?> getCondition() {
                    return null;
                }

                @Override
                public Converter<String, String> getConverter() {
                    return null;
                }

                @Override
                public Class<String> getDestinationType() {
                    return null;
                }

                @Override
                public List<Mapping> getMappings() {
                    return null;
                }

                @Override
                public String getName() {
                    return null;
                }

                @Override
                public Converter<String, String> getPostConverter() {
                    return null;
                }

                @Override
                public Converter<String, String> getPreConverter() {
                    return null;
                }

                @Override
                public Condition<?, ?> getPropertyCondition() {
                    return null;
                }

                @Override
                public Converter<?, ?> getPropertyConverter() {
                    return null;
                }

                @Override
                public Provider<?> getPropertyProvider() {
                    return null;
                }

                @Override
                public Provider<String> getProvider() {
                    return null;
                }

                @Override
                public Class<String> getSourceType() {
                    return null;
                }

                @Override
                public List<PropertyInfo> getUnmappedProperties() {
                    return null;
                }

                @Override
                public String map(final String source) {
                    return null;
                }

                @Override
                public void map(final String source, final String destination) {

                }

                @Override
                public TypeMap<String, String> setCondition(final Condition<?, ?> condition) {
                    return null;
                }

                @Override
                public TypeMap<String, String> setConverter(final Converter<String, String> converter) {
                    return null;
                }

                @Override
                public TypeMap<String, String> setPostConverter(final Converter<String, String> converter) {
                    return null;
                }

                @Override
                public TypeMap<String, String> setPreConverter(final Converter<String, String> converter) {
                    return null;
                }

                @Override
                public TypeMap<String, String> setPropertyCondition(final Condition<?, ?> condition) {
                    return null;
                }

                @Override
                public TypeMap<String, String> setPropertyConverter(final Converter<?, ?> converter) {
                    return null;
                }

                @Override
                public TypeMap<String, String> setPropertyProvider(final Provider<?> provider) {
                    return null;
                }

                @Override
                public TypeMap<String, String> setProvider(final Provider<String> provider) {
                    return null;
                }

                @Override
                public void validate() {

                }
            };

            modelMapper.map(s, String.class);
            return s;
        }
    }

    @Test
    public void testSupport() {
        assertThat(mockConverter.support("1")).isTrue();
        assertThat(mockConverter.support(123)).isFalse();

        assertThat(mockConverter.support("1", String.class)).isTrue();
        assertThat(mockConverter.support("1", Integer.class)).isFalse();
    }

    @Test
    public void testConvert() {
        assertThat(mockConverter.convert("123")).isEqualTo("123");
    }
}