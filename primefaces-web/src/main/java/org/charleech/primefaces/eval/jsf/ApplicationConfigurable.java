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
