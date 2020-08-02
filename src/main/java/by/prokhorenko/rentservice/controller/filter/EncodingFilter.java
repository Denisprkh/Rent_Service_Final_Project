package by.prokhorenko.rentservice.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param") })
public class EncodingFilter implements Filter {

    private String encoding;
    private static final String ENCODING_PARAMETER_NAME = "encoding";

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter(ENCODING_PARAMETER_NAME);
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        String codeRequest = request.getCharacterEncoding();
        if (encoding != null && !encoding.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        encoding = null;
    }
}
