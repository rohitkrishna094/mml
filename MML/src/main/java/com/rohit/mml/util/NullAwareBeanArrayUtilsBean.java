package com.rohit.mml.util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;

public class NullAwareBeanArrayUtilsBean extends BeanUtilsBean {

    @Override
    public void copyProperty(Object dest, String name, Object value) throws IllegalAccessException, InvocationTargetException {
        if (value == null || (value instanceof List<?> && ((List<?>) value).size() <= 0))
            return;
        super.copyProperty(dest, name, value);
    }

}