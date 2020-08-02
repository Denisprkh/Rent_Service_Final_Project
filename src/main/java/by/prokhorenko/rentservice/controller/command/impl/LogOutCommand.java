package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.CommandName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Attribute.USER);
        session.removeAttribute(Attribute.USER_ROLE);
        String redirectUrl = buildRedirectUrl(request, CommandName.MAIN_PAGE.getCommandName());
        return new Router(redirectUrl);
    }
}
