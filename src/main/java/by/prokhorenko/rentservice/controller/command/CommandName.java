package by.prokhorenko.rentservice.controller.command;

import by.prokhorenko.rentservice.controller.command.impl.*;
import by.prokhorenko.rentservice.controller.command.impl.page.*;

import java.util.Arrays;
import java.util.Optional;

public enum CommandName {

    UPDATE_ADVERTISEMENT_PAGE(new UpdateAdvertisementPageCommand(), "updateAdvertisementPage"),
    ADD_AN_ADVERTISEMENT_PAGE(new AddAnAdvertisementPageCommand(), "addAnAdvertisementPage"),
    ADVERTISEMENT_PAGE(new AdvertisementPageCommand(), "advertisementPage"),
    ALL_USERS_PAGE(new AllUsersPageCommand(), "allUsersPage"),
    ACTIVATION_INFO_PAGE(new ActivationInfoPageCommand(), "activationInfoPage"),
    APPROVE_REQUEST(new ApproveRequestCommand(), "approveRequest"),
    BAN_USER(new BanUserCommand(), "banUser"),
    CHANGE_LANGUAGE(new ChangeLanguageCommand(), "changeLanguage"),
    CONFIRM_REGISTRATION(new ConfirmRegistrationCommand(), "confirmRegistration"),
    CREATE_NEW_ADVERTISEMENT(new AddNewAdvertisementCommand(), "createNewAdvertisement"),
    DELETE_ADVERTISEMENT(new DeleteAdvertisementCommand(), "deleteAdvertisement"),
    DISAPPROVE_REQUEST(new DisapproveRequestCommand(), "disapproveRequest"),
    FIND_ADVERTISEMENTS_BY_FILTER(new FindAdvertisementsByUserChoiceCommand(), "findAdvertisementsByFilter"),
    MAIN_PAGE(new MainPageCommand(), "mainPage&currentPage=1"),
    GIVE_ADMIN_RIGHTS(new GiveAdminRightsCommand(), "giveAdminRights"),
    LOG_OUT(new LogOutCommand(), "logOut"),
    PICK_UP_ADMIN_RIGHTS(new PickUpAdminRightsCommand(), "pickUpAdminRights"),
    PROFILE_PAGE(new ProfilePageCommand(), "profilePage"),
    SEND_A_REQUEST(new NewRequestCommand(), "sendARequest"),
    SET_FLAT_IN_RENT(new SetFlatIsInRentCommand(), "setFlatInRent"),
    SET_FLAT_IS_NOT_IN_RENT(new SetFlatIsNotInRentCommand(), "setFlatIsNotInRent"),
    SIGN_IN(new SignInCommand(), "signIn"),
    SIGN_IN_PAGE(new SignInPageCommand(), "signInPage"),
    SIGN_UP(new SignUpCommand(), "signUp"),
    SIGN_UP_PAGE(new SignUpPageCommand(), "signUpPage"),
    UNBAN_USER(new UnBanUserCommand(), "unBanUser"),
    UPDATE_PROFILE_DATA(new UpdateUserInfoCommand(), "updateProfileData"),
    UPDATE_AN_ADVERTISEMENT(new UpdateAdvertisementCommand(), "updateAnAdvertisement"),
    ALL_ADVERTISEMENTS_PAGE(new AllAdvertisementsPageCommand(), "allAdvertisementsPage"),
    ALL_REQUESTS_PAGE(new AllRequestsPageCommand(), "allRequestsPage"),
    MY_REQUESTS_PAGE(new MyRequestsPageCommand(), "myRequestsPage"),
    MY_ADS_PAGE(new MyAdsPageCommand(), "myAdsPage"),
    REQUESTS_FOR_MY_ADS_PAGE(new RequestsForMyAdsPageCommand(), "requestsForMyAdsPage");

    private final String commandName;
    private final Command command;

    CommandName(Command command, String commandName) {
        this.command = command;
        this.commandName = commandName;
    }

    public Command getCommand() {
        return command;
    }

    public String getCommandName() {
        return commandName;
    }

    public static CommandName findCommandByName(String commandName) {
        CommandName[] commandNames = CommandName.values();
        Optional<CommandName> command = Arrays.stream(commandNames).filter
                (o -> o.getCommandName().equals(commandName)).findAny();
        if (command.isEmpty()) {
            return CommandName.MAIN_PAGE;
        }
        return command.get();
    }
}
