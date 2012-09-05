package org.charleech.primefaces.eval.exception;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

/**
 * <p>
 * This is a concrete implementing class which provides the JSF exception
 * handler.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 * @see ExceptionHandlerWrapper
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
public class JsfExceptionHandler extends ExceptionHandlerWrapper {

    /**
     * This is the {@link ExceptionHandler} instance.
     *
     * @since 0.0.1
     */
    private final ExceptionHandler wrapped;

    /**
     * This is a constructor which receives the {@link ExceptionHandler} for
     * further managing.
     *
     * @param wrapped
     *            The {@link ExceptionHandler} instance.
     * @since 0.0.1
     */
    public JsfExceptionHandler(final ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
        final Iterable<ExceptionQueuedEvent> events =
                this.wrapped.getUnhandledExceptionQueuedEvents();
        for (final Iterator<ExceptionQueuedEvent> it = events.iterator(); it
                .hasNext();) {
            final ExceptionQueuedEvent event = it.next();
            final ExceptionQueuedEventContext eqec = event.getContext();

            if (eqec.getException() instanceof ViewExpiredException) {
                final FacesContext context = eqec.getContext();
                final NavigationHandler navHandler =
                        context.getApplication().getNavigationHandler();

                try {
                    navHandler.handleNavigation(context, null,
                            "index?faces-redirect=true&expired=true");
                } finally {
                    it.remove();
                }
            } else {
                eqec.getException().printStackTrace();
            }
        }

        this.wrapped.handle();;
    }
}
