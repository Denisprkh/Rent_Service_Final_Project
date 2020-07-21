package by.prokhorenko.rentservice.controller.command;

import by.prokhorenko.rentservice.controller.command.impl.*;

public enum  CommandType {
    SIGN_UP(new SignUpCommand()),
    CONFIRM_REGISTRATION(new ConfirmRegistrationCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    SIGN_IN(new SignInCommand()),
    LOG_OUT(new LogOutCommand()),
    FIND_ALL_ADVERTISEMENTS(new FindAllAdvertisementsCommand()),
    CREATE_NEW_ADVERTISEMENT(new AddNewAdvertisementCommand()),
    PROFILE_PAGE(new ProfileRedirectCommand()),
    TEST(new TestCommand());

    private Command command;

    CommandType(Command command){
        this.command = command;
    }

    public Command getCommand(){
        return command;
    }
}
