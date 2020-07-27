package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        request.getSession().removeAttribute(Attribute.USER);
        request.getSession().removeAttribute(Attribute.USER_ROLE);
        router.setPage(PagePath.INDEX);
        return router;
    }
}
