package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.DisPathType;
import by.prokhorenko.rentservice.controller.command.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Attribute;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.RequestParameter;
import by.prokhorenko.rentservice.controller.command.util.CommandUtil;
import by.prokhorenko.rentservice.entity.Advertisement;
import by.prokhorenko.rentservice.entity.User;
import by.prokhorenko.rentservice.entity.UserRole;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.AdvertisementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteAdvertisementCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private AdvertisementService advertisementService;

    public DeleteAdvertisementCommand() {
        this.advertisementService = ServiceFactory.getInstance().getAdvertisementService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router;
        User user = (User) session.getAttribute(Attribute.USER);
        UserRole userRole = (UserRole) session.getAttribute(Attribute.USER_ROLE);
        int usersId = user.getId();
        int advertisementsId = Integer.parseInt(request.getParameter(RequestParameter.ADVERTISEMENT_ID));
        try {
            Advertisement advertisement = advertisementService.findAdvertisementById(advertisementsId);
            if (advertisement.getAuthor().getId() == usersId || UserRole.ADMIN.equals(userRole)) {
                advertisementService.deleteAdvertisement(advertisementsId);
                String page = request.getHeader(CommandUtil.REFERER_HEADER);
                router = new Router(page);
            } else {
                router = new Router(DisPathType.FORWARD, PagePath.WRONG_REQUEST);
            }

        } catch (ServiceException e) {
            LOG.error(e);
            router = new Router(DisPathType.FORWARD, PagePath.SERVER_ERROR_PAGE);
        }
        return router;
    }
}
