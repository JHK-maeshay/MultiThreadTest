package app;

import java.util.logging.Logger;

public class CommandHandler {
    private final CommandRegistry registry;

    public CommandHandler(CommandRegistry registry) {
        this.registry = registry;
    }

    public void handle(String commandName, float f, Logger logger) {
        Command command = registry.getCommand(commandName);
        if (command != null) {
            command.execute(f, logger);
        } else {
            logger.warning("Unknown command: " + commandName);
        }
    }
}