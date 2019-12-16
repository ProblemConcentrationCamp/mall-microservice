package com.mall.util;

import java.util.Collection;
import java.util.Map;

/**
 * Collection & Map Util
 *
 * @author liam
 * @version V1.0
 * @date 2019/11/22
 **/
public class CollectionMapUtil {

  /**
   * 判定map集合是否为空
   *
   * @param map 需要判断的map
   * @return boolean
   * @author liam
   */
  public static boolean isEmpty(Map<?, ?> map) {
    return (map == null || map.isEmpty());
  }

  /**
   * 判定map集合是否不为空
   *
   * @param map 需要判断的map
   * @return boolean
   * @author liam
   */
  public static boolean isNotEmpty(Map<?, ?> map) {
    return !isEmpty(map);
  }

  /**
   * 判定collection集合是否为空
   *
   * @param collection 需要判断的collection
   * @return boolean
   * @author liam
   */
  public static boolean isEmpty(Collection<?> collection) {
    return (collection == null || collection.isEmpty());
  }

  /**
   * 判定collection集合是否不为空
   *
   * @param collection 需要判断的collection
   * @return boolean
   * @author liam
   */
  public static boolean isNotEmpty(Collection<?> collection) {
    return !isEmpty(collection);
  }
}
