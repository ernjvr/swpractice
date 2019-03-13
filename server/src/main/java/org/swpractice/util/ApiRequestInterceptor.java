package org.swpractice.util;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiRequestInterceptor implements HandlerInterceptor {

    private String requestMethod = "";
    private String requestURI = "";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        requestMethod = request.getMethod();
        requestURI= request.getRequestURI();
        return true;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestURI() {
        return requestURI;
    }
}
