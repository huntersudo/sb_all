package com.project.common.dto.converter;

import com.project.common.exception.ServerException;
import com.project.common.util.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wang Zheng
 */
@Component
public class DtoConverterHandler {

    @Autowired(required = false)
    private List<DtoConverter> converterList;

    /**
     * Convert the source object to its corresponding DTO object.
     *
     * @param srcObj           the source object
     * @param destinationClass the class of the destination, only the first one will be applied
     * @return an instance of the corresponding DTO of the source object, or an empty map if no matching converter was found
     */
    public Object convertSingle(final Object srcObj, final Class<?>... destinationClass) {
        for (DtoConverter converter : converterList) {
            if (converter.support(srcObj, destinationClass)) {
                return converter.convert(srcObj);
            }
        }

        final String msg = MessageHelper.getMessage("MSG999996", ((null == srcObj) ? "null" : srcObj.getClass().getCanonicalName()));
        throw new ServerException(msg);
    }

    /**
     * Convert all the source objects to there corresponding DTO object.
     *
     * @param srcList          the source object list
     * @param destinationClass the class of the destination, only the first one will be applied
     * @return a list which contains all the DTOs
     */
    public List convertList(final List srcList, final Class<?>... destinationClass) {
        final List result = new ArrayList(srcList.size());

        for (Object obj : srcList) {
            result.add(convertSingle(obj, destinationClass));
        }

        return result;
    }
}
