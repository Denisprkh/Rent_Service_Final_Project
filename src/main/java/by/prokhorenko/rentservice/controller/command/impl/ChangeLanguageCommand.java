package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChangeLanguageCommand implements Command {
    private static final String REFERER_HEADER = "referer";
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        String targetLanguage = req.getParameter(RequestParameter.PARAM_LANGUAGE);
        String previousPage = null;
        if (targetLanguage != null) {
            req.getSession().setAttribute(Attribute.LANGUAGE, targetLanguage);
            previousPage= req.getHeader(REFERER_HEADER);

        }
        return new Router(previousPage);
    }
}
