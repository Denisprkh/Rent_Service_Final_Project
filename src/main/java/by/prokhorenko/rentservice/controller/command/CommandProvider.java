package by.prokhorenko.rentservice.controller.command;

import java.util.Optional;

public class CommandProvider {

    public static Optional<Command> defineCommand(String commandName){
        Optional<Command> current;

        if(commandName == null || commandName.isEmpty()){
            return Optional.empty();
        }

        try {
            CommandType type = CommandType.valueOf(commandName.toUpperCase());
            current = Optional.of(type.getCommand());
        }catch (IllegalArgumentException e){
            current = Optional.empty();
        }

        return current;
    }
}
