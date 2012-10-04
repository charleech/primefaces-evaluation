package org.charleech.primefaces.eval.ui.table.lazy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import org.charleech.primefaces.eval.intrcptr.MyInterceptable;
import org.charleech.primefaces.eval.ui.table.DynamicTableBean;
import org.charleech.primefaces.eval.ui.table.MyData;
import org.charleech.primefaces.eval.ui.table.MyDetail;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

/**
 * <p>
 * This is a concrete implementing class which provides the feature for
 * demonstrating the data table with dynamic columns and lazy behavior.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 * @see DynamicTableBean
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
public class DynamicTableLazyBean extends DynamicTableBean {

    /**
     * This is a default serial version UID.
     *
     * @since 0.0.1
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is a variable which represents the selected {@link MyData}.
     *
     * @since 0.0.1
     */
    @Inject
    private MyData selectedElement;

    /**
     * This is a variable which represents the lazied data list.
     *
     * @since 0.0.1
     */
    private LazyDataModel<MyData> datasLazy;

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
        this.datasLazy = new LazyMyDataDataModel(this.createDatas());
        super.postConstruct();
    }

    @Override
    public void preDestroy() {
        this.selectedElement = null;
        this.datasLazy       = null;
        super.preDestroy();
    }

    /**
     * The listener for clicking event.
     *
     * @param event
     *            The {@link SelectEvent}
     * @since 0.0.1
     */
    public void onRowSelect(final SelectEvent event) {
        DynamicTableLazyBean.log.info(
           this.getMarker(),
           "The selected is {}",
           this.selectedElement);
    }

    /**
     * Create dummy data.
     *
     * @since 0.0.1
     */
    private List<MyData> createDatas() {
        List<MyData> datas  = null;
        MyData       data   = null;
        MyDetail     detail = null;
        String       idx    = null;
        try {
            datas = Collections.synchronizedList(new ArrayList<MyData>());
            for (int i = 1 ; i < 14 ; i++) {
                idx    = String.valueOf(i);
                data   = new MyData();
                data.setId("id-".concat(idx));
                data.setName("Name-".concat(idx));

                detail = new MyDetail();
                detail.setHomeNo("HomeNo-".concat(idx));
                detail.setMobileNo("MobileNo-".concat(idx));

                data.setDetail(detail);
                datas.add(data);
            }

            return datas;
        } finally {
            datas  = null;
            data   = null;
            detail = null;
        }
    }

}
