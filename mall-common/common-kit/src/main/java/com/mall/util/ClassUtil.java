package com.mall.util;

/**
 * <pre>
 * class com.mall.util
 * </pre>
 *
 * @author LCN
 * @date 2019-12-10 21:37
 */
public class ClassUtil {

    /**
     * Judge whether a class is base type
     *
     * @param className object
     * @return boolean result
     */
    public static boolean isBaseType(Class className) {
        return isBaseType(className, false);
    }

    /**
     * Judge whether a class is base type, String can special handler
     *
     * @param className object
     * @param incString String is base type
     * @return boolean result
     */
    public static boolean isBaseType(Class className, boolean incString) {

        if (incString && className.equals(String.class)) {
            return true;
        }

        return className.equals(Integer.class) || className.equals(int.class) ||
            className.equals(Byte.class) || className.equals(byte.class) ||
            className.equals(Long.class) || className.equals(long.class) ||
            className.equals(Double.class) || className.equals(double.class) ||
            className.equals(Float.class) || className.equals(float.class) ||
            className.equals(Character.class) || className.equals(char.class) ||
            className.equals(Short.class) || className.equals(short.class) ||
            className.equals(Boolean.class) || className.equals(boolean.class);
    }
}
