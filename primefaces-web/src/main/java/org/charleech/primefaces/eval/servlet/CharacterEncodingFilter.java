package org.charleech.primefaces.eval.servlet;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import lombok.extern.slf4j.Slf4j;

import org.charleech.primefaces.eval.AbstractMarker;

/**
 * <p>
 * This is a concrete implementing class which provides the feature described
 * at {@link Filter} as a character encoding filter.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 * @see AbstractMarker
 * @see Filter
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
@Singleton
@WebFilter(
   filterName   = "Character Encoding Filter",
   servletNames =  {"Faces Servlet"}
)
@Slf4j
public class CharacterEncodingFilter
     extends AbstractMarker
  implements Filter {

    /**
     * This is a default serial version UID.
     *
     * @since 0.0.1
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void destroy() {
        CharacterEncodingFilter.log.info(this.getMarker(),
                                         "The {} has been destroyed.",
                                         this.getClass().getName());
    }

    @Override
    public void doFilter(final ServletRequest  request,
                         final ServletResponse response,
                         final FilterChain     chain)
                throws IOException,
                       ServletException {
        CharacterEncodingFilter.log.info(
           this.getMarker(),
           "The HTTP request/response is being filtering.");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    @Override
    public void init(final FilterConfig arg0) throws ServletException {
        CharacterEncodingFilter.log.info(this.getMarker(),
                                         "The {} has been initiated.",
                                         this.getClass().getName());
    }

}
