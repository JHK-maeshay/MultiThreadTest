package app;

import java.util.logging.Logger;

import warehouse.Warehouse;

public class CreateWarehouseCommand implements Command {
    private final CommandContext context;

    public CreateWarehouseCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute(float f, Logger logger) {
        int capacity = (int) f;
        Warehouse warehouse = new Warehouse(capacity);
        context.addWarehouse(warehouse);
    }
}