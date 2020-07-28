package by.prokhorenko.rentservice.controller.command;

import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.impl.Attribute;
import by.prokhorenko.rentservice.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface Command {

    Router execute(HttpServletRequest request, HttpServletResponse response);

    default void definePaginationContext(HttpServletRequest request, int fullRecordsQuantity, int currentPage, int recordsPerPage) {
        int allPagesAmount = fullRecordsQuantity / recordsPerPage;
        if((fullRecordsQuantity-(allPagesAmount * recordsPerPage)) % recordsPerPage > 0){
            allPagesAmount++;
        }

        request.setAttribute(Attribute.PAGINATION_PAGES_QUANTITY,allPagesAmount);
        request.setAttribute(Attribute.PAGINATION_CURRENT_PAGE,currentPage);
    }


}
