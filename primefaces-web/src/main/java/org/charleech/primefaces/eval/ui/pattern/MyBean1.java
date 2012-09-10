package org.charleech.primefaces.eval.ui.pattern;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * This is a concrete implementing class which provides the extended feature
 * described at {@link AbstractPattern} as a specific for bean 1.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 * @see AbstractPattern
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
@ConversationScoped
public class MyBean1 extends AbstractPattern {

    /**
     * This is a default serial version UID.
     *
     * @since 0.0.1
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is a variable which represents the backing bean unique name as a
     * prefix.
     *
     * @since 0.0.1
     */
    private String prefix;

    @Override
    public void postConstruct() {
        this.prefix = "MyBean1";
        this.setRendered(true);
        super.postConstruct();
    }

    @Override
    public void preDestroy() {
        this.prefix = "null";
        super.preDestroy();
    }

}
