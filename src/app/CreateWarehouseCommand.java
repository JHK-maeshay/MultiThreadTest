package app;

import java.util.logging.Logger;

public class CreateWarehouseCommand implements Command{
    private final CommandContext context;

    public CreateWarehouseCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute(float f, Logger logger){
        logger.info("CreateWCommand executed with n = " + f);
    }
}
