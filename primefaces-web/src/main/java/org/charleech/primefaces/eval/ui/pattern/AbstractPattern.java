package org.charleech.primefaces.eval.ui.pattern;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import org.charleech.primefaces.eval.AbstractMarker;

/**
 * <p>
 * This is a base class which provides the feature described at
 * {@link Patternable}.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 * @see AbstractMarker
 * @see Patternable
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
@Slf4j
public abstract class AbstractPattern
              extends AbstractMarker
           implements Patternable {

    /**
     * This is a default serial version UID.
     *
     * @since 0.0.1
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is a constant which represents the default conversation timeout.
     *
     * @since 0.0.1
     */
    private static final long DEFAULT_CONVERSATION_TIMEOUT = 300000L;

    /**
     * This is a variable which represents the {@link Conversation}.
     *
     * @since 0.0.1
     */
    @Inject
    private Conversation conversation;

    /**
     * This is a variable which represents the flag which identifies if the
     * component should be rendered or not.
     *
     * @since 0.0.1
     */
    private boolean rendered;

    @Override
    public void myAction() {
        this.rendered = !this.rendered;
        AbstractPattern.log.info(
           this.getMarker(),
           "This is a my action for {}",
           this.getClass().getName());
    }

    /**
     * Start the conversation.
     *
     * @since 0.0.1
     */
    @Override
    public void startConversation() {
        if (this.conversation.isTransient()) {
            this.conversation.setTimeout(
                    AbstractPattern.DEFAULT_CONVERSATION_TIMEOUT);
            this.conversation.begin();
            AbstractPattern.log.info(
               this.getMarker(),
               "The conversation id {} is started with timeout as {} ms.",
               this.conversation.getId(),
               this.conversation.getTimeout());
        } else {
            AbstractPattern.log.warn(
               this.getMarker(),
               "The conversation is begun already with id as {}",
               this.conversation.getId());
        }
    }
}
