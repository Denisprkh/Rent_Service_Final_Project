package by.prokhorenko.rentservice.controller.command;

import java.util.EnumSet;
import java.util.Set;
import static by.prokhorenko.rentservice.controller.command.CommandName.*;

public enum CommandType {

    GUEST(EnumSet.of(
            FIND_ALL_ADVERTISEMENTS,
            FIND_ADVERTISEMENTS_BY_FILTER,
            SIGN_IN_PAGE,
            SIGN_IN,
            SIGN_UP_PAGE,
            SIGN_UP,
            CHANGE_LANGUAGE,
            ADVERTISEMENT_PAGE,
            CONFIRM_REGISTRATION
    )),
    USER(EnumSet.of(
            FIND_ALL_ADVERTISEMENTS,
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
            SET_FLAT_IN_RENT,
            SET_FLAT_IS_NOT_IN_RENT,
            APPROVE_REQUEST,
            DISAPPROVE_REQUEST
    )),
    ADMIN(EnumSet.of(
            FIND_ALL_ADVERTISEMENTS,
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
            SET_FLAT_IN_RENT,
            SET_FLAT_IS_NOT_IN_RENT,
            APPROVE_REQUEST,
            DISAPPROVE_REQUEST,
            BAN_USER,
            UNBAN_USER,
            GIVE_ADMIN_RIGHTS,
            PICK_UP_ADMIN_RIGHTS,
            ALL_USERS_PAGE
    ));



    private final Set<CommandName> commandNames;
    CommandType(Set<CommandName> commandNames){
        this.commandNames = commandNames;
    }

    public Set<CommandName> getCommandNames(){
        return commandNames;
    }
}
