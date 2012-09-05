package org.charleech.primefaces.eval.jsf;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * <p>
 * This is a concrete implementing class which provides the primefaces theme
 * setting as a CDI managed bean.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 */
@Named
@SessionScoped
public class GuestPreferences implements Serializable {

    /**
     * This is a default serial version UID.
     *
     * @since 0.0.1
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is a variable which represents the selected theme.
     *
     * @since 0.0.1
     */
    private String theme;

    /**
     * This is a CDI post construction.
     *
     * @since 0.0.1
     */
    @PostConstruct
    public void postConstruct() {
        this.theme = "aristo";
    }

    /**
     * This is a CDI pre destruction.
     *
     * @since 0.0.1
     */
    @PreDestroy
    public void preDestroy() {
        this.theme = null;
    }

    /**
     * Get the current theme.
     *
     * @return The current theme
     * @since 0.0.1
     */
    public String getTheme() {
        final Map<String, String> params = FacesContext.
                                        getCurrentInstance().
                                           getExternalContext().
                                              getRequestParameterMap();
        if(params.containsKey("theme")) {
            this.theme = params.get("theme");
        }

        return this.theme;
    }

    /**
     * Set the current theme.
     *
     * @param theme
     *            The setting theme
     * @since 0.0.1
     */
    public void setTheme(final String theme) {
        this.theme = theme;
    }
}
