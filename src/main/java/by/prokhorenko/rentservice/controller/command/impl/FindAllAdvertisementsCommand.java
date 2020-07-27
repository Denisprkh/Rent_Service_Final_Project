package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.DisPathType;
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
import javax.servlet.http.HttpSession;
import java.util.List;


public class FindAllAdvertisementsCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private static final int RECORDS_PER_PAGE = 10;
    private AdvertisementService advertisementService;
    public FindAllAdvertisementsCommand(){
        this.advertisementService = ServiceFactory.getInstance().getAdvertisementService();
    }

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response){
        int currentPage = Integer.parseInt(request.getParameter(Attribute.PAGINATION_CURRENT_PAGE));
        int start = ((currentPage-1) * RECORDS_PER_PAGE);
        HttpSession session = request.getSession();
        try {
            definePaginationContext(request,advertisementService.findAdvertisementsQuantity(),currentPage,RECORDS_PER_PAGE);
            List<Advertisement> advertisementList = advertisementService.findAllAdvertisements(start,RECORDS_PER_PAGE);
            session.setAttribute(Attribute.ADVERTISEMENT_LIST,advertisementList);
            session.removeAttribute(Attribute.ADVERTISEMENT_FILTER);
        } catch (ServiceException e) {
            LOG.error(e);
        }
        return new Router(PagePath.INDEX);
    }
}
