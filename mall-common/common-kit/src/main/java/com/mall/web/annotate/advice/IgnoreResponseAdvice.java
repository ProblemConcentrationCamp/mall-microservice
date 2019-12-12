package com.mall.web.annotate.advice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 *  the annotate can use on the class or method, use this annotate mean the method return value
 *  not need to handler to Response object
 * </pre>
 *
 * @author LCN
 * @date 2019-12-12 22:51
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseAdvice {
}
