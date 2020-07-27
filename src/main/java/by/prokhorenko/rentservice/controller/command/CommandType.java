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
    PROFILE_PAGE(new ProfilePageCommand()),
    ADD_AN_ADVERTISEMENT_PAGE(new AddAnAdvertisementPageCommand()),
    ADVERTISEMENT_PAGE(new AdvertisementPageCommand()),
    SEND_A_REQUEST(new NewRequestCommand()),
    UPDATE_PROFILE_DATA(new UpdateUserInfoCommand()),
    DELETE_ADVERTISEMENT(new DeleteAdvertisementCommand()),
    FIND_ADVERTISEMENTS_BY_FILTER(new FindAdvertisementsByUserChoiceCommand()),
    SET_FLAT_IN_RENT(new SetFlatIsInRentCommand()),
    SET_FLAT_IS_NOT_IN_RENT(new SetFlatIsNotInRentCommand());


    private Command command;

    CommandType(Command command){
        this.command = command;
    }

    public Command getCommand(){
        return command;
    }
}
