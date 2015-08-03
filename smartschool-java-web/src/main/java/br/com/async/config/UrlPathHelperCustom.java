package br.com.async.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.UrlPathHelper;

/**
 * Created by rondymesquita on Aug 2, 2015
 *
 */
public class UrlPathHelperCustom extends UrlPathHelper {

    public UrlPathHelperCustom() {
        super.setUrlDecode(false);
    }

    @Override
    public void setUrlDecode(boolean urlDecode) {
        if (urlDecode) {
            throw new IllegalArgumentException("Handler does not support URL decoding.");
        }
    }

    @Override
    public String getServletPath(HttpServletRequest request) {
        String servletPath = getOriginatingServletPath(request);
        return servletPath;
    }


    @Override
    public String getOriginatingServletPath(HttpServletRequest request) {
        String servletPath = request.getRequestURI().substring(request.getContextPath().length());
        return servletPath;
    }
}