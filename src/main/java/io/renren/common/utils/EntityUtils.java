package io.renren.common.utils;

import org.springframework.cglib.beans.BeanMap;

import java.util.Map;

public class EntityUtils {
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }
}
