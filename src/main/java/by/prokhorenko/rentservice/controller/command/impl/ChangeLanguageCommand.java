package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Attribute;
import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.RequestParameter;
import by.prokhorenko.rentservice.controller.command.util.CommandUtil;

import javax.servlet.http.HttpServletRequest;

public class ChangeLanguageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        String targetLanguage = request.getParameter(RequestParameter.PARAM_LANGUAGE);
        String previousPage = null;
        if (targetLanguage != null) {
            request.getSession().setAttribute(Attribute.LANGUAGE, targetLanguage);
            previousPage = request.getHeader(CommandUtil.REFERER_HEADER);
        }
        return new Router(previousPage);
    }
}
