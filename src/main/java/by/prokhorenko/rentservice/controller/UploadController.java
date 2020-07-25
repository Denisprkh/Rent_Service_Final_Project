package by.prokhorenko.rentservice.controller;

import by.prokhorenko.rentservice.controller.command.Command;
import by.prokhorenko.rentservice.controller.command.CommandProvider;
import by.prokhorenko.rentservice.controller.command.impl.RequestParameter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/UploadController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        Optional<Command> commandOptional = CommandProvider.defineCommand(
                request.getParameter(RequestParameter.PARAM_COMMAND));
        Command command = commandOptional.orElseThrow(IllegalArgumentException::new);
        Router router = command.execute(request,response);
        if(DisPathType.FORWARD.equals(router.getDisPathType())){
            request.getRequestDispatcher(router.getPage()).forward(request,response);
        }else{
            response.sendRedirect(router.getPage());
        }

    }
}
