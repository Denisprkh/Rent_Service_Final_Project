package by.prokhorenko.rentservice.controller.command.impl.page;

import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return new Router(PagePath.INDEX);
    }
}
