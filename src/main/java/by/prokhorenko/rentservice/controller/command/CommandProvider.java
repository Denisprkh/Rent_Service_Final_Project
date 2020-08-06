package by.prokhorenko.rentservice.controller.command;


import java.util.Optional;

public class CommandProvider {
    private CommandProvider() {
    }

    public static Optional<Command> defineCommand(String commandName) {
        Optional<Command> current;
        if (commandName == null || commandName.isEmpty()) {
            return Optional.empty();
        }

        try {
            CommandName command = CommandName.findCommandByName(commandName);
            current = Optional.of(command.getCommand());
        } catch (IllegalArgumentException e) {
            current = Optional.empty();
        }
        return current;
    }
}
