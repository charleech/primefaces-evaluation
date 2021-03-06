package org.charleech.primefaces.eval.el;

import java.util.Collection;
import java.util.Map;
import java.util.ResourceBundle;

import javax.el.BeanELResolver;
import javax.el.ELContext;
import javax.el.ELException;
import javax.el.PropertyNotFoundException;
import org.primefaces.application.PrimeResourceHandler;
import org.primefaces.extensions.application.PrimeFacesExtensionsResourceHandler;

/**
 * <p>
 * This is a concrete implementing class which provides the extended feature
 * described at {@link BeanELResolver}.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 * @see BeanELResolver
 * @see <a href="http://bit.ly/UqZ18o">StackOverFlow</a>
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
public class ExtendedBeanELResolver extends BeanELResolver {

    @Override
    public Object getValue(final ELContext context,
                           final Object    base,
                           final Object    property)
                  throws NullPointerException,
                         PropertyNotFoundException,
                         ELException {
        boolean isInValid      = false;
        String  propertyString = null;
        Object  value          = null;
        try {
            isInValid = (property == null)
                     || (base == null)
                     || (base instanceof ResourceBundle)
                     || (base instanceof Map)
                     || (base instanceof Collection)
                     || (base instanceof PrimeResourceHandler)
                     || (base instanceof PrimeFacesExtensionsResourceHandler);
            if (isInValid) {
                return null;
            }

            propertyString = property.toString();

            if (propertyString.contains(".")) {
                value = base;

                for (final String propertyPart : propertyString.split("\\.")) {
                    value = super.getValue(context, value, propertyPart);
                }

                return value;
            } else {
                return super.getValue(context, base, property);
            }
        } finally {
            propertyString = null;
            value          = null;
        }

    }

}
