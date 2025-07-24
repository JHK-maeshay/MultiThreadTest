package app;

import java.util.logging.Logger;

import conveyor.Distributor;

public class CreateDistributorCommand implements Command {
    private final CommandContext context;

    public CreateDistributorCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute(float f, Logger logger) {
        Distributor distributor = new Distributor();
        context.addDistributor(distributor);
    }
}

