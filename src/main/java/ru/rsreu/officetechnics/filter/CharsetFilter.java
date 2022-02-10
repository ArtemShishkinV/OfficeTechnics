package ru.rsreu.officetechnics.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        next.doFilter(request, response);
    }
}