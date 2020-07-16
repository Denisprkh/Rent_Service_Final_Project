package by.prokhorenko.rentservice.controller;

import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.CommandProvider;
import by.prokhorenko.rentservice.controller.command.impl.JspParameter;
import by.prokhorenko.rentservice.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/controller")
public class ServletController extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger();



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        Optional<Command> commandOptional = CommandProvider.defineCommand(
                request.getParameter(JspParameter.PARAM_COMMAND));
        Command command = commandOptional.orElseThrow(IllegalArgumentException::new);
        Router router = command.execute(request,response);
        if(Router.DisPathType.FORWARD.equals(router.getDisPathType())){
            request.getRequestDispatcher(router.getPage()).forward(request,response);
        }else{
            response.sendRedirect(router.getPage());
        }

    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.INSTANCE.destroyPool();
    }
}
