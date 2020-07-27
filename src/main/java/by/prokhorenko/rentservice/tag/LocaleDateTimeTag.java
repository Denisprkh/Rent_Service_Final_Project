package by.prokhorenko.rentservice.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocaleDateTimeTag extends TagSupport {

    private static final Logger LOG = LogManager.getLogger();
    private LocalDateTime value;
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public void setValue(LocalDateTime value){
        this.value = value;
    }

    @Override
    public int doStartTag() throws JspException {

        String dateTime;

        if (value instanceof LocalDateTime) {
            dateTime = value.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        } else {
            dateTime = "";
        }

        JspWriter writerOut = pageContext.getOut();
        try {
            writerOut.write(dateTime);
        } catch (IOException e) {
            LOG.error("Executing localeDateTime tag error",e);
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
