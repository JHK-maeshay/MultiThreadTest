package com.example.java_play_ground.service.app;

import com.example.java_play_ground.service.components.Conveyor;
import com.example.java_play_ground.service.components.Distributor;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LinkDistributorToConveyorCommand implements Command {
    private final CommandContext context;
    
    @Autowired
    public LinkDistributorToConveyorCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute(float f, Logger logger) {
        String distributorName = context.getTempLinkFrom();
        String conveyorName = context.getTempLinkTo();

        Distributor distributor = context.getDistributorByName(distributorName);
        Conveyor conveyor = context.getConveyorByName(conveyorName);

        if (distributor != null && conveyor != null) {
            distributor.addTarget(conveyor); // Conveyor는 Receiver이므로 가능
            context.addLink(distributorName, conveyorName); // universalLinks에 저장
            logger.info("Linked Distributor " + distributorName + " to Conveyor " + conveyorName);
        } else {
            logger.warning("Link failed: Distributor or Conveyor not found.");
        }
    }
}
