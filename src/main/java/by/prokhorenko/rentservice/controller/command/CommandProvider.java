package by.prokhorenko.rentservice.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class CommandProvider {
    private static final Logger LOG = LogManager.getLogger();

    public static Optional<Command> defineCommand(String commandName){
        Optional<Command> current;
        if(commandName == null || commandName.isEmpty()){
            return Optional.empty();
        }

        try {
            CommandName command = CommandName.findCommandByName(commandName);
            current = Optional.of(command.getCommand());
        }catch (IllegalArgumentException e){
            current = Optional.empty();
        }
        return current;
    }
}
