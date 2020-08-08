package by.prokhorenko.rentservice.controller.filter;

import by.prokhorenko.rentservice.controller.command.PagePath;
import by.prokhorenko.rentservice.controller.command.CommandName;
import by.prokhorenko.rentservice.controller.command.CommandType;
import by.prokhorenko.rentservice.controller.command.Attribute;
import by.prokhorenko.rentservice.controller.command.RequestParameter;
import by.prokhorenko.rentservice.entity.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * Command matching security filter
 */
@WebFilter(filterName = "CommandMatchingSecurityFilter")
public class CommandMatchingSecurityFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String commandName = request.getParameter(RequestParameter.PARAM_COMMAND);
        CommandName command = CommandName.findCommandByName(commandName);
        HttpSession session = request.getSession();
        UserRole userRole = (UserRole) session.getAttribute(Attribute.USER_ROLE);
        Set<CommandName> commandNames;
        switch (userRole) {
            case USER:
                commandNames = CommandType.USER.getCommandNames();
                break;
            case ADMIN:
                commandNames = CommandType.ADMIN.getCommandNames();
                break;
            default:
                commandNames = CommandType.GUEST.getCommandNames();
                break;
        }
        if (!commandNames.contains(command)) {
            request.getRequestDispatcher(PagePath.WRONG_REQUEST).forward(req, resp);
        } else {
            chain.doFilter(req, resp);
        }
    }

}
