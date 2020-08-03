package by.prokhorenko.rentservice.controller.filter;

import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.command.CommandName;
import by.prokhorenko.rentservice.controller.command.CommandType;
import by.prokhorenko.rentservice.controller.command.impl.Attribute;
import by.prokhorenko.rentservice.controller.command.impl.RequestParameter;
import by.prokhorenko.rentservice.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@WebFilter(filterName = "CommandMatchingSecurityFilter")
public class CommandMatchingSecurityFilter implements Filter {

    private static final Logger LOG = LogManager.getLogger();
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String commandName = request.getParameter(RequestParameter.PARAM_COMMAND);
        CommandName command = CommandName.findCommandByName(commandName);
        HttpSession session = request.getSession();
        UserRole userRole = (UserRole) session.getAttribute(Attribute.USER_ROLE);
        Set<CommandName> commandNames;
        LOG.debug(request.getHeader("referer"));
        switch (userRole){
            case USER: commandNames = CommandType.USER.getCommandNames();
            break;
            case ADMIN: commandNames = CommandType.ADMIN.getCommandNames();
            break;
            default: commandNames = CommandType.GUEST.getCommandNames();
            break;
        }
        LOG.debug(commandNames.contains(command));
        if(!commandNames.contains(command)){
            request.getRequestDispatcher(PagePath.WRONG_REQUEST).forward(req,resp);
        }else{
            chain.doFilter(req, resp);
        }
    }

}
