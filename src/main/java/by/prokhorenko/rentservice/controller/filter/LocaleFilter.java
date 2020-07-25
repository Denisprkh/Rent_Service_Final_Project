package by.prokhorenko.rentservice.controller.filter;

import by.prokhorenko.rentservice.controller.command.impl.Attribute;
import by.prokhorenko.rentservice.controller.command.impl.RequestParameter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LocaleFilter")
public class LocaleFilter implements Filter {
    private static final String DEFAULT_LOCALE = "en";

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        setLocalisation((HttpServletRequest) req, (HttpServletResponse) resp);
        chain.doFilter(req, resp);
    }

    private void setLocalisation(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Object languageAttribute = session.getAttribute(Attribute.LANGUAGE);
        if(languageAttribute == null){
            session.setAttribute(Attribute.LANGUAGE,DEFAULT_LOCALE);
        }
        String languageParameter = request.getParameter(RequestParameter.PARAM_LANGUAGE);
        if(languageParameter != null){
            session.setAttribute(Attribute.LANGUAGE,languageParameter);
        }
    }
}