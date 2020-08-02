package by.prokhorenko.rentservice.controller.command.impl.page;

import by.prokhorenko.rentservice.controller.DisPathType;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import javax.servlet.http.HttpServletRequest;


public class ActivationInfoPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(DisPathType.FORWARD, PagePath.ACTIVATION_INFO);
    }
}
