package org.charleech.primefaces.eval.ui.pattern;

import org.charleech.primefaces.eval.Markable;

/**
 * <p>
 * This is an interface which provides the feature for demonstarating the
 * JSF/Facelet template.
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
public interface Patternable extends Markable {

    /**
     * Get the flag which identifies if the component should be rendered or not.
     *
     * @since 0.0.1
     */
    boolean isRendered();

    /**
     * Get the backing bean unique name as a prefix.
     *
     * @return The backing bean unique name as a prefix
     * @since 0.0.1
     */
    String getPrefix();

    /**
     * This is a dummy action.
     *
     * @since 0.0.1
     */
    void myAction();

    /**
     * Start the JSF conversation.
     *
     * @since 0.0.1
     */
    void startConversation();
}
