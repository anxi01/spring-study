package com.example.filter.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/api/user/*") // urlPatterns에 맞는 Controller에서만 적용(특정하게 사용) + main에서 @ServletComponentScan 사용
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        // 전처리

        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(request, response);  
        // doFilter 이후 후처리
        
        String url = httpServletRequest.getRequestURI();

        /*BufferedReader br = httpServletRequest.getReader();

        br.lines().forEach(line->{
            log.info("url : {}, line : {}", url, line);
        });*/


        // 후처리
        // req
        String reqContent = new String(httpServletRequest.getContentAsByteArray());

        log.info("request url : {}, request body : {}", url, reqContent);

        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();

        httpServletResponse.copyBodyToResponse(); // 이 메소드를 사용해서 client가 body 내용을 읽을 수 있다.

        log.info("response status : {}, responseBody : {}", httpStatus, resContent);
    }
}
