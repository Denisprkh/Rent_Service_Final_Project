package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.entity.user.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileRedirectCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        router.setRedirect();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attribute.USER);
        if(user == null){
            router.setPage(PagePath.SIGN_IN);
        }
        if(UserRole.ADMIN.equals(user.getUserRole())){
            router.setPage(PagePath.ADMIN_PROFILE);
        }else{
            router.setPage(PagePath.USER_PROFILE);
        }
        return router;
    }
}
