package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.dao.RequestDao;
import by.prokhorenko.rentservice.entity.request.Request;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.request.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

public class ApproveRequestCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private RequestService requestService;
    public ApproveRequestCommand(){
        this.requestService = ServiceFactory.getInstance().getRequestService();
    }

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        int requestsId = Integer.parseInt(request.getParameter(RequestParameter.REQUEST_ID));
        try {
            requestService.ApproveRequestById(requestsId);
        } catch (ServiceException e) {
            LOG.error(e);
        }
        return null;
    }
}
