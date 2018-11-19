package com.project.common.dto.converter;

/**
 * @author Wang Zheng
 */
public interface DtoConverter<S, D> {
    /**
     * Check if the object is supported by this converter.<br/>
     * If you have one model which will be converted to different dtos according to different situations,
     * then you <strong>MUST</strong> set the 'destination class' of which the dto is.
     *
     * @param object           the object to be checked
     * @param destinationClass the class of the destination, only the first one will be applied
     * @return true if the object is supported by this convert, false otherwise
     */
    boolean support(final Object object, final Class<?>... destinationClass);

    /**
     * Convert the source object to the destination object.
     *
     * @param s the source object
     * @return an instance of the destination object
     */
    D convert(final S s);
}
