package com.rx.utils.shiroUtil;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

public class CesBeanUtils {
//  private static DozerBeanMapper dozer = new DozerBeanMapper();
  private static ObjectMapper mapper;

  private CesBeanUtils() {
  }

  public static String toJson(Object bean) throws JsonProcessingException {
    initJackson();
    return bean == null ? null : mapper.writeValueAsString(bean);
  }

  public static <T> T toBean(String json, Class<T> beanClass) throws JsonMappingException, IOException {
    initJackson();
    return mapper.readValue(json, beanClass);
  }

  private static void initJackson() {
    if (mapper == null) {
      mapper = new ObjectMapper();
      mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
      mapper.setTimeZone(TimeZone.getDefault());
    }

  }

//  public static <T> T map(Object source, Class<T> destinationClass) {
//    return dozer.map(source, destinationClass);
//  }
//
//  public static <T> List<T> mapList(List<T> sourceList, Class<T> destinationClass) {
//    if (sourceList != null && !sourceList.isEmpty()) {
//      List<T> destinationList = Lists.newArrayList();
//      Iterator i$ = sourceList.iterator();
//
//      while(i$.hasNext()) {
//        Object sourceObject = i$.next();
//        T destinationObject = dozer.map(sourceObject, destinationClass);
//        destinationList.add(destinationObject);
//      }
//
//      return destinationList;
//    } else {
//      return sourceList;
//    }
//  }

//  public static void copy(Object source, Object destinationObject) {
//    dozer.map(source, destinationObject);
//  }

//  public static Object convertValue(Class clazz, String propertyName, Object value) {
//    try {
//      int pos = propertyName.indexOf(".");
//      if (pos != -1) {
//        String p = propertyName.substring(0, pos);
//        String s = propertyName.substring(pos + 1);
//        return convertValue(BeanUtils.findPropertyType(p, new Class[]{clazz}), s, value);
//      } else {
//        Class toType = BeanUtils.findPropertyType(propertyName, new Class[]{clazz});
//        return convertValue(toType, value);
//      }
//    } catch (Exception var6) {
//      throw new IllegalArgumentException("数据类型转换失败", var6);
//    }
//  }

//  public static Object convertValue(Class<?> valueType, Object value) throws Exception {
//    return valueType == Date.class ? CesDateUtils.toDate(value) : OgnlOps.convertValue(value, valueType);
//  }

//  public static <K, T> Map<K, T> setToMap(Set<T> set, Class<K> clazz, String property) {
//    Map<K, T> map = new HashMap();
//    Iterator i$ = set.iterator();
//
//    while(i$.hasNext()) {
//      T t = i$.next();
//      Object key = CesClassUtils.getField(t, property);
//      if (key != null && clazz.isAssignableFrom(key.getClass())) {
//        map.put(key, t);
//      }
//    }
//
//    return map;
//  }

//  public static <T> T copyProperties(Object o, Class<T> clazz) {
//    if (o == null) {
//      return null;
//    } else {
//      Object t = null;
//
//      try {
//        t = clazz.newInstance();
//        BeanUtils.copyProperties(o, t);
//      } catch (BeansException var4) {
//        var4.printStackTrace();
//      } catch (InstantiationException var5) {
//        var5.printStackTrace();
//      } catch (IllegalAccessException var6) {
//        var6.printStackTrace();
//      }
//
//      return t;
//    }
//  }

//  public static <T> List<T> copyProperties(List<?> list, Class<T> clazz) {
//    if (list == null) {
//      return null;
//    } else {
//      List<T> l = new ArrayList(list.size());
//      Iterator i$ = list.iterator();
//
//      while(i$.hasNext()) {
//        Object o = i$.next();
//        l.add(copyProperties(o, clazz));
//      }
//
//      return l;
//    }
//  }
}

