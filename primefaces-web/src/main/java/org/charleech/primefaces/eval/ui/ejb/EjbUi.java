package org.charleech.primefaces.eval.ui.ejb;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.charleech.primefaces.eval.AbstractMarker;
import org.charleech.primefaces.eval.ejb.MyServiceable;

/**
 * <p>
 * This is an concrete implementing class which provides the demonstration for
 * the EJB/JSF integration.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 * @see AbstractMarker
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
@Named
@SessionScoped
@Slf4j
public class EjbUi extends AbstractMarker {

    /**
     * This is a default serial version UID.
     *
     * @since 0.0.1
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is a variable which represents the {@link MyServiceable}.
     *
     * @since 0.0.1
     */
    @Inject
    private MyServiceable ejbService;

    /**
     * This is a variable which represents the name.
     *
     * @since 0.0.1
     */
    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)
    private String name;

    /**
     * This is a variable which represents the result.
     *
     * @since 0.0.1
     */
    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)
    private String result;

    public void say() {
        EjbUi.log.info(this.getMarker(),
                       "The active EJB is {}.",
                       this.ejbService);
        this.result = this.ejbService.say(this.name);
    }
}
