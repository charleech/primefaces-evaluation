package org.charleech.primefaces.eval;

import java.io.Serializable;

import org.slf4j.Marker;

/**
 * <p>
 * This is an interface which provides the feature for getting the SLF4J
 * {@link Marker} as a utility method for any derived classes.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
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
public interface Markable extends Serializable {
    /**
     * Get the {@link Marker} for this class.
     *
     * @return The {@link Marker} for this class.
     */
    Marker getMarker();

    /**
     * This is a CDI post construction.
     *
     * @since 0.0.1
     */
    void postConstruct();

    /**
     * This is a CDI pre destruction.
     *
     * @since 0.0.1
     */
    void preDestroy();
}
