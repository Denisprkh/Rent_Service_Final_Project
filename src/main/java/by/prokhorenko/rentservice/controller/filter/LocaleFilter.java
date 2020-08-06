package by.prokhorenko.rentservice.controller.filter;

import by.prokhorenko.rentservice.controller.command.Attribute;
import by.prokhorenko.rentservice.controller.command.RequestParameter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LocaleFilter")
public class LocaleFilter implements Filter {
    private static final String DEFAULT_LOCALE = "en";

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        setLocalisation((HttpServletRequest) req);
        chain.doFilter(req, resp);
    }

    private void setLocalisation(HttpServletRequest request){
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
