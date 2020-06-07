package by.prokhorenko.rentservice.controller;

import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.CommandProvider;
import by.prokhorenko.rentservice.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(urlPatterns = {"/controller"})
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

    @Override
    public void destroy() {
        LOG.trace("ControllerServlet destroying");
        ConnectionPool.INSTANCE.destroyPool();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        Optional<Command> commandOptional = CommandProvider.defineCommand(request.getParameter("command"));

        Command command = commandOptional.orElseThrow(IllegalArgumentException::new);

        String page = command.execute(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request,response);
    }
}
