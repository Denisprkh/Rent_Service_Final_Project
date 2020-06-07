package by.prokhorenko.rentservice.controller.command;

import by.prokhorenko.rentservice.service.user.impl.UserServiceImpl;

public enum  CommandType {
    SIGN_UP(new SignUpCommand(new UserServiceImpl()));


    private Command command;

    CommandType(Command command){
        this.command = command;
    }

    public Command getCommand(){
        return command;
    }
}
