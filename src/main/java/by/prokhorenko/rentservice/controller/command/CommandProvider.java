package by.prokhorenko.rentservice.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class CommandProvider {
    private static final Logger LOG = LogManager.getLogger();

    public static Optional<Command> defineCommand(String commandName){
        Optional<Command> current;
        LOG.debug(commandName);
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
