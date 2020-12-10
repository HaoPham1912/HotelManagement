package com.h2.hotelmangement.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class FormatResponseDataInterceptor implements HandlerInterceptor {
    //   @Override
//   public boolean preHandle(
//      HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//      return true;
//   }
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        System.out.println("Post Handle method is Calling");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {
        System.out.println("afterCompletion Handle method is Calling");
    }
}
