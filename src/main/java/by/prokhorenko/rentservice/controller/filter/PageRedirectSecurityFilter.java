package by.prokhorenko.rentservice.controller.filter;

import by.prokhorenko.rentservice.controller.command.RequestParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Page redirect security filter
 */
@WebFilter(filterName = "PageRedirectSecurityFilter")
public class PageRedirectSecurityFilter implements Filter {

    private static final Logger LOG = LogManager.getLogger();
    private static final String REDIRECT_URL = "/controller?command=mainPage&currentPage=1";

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (request.getParameter(RequestParameter.PARAM_COMMAND) == null) {
            response.sendRedirect(request.getContextPath() + REDIRECT_URL);
        }
        chain.doFilter(req, resp);
    }

}
