package org.charleech.primefaces.eval.jsf;

import java.util.Locale;

import org.charleech.primefaces.eval.Markable;

/**
 * <p>
 * This is an interface which provides the feature for configuring the JSF as
 * an session scope.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 * @see Markable
 */
public interface PageConfigurable extends Markable {
    /**
     * Get the current selected {@link Locale}.
     *
     * @return The current selected {@link Locale}
     * @since 0.0.1
     */
    Locale getCurrentLocale();

    /**
     * Set the current {@link Locale} to the specifying.
     *
     * @param settingLocale
     *            The setting {@link Locale}
     * @since 0.0.1
     */
    void setCurrentLocale(final Locale settingLocale);
}
