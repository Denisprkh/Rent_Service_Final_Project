package by.prokhorenko.rentservice.controller.filter;

import by.prokhorenko.rentservice.controller.command.Attribute;
import by.prokhorenko.rentservice.entity.User;
import by.prokhorenko.rentservice.entity.UserRole;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * User block filter
 */
@WebFilter(filterName = "UserBlockFilter")
public class UserBlockFilter implements Filter {

    private static final Logger LOG = LogManager.getLogger();

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attribute.USER);
        if (user != null) {
            String email = user.getEmail();
            try {
                UserService userService = ServiceFactory.getInstance().getUserService();
                Optional<User> optionalUser = userService.findUserByEmail(email);
                if (optionalUser.isEmpty() || optionalUser.get().isBanned()) {
                    session.setAttribute(Attribute.USER_ROLE, UserRole.GUEST);
                    session.removeAttribute(Attribute.USER);

                }
            } catch (ServiceException e) {
                LOG.error(e);
            }
        }
        chain.doFilter(req, resp);
    }

}
