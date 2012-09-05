package org.charleech.primefaces.eval.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.inject.Singleton;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
   filterName   = "Debugging Filter",
   urlPatterns  = "/*"
)
@Slf4j
public class DebuggingFilter
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
        DebuggingFilter.log.info(this.getMarker(),
                                         "The {} has been destroyed.",
                                         this.getClass().getName());
    }

    @Override
    public void doFilter(final ServletRequest  request,
                         final ServletResponse response,
                         final FilterChain     chain)
                throws IOException,
                       ServletException {
        DebuggingFilter.log.info(
           this.getMarker(),
           "The HTTP debugging is being filtered.");

        this.printInfo((HttpServletRequest) request);

        chain.doFilter(request, response);
    }

    @Override
    public void init(final FilterConfig arg0) throws ServletException {
        DebuggingFilter.log.info(this.getMarker(),
                                         "The {} has been initiated.",
                                         this.getClass().getName());
    }

    @SuppressWarnings("rawtypes")
    private void printInfo(final HttpServletRequest request) {
        StringBuilder builder = null;
        Enumeration   myEnum  = null;
        Cookie[]      cookies = null;
        HttpSession   session = null;
        String        key     = null;
        String        value   = null;
        try {

            if (!DebuggingFilter.log.isTraceEnabled()) {
                DebuggingFilter.log.info(this.getMarker(),
                                         "The debugging is not enabled.");
                return;
            }
            builder = new StringBuilder();

            builder.append("-------------------------\r\n");
            builder.append("The session\r\n");
            session = request.getSession();
            builder.append(session.getId()).append("\r\n").
                    append(session.getCreationTime()).append("\r\n").
                    append(session.getLastAccessedTime()).append("\r\n").
                    append(session.getMaxInactiveInterval()).append("\r\n");

            myEnum = session.getAttributeNames();
            while (myEnum.hasMoreElements()) {
                key   = (String) myEnum.nextElement();
                value = session.getAttribute(key).getClass().getName();
                builder.append(key).
                        append(":").
                        append(value).
                        append("\r\n");
            }

            builder.append("-------------------------\r\n");
            builder.append("The request header\r\n");

            myEnum = request.getHeaderNames();
            while (myEnum.hasMoreElements()) {
                key   = (String) myEnum.nextElement();
                value = request.getHeader(key);
                builder.append(key).
                        append(":").
                        append(value).
                        append("\r\n");
            }

            builder.append("-------------------------\r\n");
            builder.append("The request cookies\r\n");

            cookies = request.getCookies();
            if (cookies != null) {
                for (final Cookie cookie : cookies) {
                    builder.append(cookie.getName()).
                            append(":").
                            append(cookie.getValue()).
                            append(":").
                            append(cookie.getPath()).
                            append(":").
                            append(cookie.getDomain()).
                            append("\r\n");
                }
            }
            builder.append("-------------------------\r\n");
            builder.append("The request attributes\r\n");

            myEnum = request.getAttributeNames();
            while (myEnum.hasMoreElements()) {
                key   = (String) myEnum.nextElement();
                value = request.getAttribute(key).getClass().getName();
                builder.append(key).
                        append(":").
                        append(value).
                        append("\r\n");
            }

            builder.append("-------------------------\r\n");
            builder.append("The request parameters\r\n");

            myEnum = request.getParameterNames();
            while (myEnum.hasMoreElements()) {
                key   = (String) myEnum.nextElement();
                value = request.getParameter(key);
                builder.append(key).
                        append(":").
                        append(value).
                        append("\r\n");
            }

            builder.append("-------------------------\r\n");
            builder.append("The request information\r\n");
            builder.append("Context path: ").
                       append(request.getContextPath()).
                       append("\r\n").
                    append("Method: ").
                       append(request.getMethod()).
                       append("\r\n").
                    append("Path Info: ").
                       append(request.getPathInfo()).
                       append("\r\n").
                    append("Path Transalate: ").
                       append(request.getPathTranslated()).
                       append("\r\n").
                    append("Protocol: ").
                       append(request.getProtocol()).
                       append("\r\n").
                    append("Query String: ").
                       append(request.getQueryString()).
                       append("\r\n").
                    append("Request Session Id: ").
                       append(request.getRequestedSessionId()).
                       append("\r\n").
                    append("URI: ").
                       append(request.getRequestURI()).
                       append("\r\n").
                    append("URL: ").
                       append(request.getRequestURL()).
                       append("\r\n").
                    append("Scheme: ").
                       append(request.getScheme()).
                       append("\r\n").
                    append("Servlet Path: ").
                       append(request.getServletPath()).
                       append("\r\n");


            DebuggingFilter.log.info(
                this.getMarker(),
                "The request information is\r\n{}",
                builder.toString());
        } finally {
            builder = null;
            myEnum  = null;
            cookies = null;
            session = null;
            key     = null;
            value   = null;
        }
    }

}
