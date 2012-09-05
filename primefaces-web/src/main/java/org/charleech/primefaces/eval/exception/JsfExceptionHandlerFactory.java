package org.charleech.primefaces.eval.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * <p>
 * This is a concrete implementing class which provides the JSF exception
 * handler factory.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 * @see ExceptionHandlerFactory
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
public class JsfExceptionHandlerFactory extends ExceptionHandlerFactory {

    /**
     * This is the {@link ExceptionHandlerFactory} instance.
     *
     * @since 0.0.1
     */
    private final ExceptionHandlerFactory base;

    /**
     * This is a constructor which receives the {@link ExceptionHandlerFactory}
     * instance.
     *
     * @param base
     *            The {@link ExceptionHandlerFactory} instance
     * @since 0.0.1
     */
    public JsfExceptionHandlerFactory(final ExceptionHandlerFactory base) {
        this.base = base;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new JsfExceptionHandler(this.base.getExceptionHandler());
    }
}
