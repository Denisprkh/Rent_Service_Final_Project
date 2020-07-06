package by.prokhorenko.rentservice.controller.command.impl;

import by.prokhorenko.rentservice.controller.PagePath;
import by.prokhorenko.rentservice.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguageCommand implements Command {
    private static final String REFERER_HEADER = "referer";
    private static final Logger LOG = LogManager.getLogger();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String targetLanguage = req.getParameter(JspParameter.PARAM_LANGUAGE);
        if (targetLanguage != null) {
            req.getSession().setAttribute(JspParameter.PARAM_LANGUAGE, targetLanguage);
            String prev = req.getHeader(REFERER_HEADER);
            try {
                resp.sendRedirect(prev);
                return PagePath.EMPTY_PAGE;
            } catch (IOException e) {
                LOG.warn(e);
                return PagePath.SIGN_UP;
            }
        }
        return PagePath.SIGN_UP;
    }
}
