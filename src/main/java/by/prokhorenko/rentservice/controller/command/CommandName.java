package by.prokhorenko.rentservice.controller.command;

import by.prokhorenko.rentservice.controller.command.impl.*;
import by.prokhorenko.rentservice.controller.command.impl.page.*;

public enum CommandName {
    INDEX(new IndexPageCommand()),
    UPDATE_ADVERTISEMENT_PAGE(new UpdateAdvertisementPageCommand()),
    ADD_AN_ADVERTISEMENT_PAGE(new AddAnAdvertisementPageCommand()),
    ADVERTISEMENT_PAGE(new AdvertisementPageCommand()),
    ALL_USERS_PAGE(new AllUsersPageCommand()),
    APPROVE_REQUEST(new ApproveRequestCommand()),
    BAN_USER(new BanUserCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    CONFIRM_REGISTRATION(new ConfirmRegistrationCommand()),
    CREATE_NEW_ADVERTISEMENT(new AddNewAdvertisementCommand()),
    DELETE_ADVERTISEMENT(new DeleteAdvertisementCommand()),
    DISAPPROVE_REQUEST(new DisapproveRequestCommand()),
    FIND_ADVERTISEMENTS_BY_FILTER(new FindAdvertisementsByUserChoiceCommand()),
    FIND_ALL_ADVERTISEMENTS(new FindAllAdvertisementsCommand()),
    GIVE_ADMIN_RIGHTS(new GiveAdminRightsCommand()),
    LOG_OUT(new LogOutCommand()),
    PICK_UP_ADMIN_RIGHTS(new PickUpAdminRightsCommand()),
    PROFILE_PAGE(new ProfilePageCommand()),
    SEND_A_REQUEST(new NewRequestCommand()),
    SET_FLAT_IN_RENT(new SetFlatIsInRentCommand()),
    SET_FLAT_IS_NOT_IN_RENT(new SetFlatIsNotInRentCommand()),
    SIGN_IN(new SignInCommand()),
    SIGN_IN_PAGE(new SignInPageCommand()),
    SIGN_UP(new SignUpCommand()),
    SIGN_UP_PAGE(new SignUpPageCommand()),
    UNBAN_USER(new UnBanUserCommand()),
    UPDATE_PROFILE_DATA(new UpdateUserInfoCommand()),
    UPDATE_AN_ADVERTISEMENT(new UpdateAdvertisementCommand()),
    ALL_ADVERTISEMENTS_PAGE(new AllAdvertisementsPageCommand()),
    ALL_REQUESTS_PAGE(new AllRequestsPageCommand());


    private Command command;

    CommandName(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
