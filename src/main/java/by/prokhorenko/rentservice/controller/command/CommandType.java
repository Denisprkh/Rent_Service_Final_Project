package by.prokhorenko.rentservice.controller.command;

import by.prokhorenko.rentservice.controller.command.impl.*;
import by.prokhorenko.rentservice.service.user.impl.UserServiceImpl;

public enum  CommandType {
    SIGN_UP(new SignUpCommand()),
    CONFIRM_REGISTRATION(new ConfirmRegistrationCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    SIGN_IN(new SignInCommand()),
    LOG_OUT(new LogOutCommand());

    private Command command;

    CommandType(Command command){
        this.command = command;
    }

    public Command getCommand(){
        return command;
    }
}
