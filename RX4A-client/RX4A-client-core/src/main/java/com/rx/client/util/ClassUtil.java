package com.rx.client.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Maplist to objectlist
 *
 * @param map maplist
 * @param clazz objectlist
 * @return
 */


/**
 * A class util - used to convert map and object.
 *
 * @author gu.chengbin@cesgroup.com.cn
 */
public class ClassUtil {

  /**
   * objList to maplist.
   *
   * @param objList objList
   * @return maplist
   */
  public static List<Map<String, Object>> object2MapList(List<?> objList) {
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    for (Object obj : objList) {
      Map<String, Object> map = object2Map(obj);
      list.add(map);
    }
    return list;

  }

  /**
   * mapList to objlist.
   *
   * @param mapList mapList
   * @param clazz objclass
   * @return objlist
   */
  public static List map2ObjectList(List<Map<String, Object>> mapList, Class<?> clazz) {
    List<Object> resList = new ArrayList<>();
    for (Map<String, Object> map : mapList) {
      Object obj = map2Object(map, clazz);
      resList.add(obj);
    }
    return resList;

  }

  /**
   * entity to Map.
   *
   * @param obj entity
   */
  public static Map<String, Object> object2Map(Object obj) {
    Map<String, Object> map = new HashMap<>();
    if (obj == null) {
      return map;
    }
    Class clazz = obj.getClass();
    Field[] fields = clazz.getDeclaredFields();
    try {
      for (Field field : fields) {
        field.setAccessible(true);
        map.put(field.getName(), field.get(obj));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }

  /**
   * Map to object.
   *
   * @param map map
   * @param clazz object
   */
  public static Object map2Object(Map<String, Object> map, Class<?> clazz) {
    if (map == null) {
      return null;
    }
    Object obj = null;
    try {
      obj = clazz.newInstance();

      Field[] fields = obj.getClass().getDeclaredFields();
      for (Field field : fields) {
        int mod = field.getModifiers();
        if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
          continue;
        }
        field.setAccessible(true);
        try {
          String currType = field.getType().getSimpleName();
          //map.get(field.getName()).getClass().getSimpleName()
          String valueType = null;
          if (map.get(field.getName()) != null) {
            valueType = map.get(field.getName()).getClass().getSimpleName();
          }
          Object value = map.get(field.getName());
          if (currType.equals("int") && null == value) {
            value = 0;
          } else if (currType.equals("Long") && "Double".equals(valueType)) {
            double mvalue = (Double) map.get(field.getName());
            value = Math.round(mvalue);
          } else if (currType.equals("long") && "Double".equals(valueType)) {
            double mvalue = (Double) map.get(field.getName());
            value = Math.round(mvalue);
          } else if (currType.equals("Long") && valueType == null) {
            value = 0L;
          } else if (currType.equals("long") && valueType == null) {
            value = 0L;
          } else if (currType.equals("int") && "Double".equals(valueType)) {
            double dvalue = (Double) map.get(field.getName());
            value = ((Number) dvalue).intValue();
          }
          field.set(obj, value);

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }


}
