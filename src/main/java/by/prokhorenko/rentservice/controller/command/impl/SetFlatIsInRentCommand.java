package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.DisPathType;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Attribute;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.CommandName;
import by.prokhorenko.rentservice.controller.command.RequestParameter;
import by.prokhorenko.rentservice.entity.Advertisement;
import by.prokhorenko.rentservice.entity.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.AdvertisementService;
import by.prokhorenko.rentservice.service.FlatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SetFlatIsInRentCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private FlatService flatService;
    private AdvertisementService advertisementService;
    public SetFlatIsInRentCommand(){
        this.flatService = ServiceFactory.getInstance().getFlatService();
        this.advertisementService = ServiceFactory.getInstance().getAdvertisementService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int flatsId = Integer.parseInt(request.getParameter(RequestParameter.FLAT_ID));
        User user = (User) session.getAttribute(Attribute.USER);
        int usersId = user.getId();
        int advertisementId = Integer.parseInt(request.getParameter(RequestParameter.ADVERTISEMENT_ID));
        Router router;
        try {
            Advertisement advertisement = advertisementService.findAdvertisementById(advertisementId);
            if(advertisement.getAuthor().getId() == usersId) {
                flatService.setFlatIsInRent(flatsId);
                String redirectUrl = buildRedirectUrl(request, CommandName.MY_ADS_PAGE.getCommandName());
                router = new Router(redirectUrl);
            }else{
                router = new Router(DisPathType.FORWARD, PagePath.WRONG_REQUEST);
            }
        } catch (ServiceException e) {
            LOG.error(e);
            router = new Router(DisPathType.FORWARD, PagePath.SERVER_ERROR_PAGE);
        }
        return router;
    }
}
