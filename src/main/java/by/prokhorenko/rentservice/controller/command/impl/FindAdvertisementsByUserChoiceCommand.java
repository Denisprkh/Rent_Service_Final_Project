package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.builder.UserChoiceDataHandlerBuilder;
import by.prokhorenko.rentservice.controller.DisPathType;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.util.CommandUtil;
import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.advertisement.UserChoiceDataHandler;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.advertisement.AdvertisementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindAdvertisementsByUserChoiceCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private AdvertisementService advertisementService;

    public FindAdvertisementsByUserChoiceCommand() {
        this.advertisementService = ServiceFactory.getInstance().getAdvertisementService();
    }

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String newSearchFlag = request.getParameter(RequestParameter.NEW_FILTER_SEARCH_FLAG);
        UserChoiceDataHandler userChoiceDataHandler = buildDataHandlerFromRequest(request);
        int start = CommandUtil.defineStartOfRecords(request);
        HttpSession session = request.getSession();
        try {
            CommandUtil.definePaginationContext(request,
                    advertisementService.findFilteredAdvertisementsQuantity(userChoiceDataHandler));
            UserChoiceDataHandler previousHandler = (UserChoiceDataHandler) session.
                    getAttribute(Attribute.ADVERTISEMENT_FILTER);
            UserChoiceDataHandler handlerForSearch;
            if (previousHandler == null || newSearchFlag != null) {
                session.setAttribute(Attribute.ADVERTISEMENT_FILTER, userChoiceDataHandler);
                handlerForSearch = userChoiceDataHandler;
            }else{
                handlerForSearch = previousHandler;
            }
            List<Advertisement> advertisementList = advertisementService.findAdvertisementsByUsersChoice
                    (handlerForSearch, start, CommandUtil.RECORDS_PER_PAGE);
            session.setAttribute(Attribute.ADVERTISEMENT_LIST, advertisementList);
        } catch (ServiceException e) {
            LOG.error(e.getCause() + " " + e.getMessage());
        }
        return new Router(DisPathType.FORWARD,PagePath.INDEX);
    }

    private UserChoiceDataHandler buildDataHandlerFromRequest(HttpServletRequest request) {
        UserChoiceDataHandler dataHandler = new UserChoiceDataHandlerBuilder()
                .buildCity(request.getParameter(RequestParameter.FILTER_CITY))
                .buildDistrict(request.getParameter(RequestParameter.FILTER_DISTRICT))
                .buildStreet(request.getParameter(RequestParameter.FILTER_STREET))
                .buildRooms(request.getParameter(RequestParameter.FILTER_ROOMS))
                .buildLivingArea(request.getParameter(RequestParameter.FILTER_LIVING_AREA))
                .buildPrice(request.getParameter(RequestParameter.FILTER_PRICE))
                .buildHasFurniture(request.getParameter(RequestParameter.FILTER_HAS_FURNITURE))
                .buildHasHomeAppliances(request.getParameter(RequestParameter.FILTER_HAS_HOME_APPLIANCES))
                .buildPossibleWithChild(request.getParameter(RequestParameter.FILTER_POSSIBLE_WITH_CHILDREN))
                .buildPossibleWithPets(request.getParameter(RequestParameter.FILTER_POSSIBLE_WITH_PETS))
                .buildUserChoiceDataHandler();
        LOG.debug(dataHandler);
        return dataHandler;
    }

}
