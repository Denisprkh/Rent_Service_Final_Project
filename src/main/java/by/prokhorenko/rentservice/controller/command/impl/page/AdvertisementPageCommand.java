package by.prokhorenko.rentservice.controller.command.impl.page;

import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.Attribute;
import by.prokhorenko.rentservice.controller.command.RequestParameter;
import by.prokhorenko.rentservice.entity.Advertisement;
import by.prokhorenko.rentservice.exception.DaoException;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.AdvertisementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;



public class AdvertisementPageCommand implements Command {
    private AdvertisementService advertisementService;
    private static final Logger LOG = LogManager.getLogger();
    public AdvertisementPageCommand(){
        this.advertisementService = ServiceFactory.getInstance().getAdvertisementService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setForward();
        try {
            int advertisementsId = Integer.parseInt(request.getParameter(RequestParameter.ADVERTISEMENT_ID));
            Advertisement advertisement = advertisementService.findAdvertisementById(advertisementsId);
            request.getSession().setAttribute(Attribute.ADVERTISEMENT,advertisement);
            router.setPage(PagePath.ADVERTISEMENT);
        } catch (ServiceException e) {
           LOG.error(e);
           if(e.getCause() instanceof DaoException){
               router.setPage(PagePath.SERVER_ERROR_PAGE);
           }else{
               router.setPage(PagePath.WRONG_REQUEST);
           }
        }
        return router;
    }


}
