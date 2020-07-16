package by.prokhorenko.rentservice.controller.command;

import by.prokhorenko.rentservice.controller.Router;
import by.prokhorenko.rentservice.controller.command.impl.Attribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    Router execute(HttpServletRequest request, HttpServletResponse response);

}
