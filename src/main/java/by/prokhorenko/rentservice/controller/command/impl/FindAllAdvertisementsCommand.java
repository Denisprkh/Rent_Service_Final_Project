package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.advertisement.AdvertisementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class FindAllAdvertisementsCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private AdvertisementService advertisementService;
    public FindAllAdvertisementsCommand(){
        this.advertisementService = ServiceFactory.getInstance().getAdvertisementService();
    }

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response){
        Router router = new Router();
        int currentPage = Integer.parseInt(request.getParameter(Attribute.PAGINATION_CURRENT_PAGE));
        int recordsPerPage = 5;
        int start = 1;
        if(currentPage != 1){
            start = currentPage * currentPage +2;
        }
        try {
            definePaginationContext(request,advertisementService.findAdvertisementsQuantity(),currentPage,recordsPerPage);
            List<Advertisement> advertisementList = advertisementService.findAllAdvertisements(start,recordsPerPage);
            LOG.debug(advertisementList);
            request.setAttribute(Attribute.ADVERTISEMENT_LIST,advertisementList);
            router.setPage(PagePath.INDEX);
            router.setForward();
        } catch (ServiceException e) {
            LOG.error(e);
        }
        return router;
    }
}
