package com.example.java_play_ground.service.app;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.java_play_ground.service.components.Conveyor;
import com.example.java_play_ground.service.components.Warehouse;

@Component
public class LinkConveyorToWarehouseCommand implements Command {
	private final CommandContext context;
	
	@Autowired
    public LinkConveyorToWarehouseCommand(CommandContext context) {
        this.context = context;
    }
    
    @Override
    public void execute(float f, Logger logger) {
        String conveyorName = context.getTempLinkFrom();
        String warehouseName = context.getTempLinkTo();

        Conveyor conveyor = context.getConveyorByName(conveyorName);
        Warehouse warehouse = context.getWarehouseByName(warehouseName);

        if (conveyor != null && warehouse != null) {
        	conveyor.setTarget(warehouse);
            context.addLink(conveyorName, warehouseName); // universalLinks에 저장
            logger.info("Linked Conveyor " + conveyorName + " to Warehouse " + warehouseName);
        } else {
            logger.warning("Link failed: Conveyor or Warehouse not found.");
        }
    }
}
