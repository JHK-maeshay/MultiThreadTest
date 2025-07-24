package app;

import java.util.logging.Logger;

class ListConveyorCommand implements Command {
    private final CommandContext context;

    public ListConveyorCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute(float f, Logger logger) {
        context.showConveyor();
    }
}