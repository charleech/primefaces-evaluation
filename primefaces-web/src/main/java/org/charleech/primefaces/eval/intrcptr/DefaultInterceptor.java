package org.charleech.primefaces.eval.intrcptr;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import lombok.extern.slf4j.Slf4j;

import org.charleech.primefaces.eval.AbstractMarker;

/**
 * <p>
 * This is a concrete implementing class which provides the feature described
 * at {@link DefaultInterceptable}.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 * @see AbstractMarker
 * @see DefaultInterceptable
 * @see <a rel="license"
 *      href="http://creativecommons.org/licenses/by-nc-sa/3.0/"><img
 *      alt="Creative Commons License" style="border-width:0"
 *      src="http://i.creativecommons.org/l/by-nc-sa/3.0/88x31.png" /></a><br />
 *      <span xmlns:dct="http://purl.org/dc/terms/"
 *      property="dct:title">Charlee@GitHub</span> by <a
 *      xmlns:cc="http://creativecommons.org/ns#"
 *      href="https://github.com/charleech" property="cc:attributionName"
 *      rel="cc:attributionURL">Charlee Chitsuk</a> is licensed under a <a
 *      rel="license"
 *      href="http://creativecommons.org/licenses/by-nc-sa/3.0/">Creative
 *      Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License</a>.
 */
@Interceptor
@MyInterceptable
@Slf4j
public class DefaultInterceptor extends AbstractMarker
                             implements DefaultInterceptable {

    /**
     * This is a default serial version UID.
     *
     * @since 0.0.1
     */
    private static final long serialVersionUID = 1L;

    @Override
    @AroundInvoke
    public Object perform(final InvocationContext context) throws Exception {
        Object result = null;
        try {
            DefaultInterceptor.log.info(
               "This is a message from interceptor.");
            result = context.proceed();
            return result;
        } finally {
            result = null;
        }
    }

}
