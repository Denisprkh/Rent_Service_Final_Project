package by.prokhorenko.rentservice.controller.command;

import by.prokhorenko.rentservice.controller.Router;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface command
 */
public interface Command {

    /**
     * Execute command.
     *
     * @param request
     * @return {@see Router}
     */
    Router execute(HttpServletRequest request);

    /**
     * Builds redirect url.
     *
     * @param request     httpRequest
     * @param commandName commandName
     * @return redirectUrl
     */
    default String buildRedirectUrl(HttpServletRequest request, String commandName) {
        String redirectUrl = request.getContextPath() + request.getServletPath() + "?" + RequestParameter.PARAM_COMMAND
                + "=" + commandName;
        return redirectUrl;
    }
}
