package by.prokhorenko.rentservice.controller.command.impl.page;

import by.prokhorenko.rentservice.controller.command.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.Attribute;
import by.prokhorenko.rentservice.entity.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ProfilePageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setForward();
        HttpSession session = request.getSession();
        UserRole userRole = (UserRole) session.getAttribute(Attribute.USER_ROLE);
        String page = UserRole.ADMIN.equals(userRole) ? PagePath.ADMIN_PROFILE : PagePath.USER_PROFILE;
        router.setPage(page);
        return router;
    }

}
