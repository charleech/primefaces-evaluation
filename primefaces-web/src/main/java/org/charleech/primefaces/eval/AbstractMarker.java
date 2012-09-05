package org.charleech.primefaces.eval;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Marker;

/**
 * <p>
 * This is an abstract class which provides the feature described at
 * {@link Markable}.
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
@Slf4j
public abstract class AbstractMarker implements Markable {

    /**
     * This is a default serial version UID.
     *
     * @since 0.0.1
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is a variable which represents the {@link MarkerWrappable}.
     *
     * @since 0.0.1
     */
    @Inject
    private MarkerWrappable markerWrapper;

    @PostConstruct
    @Override
    public void postConstruct() {
        this.printPostConstruct();
    }

    @PreDestroy
    @Override
    public void preDestroy() {
        this.printPreDestroy();
    }


    @Override
    public Marker getMarker() {
        return this.markerWrapper.getMarker(this.getClass());
    }

    /**
     * Print self information when the post construct is invoked.
     *
     * @since 0.0.1
     */
    protected void printPostConstruct() {
        this.printInfo("This is a post construction as {}");
    }

    /**
     * Print self information when the pre destroy is invoked.
     *
     * @since 0.0.1
     */
    protected void printPreDestroy() {
        this.printInfo("This is a pre destroy as {}");
    }

    /**
     * Print information.
     *
     * @param template
     *            The printing message template.
     * @since 0.0.1
     */
    private void printInfo(final String template) {
        AbstractMarker.log.trace(this.getMarker(), template, this);
    }
}
