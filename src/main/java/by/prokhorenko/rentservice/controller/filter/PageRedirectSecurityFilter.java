package by.prokhorenko.rentservice.controller.filter;

import by.prokhorenko.rentservice.controller.command.CommandName;
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

    private static final Logger LOG = LogManager.getLogger();

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if(request.getParameter(RequestParameter.PARAM_COMMAND) == null){
            response.sendRedirect(request.getContextPath()+request.getServletPath()+"?"+RequestParameter.PARAM_COMMAND
            + "=" + CommandName.MAIN_PAGE);
        }
        chain.doFilter(req, resp);
    }

}
