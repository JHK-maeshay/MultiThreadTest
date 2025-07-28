package com.example.java_play_ground.service.app;

import java.util.logging.Logger;

class ListWarehouseCommand implements Command {
    private final CommandContext context;

    public ListWarehouseCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute(float f, Logger logger) {
        context.showWarehouse();
    }
}
