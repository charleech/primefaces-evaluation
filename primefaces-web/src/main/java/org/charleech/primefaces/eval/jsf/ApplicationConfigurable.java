package org.charleech.primefaces.eval.jsf;

import java.util.List;
import java.util.Locale;

import org.charleech.primefaces.eval.Markable;

/**
 * <p>
 * This is an interface which provides the feature for configuring the JSF as
 * an application scope.
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
public interface ApplicationConfigurable extends Markable {
    /**
     * Get page encoding.
     *
     * @return The page encoding
     * @since 0.0.1
     */
    String getPageEncoding();

    /**
     * Get the HTML content type.
     *
     * @return The HTML content type
     * @since 0.0.1
     */
    String getHtmlContentType();

    /**
     * Get available {@link Locale} list.
     *
     * @return The available {@link Locale} list
     * @since 0.0.1
     */
    List<Locale> getAvailableLocales();

    /**
     * Get default {@link Locale}.
     *
     * @return The default {@link Locale}
     * @since 0.0.1
     */
    Locale getDefaultLocale();
}
