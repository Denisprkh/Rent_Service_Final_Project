package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.DisPathType;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.util.CommandUtil;
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
    private AdvertisementService advertisementService;
    public FindAllAdvertisementsCommand(){
        this.advertisementService = ServiceFactory.getInstance().getAdvertisementService();
    }

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response){
        int start = CommandUtil.defineStartOfRecords(request);
        HttpSession session = request.getSession();
        try {
            CommandUtil.definePaginationContext(request,advertisementService.findAdvertisementsQuantity());
            List<Advertisement> advertisementList = advertisementService.findAllAdvertisements(start,CommandUtil.
                    RECORDS_PER_PAGE);
            session.setAttribute(Attribute.ADVERTISEMENT_LIST,advertisementList);
            session.removeAttribute(Attribute.ADVERTISEMENT_FILTER);
        } catch (ServiceException e) {
            LOG.error(e);
        }
        return new Router(DisPathType.FORWARD,PagePath.INDEX);
    }
}
