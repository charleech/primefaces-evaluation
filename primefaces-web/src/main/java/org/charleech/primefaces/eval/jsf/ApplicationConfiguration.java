package org.charleech.primefaces.eval.jsf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.charleech.primefaces.eval.AbstractMarker;

/**
 * <p>
 * This is a concrete implementing class which provides the feature described
 * at {@link ApplicationConfigurable}.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 * @see AbstractMarker
 * @see ApplicationConfigurable
 */
@Data
@EqualsAndHashCode(
        callSuper       = true,
        doNotUseGetters = true
        )
@ToString(
        callSuper         = true,
        includeFieldNames = true,
        doNotUseGetters   = true
        )
@Named
@ApplicationScoped
public class ApplicationConfiguration extends AbstractMarker
                                   implements ApplicationConfigurable {

    /**
     * This is a default serial version UID.
     *
     * @since 0.0.1
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is a constant which represents the UTF-8 encoding.
     *
     * @since 0.0.1
     */
    private static final String UTF8 = "UTF-8";

    /**
     * This is a variable which represents the page encoding.
     *
     * @since 0.0.1
     */
    private String pageEncoding;

    /**
     * This is a variable which represents the HTML content type.
     *
     * @since 0.0.1
     */
    private String htmlContentType;


    /**
     * This is a variable which represents the JSF content type.
     *
     * @since 0.0.1
     */
    private String jsfContentType;

    /**
     * This is a variable which represents the available {@link Locale}.
     *
     * @since 0.0.1
     */
    private List<Locale> availableLocales;

    /**
     * This is a variable which represents the default {@link Locale}.
     *
     * @since 0.0.1
     */
    private Locale defaultLocale;

    @Override
    public void postConstruct() {
        this.pageEncoding     = ApplicationConfiguration.UTF8;
        this.htmlContentType  = "text/html; charset=UTF-8";
        this.jsfContentType   = "text/html";
        this.availableLocales = this.getLocales();
        super.postConstruct();
    }

    @Override
    public void preDestroy() {
        this.pageEncoding     = null;
        this.htmlContentType  = null;
        this.jsfContentType   = null;
        this.defaultLocale    = null;
        this.availableLocales.clear();
        this.availableLocales = null;
        super.preDestroy();
    }

    /**
     * Get all available {@link Locale} from {@link FacesContext}.
     *
     * @return The available {@link Locale} from {@link FacesContext}
     * @since 0.0.1
     */
    private List<Locale> getLocales() {
        List<Locale>     locales  = null;
        Iterator<Locale> localeIt = null;
        try {

            locales            = Collections.synchronizedList(
                                    new ArrayList<Locale>());

            this.defaultLocale = FacesContext.getCurrentInstance().
                                    getApplication().
                                       getDefaultLocale();

            locales.add(this.defaultLocale);

            localeIt           = FacesContext.getCurrentInstance().
                                    getApplication().
                                       getSupportedLocales();

            while (localeIt.hasNext()) {
                locales.add(localeIt.next());
            }

            return locales;
        } finally {
            locales  = null;
            localeIt = null;
        }
    }

}
