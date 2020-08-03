package by.prokhorenko.rentservice.controller.command.impl.page;

import by.prokhorenko.rentservice.controller.DisPathType;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.impl.Attribute;
import by.prokhorenko.rentservice.controller.command.util.CommandUtil;
import by.prokhorenko.rentservice.entity.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllUsersPageCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private UserService userService;
    public AllUsersPageCommand(){
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        int start = CommandUtil.defineStartOfRecords(request);

        try {
            CommandUtil.definePaginationContext(request,userService.findUsersQuantity());
            List<User> allUsers = userService.findAllUsers(start,CommandUtil.RECORDS_PER_PAGE);
            request.setAttribute(Attribute.ADMIN_ALL_USERS_LIST,allUsers);
        } catch (ServiceException e) {
            LOG.error(e);
        }
        return new Router(DisPathType.FORWARD,PagePath.ALL_USERS);
    }
}
