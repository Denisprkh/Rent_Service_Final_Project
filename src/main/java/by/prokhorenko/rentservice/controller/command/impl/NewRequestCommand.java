package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.builder.RequestBuilder;
import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.entity.advertisement.Advertisement;
import by.prokhorenko.rentservice.entity.request.Request;
import by.prokhorenko.rentservice.entity.user.User;
import by.prokhorenko.rentservice.exception.ServiceException;
import by.prokhorenko.rentservice.factory.ServiceFactory;
import by.prokhorenko.rentservice.service.request.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Arrays;

public class NewRequestCommand implements Command {

    private static final Logger LOG = LogManager.getLogger();
    private RequestService requestService;
    private static final String START_END_DATE_SPLITTER = "-";
    private static final String DATE_ON_NUMBERS_SPLITTER = "\\.";
    private static final int DAY_INDEX = 0;
    private static final int MONTH_INDEX = 1;
    private static final int YEAR_INDEX = 2;
    private static final int HOURS_DEFAULT_VALUE = 0;
    private static final int MINUTES_DEFAULT_VALUE = 0;
    private static final int START_DATE_INDEX = 0;
    private static final int END_DATE_INDEX = 1;

    public NewRequestCommand() {
        this.requestService = ServiceFactory.getInstance().getRequestService();
    }

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User sender = (User) session.getAttribute(Attribute.USER);
        String notParsedDate = request.getParameter(RequestParameter.REQUEST_RENT_DATE);
        String[] parsedDate = parseDateToStartAndEndDate(notParsedDate);
        int[] startDateInNumbers = parseDateOnNumbers(parsedDate[START_DATE_INDEX]);
        int[] endDateInNumbers = parseDateOnNumbers(parsedDate[END_DATE_INDEX]);
        LocalDateTime startDate = buildDateFromNumbers(startDateInNumbers);
        LocalDateTime endDate = buildDateFromNumbers(endDateInNumbers);
        Advertisement advertisement =(Advertisement) session.getAttribute(Attribute.ADVERTISEMENT);
        Request rentRequest = buildRequest(sender,advertisement,startDate,endDate);
        LOG.debug(startDate);
        LOG.debug(endDate);
        try {
            requestService.addRequest(rentRequest);
        } catch (ServiceException e) {
            LOG.error(e);
            return new Router(PagePath.USER_PROFILE);
        }
        return new Router(PagePath.INDEX);
    }

    private String[] parseDateToStartAndEndDate(String date){
        String[] parsedDate = date.split(START_END_DATE_SPLITTER);
        return parsedDate;
    }

    private int[] parseDateOnNumbers(String date){
        int[] numbers = new int[3];
        String[] dateForParsing = date.split(DATE_ON_NUMBERS_SPLITTER);
        for(int i = 0; i<numbers.length;i++){
            numbers[i] = Integer.parseInt(dateForParsing[i]);
        }
        LOG.debug(Arrays.toString(numbers));
        return numbers;
    }

    private LocalDateTime buildDateFromNumbers(int[] numbers){
        LocalDateTime dateFromNumbers = LocalDateTime.of(numbers[YEAR_INDEX],numbers[MONTH_INDEX],numbers[DAY_INDEX],
                HOURS_DEFAULT_VALUE,MINUTES_DEFAULT_VALUE);
        LOG.debug(dateFromNumbers);
        return dateFromNumbers;
    }

    private Request buildRequest(User sender, Advertisement advertisement, LocalDateTime startDate, LocalDateTime endDate){
        Request request = new RequestBuilder()
                .buildAdvertisement(advertisement)
                .buildUser(sender)
                .buildStartDate(startDate)
                .buildEndDate(endDate)
                .buildApplicationDate(LocalDateTime.now())
                .buildRequest();
        return request;
    }

}
