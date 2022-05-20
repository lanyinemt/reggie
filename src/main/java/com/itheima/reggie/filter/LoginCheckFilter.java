package com.itheima.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.itheima.reggie.common.BaseContext;
import com.itheima.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否完成登录
 * @author yin
 * @date 2022/05/16 22:07
 **/

@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    // 路径匹配器 支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1.获取本次请求的URI
        String requestURI = request.getRequestURI();
        String[] urls = new String[]{   // 不需要处理的路径
                "/employee/login",
                "/employee/logout",
                "/backend/**",  // 静态资源
                "/front/** "
        };

        // 2.判断请求是否需要处理
        boolean check = check(urls, requestURI);

        // 3.如果不需要处理，这直接放行
        if (check){
            filterChain.doFilter(request, response);   // 放行
            return;
        }

        // 4.判读登录状态，如果已经登录，则直接放行
        if (request.getSession().getAttribute("employee") != null){
            // long id = Thread.currentThread().getId();
            // log.info("当前线程id: {}", id);  // 每次请求都会创建一个线程
            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);     // 作用域是线程内

            filterChain.doFilter(request, response);
            return;     // 注意返回
        }

        // 5.如果未登录 则返回未登录结果，通过输出流的方式
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        log.info("拦截到请求：{}", request.getRequestURI());
    }

    /**
     * 路径匹配，检查本次请求是否放行
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls ,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match){
                return true;
            }
        }
        return false;
    }
}
