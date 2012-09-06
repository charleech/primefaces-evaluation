package org.charleech.primefaces.eval.ui.table.lazy;

import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;
import org.charleech.primefaces.eval.ui.table.MyData;
import org.primefaces.model.SortOrder;

/**
 * <p>
 * This is a concrete implementing class which provides the feature described
 * {@link Comparator} as a specific for {@link MyData}.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 * @see Comparator
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
public class LazySorter implements Comparator<MyData> {

    /**
     * This is a variable which represents the sorting field.
     *
     *  @since 0.0.1
     */
    private final String sortField;

    /**
     * This is a variable which represents the sorting order.
     *
     * @since 0.01
     */
    private final SortOrder sortOrder;

    /**
     * This is a constructor which receives the sorting field and order.
     *
     * @param sortField
     *            The sorting field
     * @param sortOrder
     *            The sorting order
     * @since 0.0.1
     */
    public LazySorter(final String sortField, final SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @SuppressWarnings({
            "rawtypes",
            "unchecked"
    })
    @Override
    public int compare(final MyData data1, final MyData data2) {
        try {
            final Object value1 = PropertyUtils.
                                     getNestedProperty(data1,
                                                       this.sortField);
            final Object value2 = PropertyUtils.
                                     getNestedProperty(data2,
                                                       this.sortField);

            final int value = ((Comparable)value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(this.sortOrder) ?
                      value : -1 * value;
        }
        catch(final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
