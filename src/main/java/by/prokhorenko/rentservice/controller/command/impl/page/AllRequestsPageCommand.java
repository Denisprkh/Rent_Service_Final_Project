package by.prokhorenko.rentservice.controller.command.impl.page;

import by.prokhorenko.rentservice.controller.DisPathType;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.impl.Attribute;
import by.prokhorenko.rentservice.controller.command.util.CommandUtil;
import by.prokhorenko.rentservice.entity.Request;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.request.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllRequestsPageCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();
    private RequestService requestService;
    public AllRequestsPageCommand(){
        this.requestService = ServiceFactory.getInstance().getRequestService();
    }

    @Override
    public Router execute(HttpServletRequest request) {
        int start = CommandUtil.defineStartOfRecords(request);
        try {
            CommandUtil.definePaginationContext(request,requestService.findAllRequestsQuantity());
            List<Request> allRequests = requestService.findAllRequests(start,CommandUtil.RECORDS_PER_PAGE);
            request.setAttribute(Attribute.ADMIN_ALL_REQUESTS_LIST,allRequests);
        } catch (ServiceException e) {
            LOG.error(e);
        }
        return new Router(DisPathType.FORWARD,PagePath.ALL_REQUESTS_PAGE);
    }
}
