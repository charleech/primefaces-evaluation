package org.charleech.primefaces.eval.intrcptr;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * This is a CDI Interceptor Annotation which describes and identifies the
 * {@link DefaultInterceptable} instance for capturing the footprint.
 *
 * @author Charlee.Ch
 * @version 0.0.1
 * @since 0.0.1
 */
@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({
    ElementType.METHOD,
    ElementType.TYPE
    })
@Documented
public @interface MyInterceptable {

}
