package by.prokhorenko.rentservice.controller.command;

import java.util.EnumSet;
import java.util.Set;
import static by.prokhorenko.rentservice.controller.command.CommandName.*;

public enum CommandType {

    GUEST(EnumSet.of(
            MAIN_PAGE,
            FIND_ADVERTISEMENTS_BY_FILTER,
            SIGN_IN_PAGE,
            SIGN_IN,
            SIGN_UP_PAGE,
            SIGN_UP,
            CHANGE_LANGUAGE,
            ADVERTISEMENT_PAGE,
            CONFIRM_REGISTRATION,
            ACTIVATION_INFO_PAGE
    )),
    USER(EnumSet.of(
            MAIN_PAGE,
            FIND_ADVERTISEMENTS_BY_FILTER,
            CHANGE_LANGUAGE,
            PROFILE_PAGE,
            ADVERTISEMENT_PAGE,
            ADD_AN_ADVERTISEMENT_PAGE,
            LOG_OUT,
            CREATE_NEW_ADVERTISEMENT,
            SEND_A_REQUEST,
            MY_ADS_PAGE,
            MY_REQUESTS_PAGE,
            REQUESTS_FOR_MY_ADS_PAGE,
            UPDATE_PROFILE_DATA,
            UPDATE_ADVERTISEMENT_PAGE,
            UPDATE_AN_ADVERTISEMENT,
            DELETE_ADVERTISEMENT,
            SET_FLAT_IN_RENT,
            SET_FLAT_IS_NOT_IN_RENT,
            APPROVE_REQUEST,
            DISAPPROVE_REQUEST
    )),
    ADMIN(EnumSet.of(
            MAIN_PAGE,
            FIND_ADVERTISEMENTS_BY_FILTER,
            CHANGE_LANGUAGE,
            PROFILE_PAGE,
            ADVERTISEMENT_PAGE,
            ADD_AN_ADVERTISEMENT_PAGE,
            LOG_OUT,
            CREATE_NEW_ADVERTISEMENT,
            SEND_A_REQUEST,
            UPDATE_PROFILE_DATA,
            DELETE_ADVERTISEMENT,
            UPDATE_ADVERTISEMENT_PAGE,
            UPDATE_AN_ADVERTISEMENT,
            SET_FLAT_IN_RENT,
            SET_FLAT_IS_NOT_IN_RENT,
            APPROVE_REQUEST,
            DISAPPROVE_REQUEST,
            MY_ADS_PAGE,
            MY_REQUESTS_PAGE,
            REQUESTS_FOR_MY_ADS_PAGE,
            BAN_USER,
            UNBAN_USER,
            GIVE_ADMIN_RIGHTS,
            PICK_UP_ADMIN_RIGHTS,
            ALL_USERS_PAGE,
            ALL_ADVERTISEMENTS_PAGE,
            ALL_REQUESTS_PAGE
    ));



    private final Set<CommandName> commandNames;
    CommandType(Set<CommandName> commandNames){
        this.commandNames = commandNames;
    }

    public Set<CommandName> getCommandNames(){
        return commandNames;
    }
}
