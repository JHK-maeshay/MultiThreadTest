package com.example.java_play_ground.service.app;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.java_play_ground.service.components.Warehouse;

@Component
public class CreateWarehouseCommand implements Command {
    private final CommandContext context;

    @Autowired
    public CreateWarehouseCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute(float f, Logger logger) {
        int capacity = (int) f;
        Warehouse warehouse = new Warehouse();
        warehouse.setCapacity(capacity);
        context.addWarehouse(warehouse);
    }
}