package by.prokhorenko.rentservice.controller.filter;

import by.prokhorenko.rentservice.controller.command.impl.RequestParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "PageRedirectSecurityFilter")
public class PageRedirectSecurityFilter implements Filter {

    private static final String DEFAULT_REDIRECT_URL = "/controller?command=FIND_ALL_ADVERTISEMENTS&currentPage=1";
    private static final Logger LOG = LogManager.getLogger();

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        LOG.debug(request.getParameter(RequestParameter.PARAM_COMMAND));
        LOG.debug(request.getContextPath());
        if(request.getParameter(RequestParameter.PARAM_COMMAND) == null){
            response.sendRedirect(request.getContextPath()+DEFAULT_REDIRECT_URL);
        }
        chain.doFilter(req, resp);
    }

}
