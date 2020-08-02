package by.prokhorenko.rentservice.controller.command;

import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.impl.Attribute;
import by.prokhorenko.rentservice.controller.command.impl.RequestParameter;
import by.prokhorenko.rentservice.entity.request.Request;
import by.prokhorenko.rentservice.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface Command {

    Router execute(HttpServletRequest request);

    default String buildRedirectUrl(HttpServletRequest request, String pageCommandName){
        String redirectUrl = request.getContextPath() + request.getServletPath() + "?" + RequestParameter.PARAM_COMMAND
                + "=" + pageCommandName;
        return redirectUrl;
    }
}
