package org.charleech.primefaces.eval.intrcptr;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.charleech.primefaces.eval.Markable;

/**
 * <p>
 * This is an interface which provides the feature as a default CDI interceptor.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 * @see Markable
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
public interface DefaultInterceptable extends Markable {
    /**
     * Perform the intercepting.
     *
     * @param context
     *            The {@link InvocationContext}.
     * @return The proceeded result.
     * @throws Exception
     *             If there is any error.
     * @since 0.0.1
     */
    @AroundInvoke
    Object perform(final InvocationContext context) throws Exception;
}
