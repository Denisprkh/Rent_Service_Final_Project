package by.prokhorenko.rentservice.controller.command;

import by.prokhorenko.rentservice.controller.Router;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    Router execute(HttpServletRequest request);

    default String buildRedirectUrl(HttpServletRequest request, String pageCommandName){
        String redirectUrl = request.getContextPath() + request.getServletPath() + "?" + RequestParameter.PARAM_COMMAND
                + "=" + pageCommandName;
        return redirectUrl;
    }
}
