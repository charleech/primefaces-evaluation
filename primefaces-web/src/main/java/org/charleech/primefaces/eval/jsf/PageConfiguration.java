package org.charleech.primefaces.eval.jsf;

import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.charleech.primefaces.eval.AbstractMarker;

/**
 * <p>
 * This is a concrete implementing class which provides the feature described
 * at {@link PageConfigurable}.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 * @see AbstractMarker
 * @see PageConfigurable
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
@SessionScoped
public class PageConfiguration extends AbstractMarker
                            implements PageConfigurable {

    /**
     * This is a default serial version UID.
     *
     * @since 0.0.1
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is a variable which represents the {@link ApplicationConfigurable}.
     *
     * @since 0.0.1
     */
    @Inject
    private ApplicationConfigurable applicationConfiguration;

    /**
     * This is a variable which represents the current selected {@link Locale}.
     *
     * @since 0.0.1
     */
    private Locale currentLocale;

    @Override
    public void postConstruct() {
        this.currentLocale = this.applicationConfiguration.getDefaultLocale();
        super.postConstruct();
    }

    @Override
    public void preDestroy() {
        this.currentLocale = null;
        super.preDestroy();
    }

}
