package by.prokhorenko.rentservice.controller.filter;

import by.prokhorenko.rentservice.controller.command.impl.Attribute;
import by.prokhorenko.rentservice.entity.user.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserRoleSecurityFilter")
public class UserRoleSecurityFilter implements Filter {

    private static final Logger LOG = LogManager.getLogger();

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession();
        Object usersRole = session.getAttribute(Attribute.USER_ROLE);
        if(usersRole == null){
            session.setAttribute(Attribute.USER_ROLE, UserRole.GUEST);
        }
        LOG.debug(session.getAttribute(Attribute.USER_ROLE));
        chain.doFilter(req, resp);
    }

}
