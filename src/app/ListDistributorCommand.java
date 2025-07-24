package app;

import java.util.logging.Logger;

class ListDistributorCommand implements Command {
    private final CommandContext context;

    public ListDistributorCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute(float f, Logger logger) {
        context.showDistributor();
    }
}
