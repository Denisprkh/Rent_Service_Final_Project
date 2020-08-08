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
import java.util.List;

public class AllAdvertisementsPageCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private AdvertisementService advertisementService;
    public AllAdvertisementsPageCommand(){
        this.advertisementService = ServiceFactory.getInstance().getAdvertisementService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        int start = CommandUtil.defineStartOfRecords(request);
        String page;
        try {
            CommandUtil.definePaginationContext(request,advertisementService.findAdvertisementsQuantity());
            List<Advertisement> allAdvertisementList = advertisementService.findAllAdvertisements(start,
                    CommandUtil.RECORDS_PER_PAGE);
            request.setAttribute(Attribute.ADMIN_ALL_ADVERTISEMENTS_LIST,allAdvertisementList);
            page = PagePath.ALL_ADVERTISEMENTS_PAGE;
        } catch (ServiceException e) {
            LOG.error(e);
            page = PagePath.SERVER_ERROR_PAGE;
        }
        return new Router(DisPathType.FORWARD,page);
    }
}
