package by.prokhorenko.rentservice.controller.command.impl.page;

import by.prokhorenko.rentservice.controller.DisPathType;
import by.prokhorenko.rentservice.controller.command.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.Attribute;
import by.prokhorenko.rentservice.controller.command.util.CommandUtil;
import by.prokhorenko.rentservice.entity.Advertisement;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.AdvertisementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MainPageCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private AdvertisementService advertisementService;

    public MainPageCommand() {
        this.advertisementService = ServiceFactory.getInstance().getAdvertisementService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        int start = CommandUtil.defineStartOfRecords(request);
        HttpSession session = request.getSession();
        String page;
        try {
            CommandUtil.definePaginationContext(request, advertisementService.findNotRentedAdvertisementsQuantity());
            List<Advertisement> advertisementList = advertisementService.findAllNotRentedAdvertisements(start,
                    CommandUtil.RECORDS_PER_PAGE);
            session.setAttribute(Attribute.ADVERTISEMENT_LIST, advertisementList);
            session.removeAttribute(Attribute.ADVERTISEMENT_FILTER);
            page = PagePath.MAIN;
        } catch (ServiceException e) {
            LOG.error(e);
            page = PagePath.SERVER_ERROR_PAGE;
        }
        return new Router(DisPathType.FORWARD, page);
    }
}
