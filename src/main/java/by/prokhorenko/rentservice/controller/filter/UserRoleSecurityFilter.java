package by.prokhorenko.rentservice.controller.filter;

import by.prokhorenko.rentservice.controller.command.impl.Attribute;
import by.prokhorenko.rentservice.entity.User;
import by.prokhorenko.rentservice.entity.UserRole;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter(filterName = "UserRoleSecurityFilter")
public class UserRoleSecurityFilter implements Filter {

    private static final Logger LOG = LogManager.getLogger();

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession();
        Object usersRole = session.getAttribute(Attribute.USER_ROLE);
        User user = (User)session.getAttribute(Attribute.USER);
        if(usersRole == null){
            session.setAttribute(Attribute.USER_ROLE, UserRole.GUEST);
        }
        if(user != null){
            String email = user.getEmail();
            UserRole userRole =(UserRole) session.getAttribute(Attribute.USER_ROLE);
            UserService userService = ServiceFactory.getInstance().getUserService();
            try {
                Optional<User> optionalUser = userService.findUserByEmail(email);
                if(optionalUser.isPresent() && !optionalUser.get().getUserRole().equals(userRole)){
                    session.setAttribute(Attribute.USER_ROLE,optionalUser.get().getUserRole());
                    LOG.debug(optionalUser.get().getUserRole());
                }
            } catch (ServiceException e) {
                LOG.error(e);
            }
        }

        chain.doFilter(req, resp);
    }

}
