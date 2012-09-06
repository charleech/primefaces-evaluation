package org.charleech.primefaces.eval.ui.table.lazy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.charleech.primefaces.eval.ui.table.MyData;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * <p>
 * This is a concrete implementing class which provides the feature described
 * {@link LazyDataModel} as a specific for {@link MyData}.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 * @see LazyDataModel
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
public class LazyMyDataDataModel extends LazyDataModel<MyData> {
    /**
     * This is a default serial version UID.
     *
     * @since 0.0.1
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is a variable which represents the datasource.
     *
     * @since 0.0.1
     */
    private final List<MyData> datasource;

    /**
     * This is a constructor which receives the external datasource.
     *
     * @param datasource
     *            The setting datasource
     * @since 0.0.1
     */
    public LazyMyDataDataModel(final List<MyData> datasource) {
        this.datasource = datasource;
    }

    @Override
    public MyData getRowData(final String rowKey) {
        for(final MyData data : this.datasource) {
            if(data.getId().equals(rowKey)) {
                return data;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(final MyData data) {
        return data.getId();
    }

    @Override
    public List<MyData> load(final int                first,
                             final int                pageSize,
                             final String             sortField,
                             final SortOrder          sortOrder,
                             final Map<String,String> filters) {
        List<MyData> datas    = null;
        boolean      match    = false;
        int          dataSize = -1;
        try {
            datas = Collections.synchronizedList(new ArrayList<MyData>());
            for (final MyData data : this.datasource) {
                match = this.determine(data, filters);
                if (match) {
                    datas.add(data);
                }
            }

            //sort
            if(sortField != null) {
                Collections.sort(datas, new LazySorter(sortField, sortOrder));
            }

            //rowCount
            dataSize = datas.size();
            this.setRowCount(datas.size());

            //paginate
            if(dataSize > pageSize) {
                try {
                    return datas.subList(first, first + pageSize);
                }
                catch(final IndexOutOfBoundsException e) {
                    return datas.subList(first, first + (dataSize % pageSize));
                }
            }
            else {
                return datas;
            }

        } finally {
            datas = null;
        }
    }

    /**
     * Determine the specified data if it is matched with the filer or not.
     *
     * @param data
     *            The determining data
     * @param filters
     *            The filtering
     * @return The matched result
     * @since 0.0.1
     */
    private boolean determine(final MyData              data,
                              final Map<String, String> filters) {
        boolean match       = false;
        String  filterValue = null;
        String  fieldValue  = null;
        try {

            match = true;

            for (final String filterProperty : filters.keySet()) {
                try {
                    filterValue = filters.get(filterProperty);
                    fieldValue  = this.getFieldValue(data, filterProperty);

                    if((filterValue == null) || fieldValue.
                                                 startsWith(filterValue)) {
                        match = true;
                    }
                    else {
                        match = false;
                        break;
                    }
                } catch(final Exception e) {
                    match = false;
                }
            }

            return match;
        } finally {
            filterValue = null;
            fieldValue  = null;
        }
    }

    /**
     * Get the data field value by using the specified property.
     *
     * @param data
     *            The source data
     * @param property
     *            The getting property
     * @return The property value matched by the specified property
     * @throws IllegalArgumentException
     *             If there is any error.
     * @throws IllegalAccessException
     *             If there is any error.
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @since 0.0.1
     */
    private String getFieldValue(final MyData data,
                                 final String property)
                                 throws IllegalArgumentException,
                                        IllegalAccessException,
                                        InvocationTargetException,
                                        NoSuchMethodException {
        return String.valueOf(PropertyUtils.getNestedProperty(data, property));
    }
}
