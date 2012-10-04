package org.charleech.primefaces.eval.ui.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import org.charleech.primefaces.eval.AbstractMarker;
import org.charleech.primefaces.eval.intrcptr.MyInterceptable;
import org.primefaces.event.SelectEvent;

/**
 * <p>
 * This is a concrete implementing class which provides the feature for
 * demonstrating the data table with dynamic columns.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 * @see AbstractMarker
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
@Slf4j
@MyInterceptable
public class DynamicTableBean extends AbstractMarker {

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
     * This is a variable which represents the selected {@link MyData}.
     *
     * @since 0.0.1
     */
    @Inject
    private MyData selectedElement;

    /**
     * This is a variable which represents the data list.
     *
     * @since 0.0.1
     */
    List<MyData> datas;

    /**
     * This is a variable which represents the filterd data list.
     *
     * @since 0.0.1
     */
    List<MyData> filteredDatas;

    /**
     * This is a variable which represents the column model list.
     *
     * @since 0.0.1
     */
    List<String> columns;

    /**
     * This is the CDI initializer method.
     *
     * @since 0.0.1
     * @see <a href="http://bit.ly/OEOHLf">Bug #20</a>
     */
    @Inject
    public void initiate() {
        this.postConstruct();
    }

    @Override
    public void postConstruct() {
        this.datas = Collections.synchronizedList(new ArrayList<MyData>());
        this.createDatas();

        this.columns = Collections.synchronizedList(new ArrayList<String>());
        this.createColumns();

        super.postConstruct();
    }

    @Override
    public void preDestroy() {
        this.datas.clear();
        this.datas = null;

        this.columns.clear();
        this.columns = null;

        super.preDestroy();
    }

    /**
     * Start the conversation.
     *
     * @since 0.0.1
     */
    public void startConversation() {
        if (this.conversation.isTransient()) {
            this.conversation.setTimeout(
                    DynamicTableBean.DEFAULT_CONVERSATION_TIMEOUT);
            this.conversation.begin();
            DynamicTableBean.log.info(
               this.getMarker(),
               "The conversation id {} is started with timeout as {} ms.",
               this.conversation.getId(),
               this.conversation.getTimeout());
        } else {
            DynamicTableBean.log.warn(
               this.getMarker(),
               "The conversation is begun already with id as {}",
               this.conversation.getId());
        }
    }

    /**
     * The listener for clicking event.
     *
     * @param event
     *            The {@link SelectEvent}
     * @since 0.0.1
     */
    public void onRowSelect(final SelectEvent event) {
        DynamicTableBean.log.info(
           this.getMarker(),
           "The selected is {}",
           this.selectedElement);
    }

    /**
     * Create dummy data.
     *
     * @since 0.0.1
     */
    private void createDatas() {
        MyData   data   = null;
        MyDetail detail = null;
        String   idx    = null;
        try {
            for (int i = 1 ; i < 5 ; i++) {
                idx    = String.valueOf(i);
                data   = new MyData();
                data.setId("id-".concat(idx));
                data.setName("Name-".concat(idx));

                detail = new MyDetail();
                detail.setHomeNo("HomeNo-".concat(idx));
                detail.setMobileNo("MobileNo-".concat(idx));

                data.setDetail(detail);
                this.datas.add(data);
            }

        } finally {
            data   = null;
            detail = null;
        }
    }

    /**
     * Create column definition.
     *
     * @since 0.0.1
     */
    private void createColumns() {
        this.columns.add("id");
        this.columns.add("name");
        this.columns.add("detail.mobileNo");
        this.columns.add("detail.homeNo");
    }

}
