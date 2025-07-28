package com.example.java_play_ground.service.app;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.java_play_ground.service.components.Conveyor;
import com.example.java_play_ground.service.components.Producer;
import com.example.java_play_ground.service.controller.Linker;

@Component
public class LinkProducerToConveyorCommand implements Command {
    private final CommandContext context;

    @Autowired
    public LinkProducerToConveyorCommand(CommandContext context) {
        this.context = context;
    }

    public boolean tryExecute(Logger logger) {
        String producerName = context.getTempLinkFrom();
        String conveyorName = context.getTempLinkTo();

        Producer producer = context.getProducerByName(producerName);
        Conveyor conveyor = context.getConveyorByName(conveyorName);

        if (producer != null && conveyor != null) {
            Linker controller = new Linker(
                    java.util.Collections.singletonList(producer),
                    java.util.Collections.singletonList(conveyor)
            );
            context.addController(controller);
            new Thread(controller).start();
            context.addLink(producerName, conveyorName);
            logger.info("Linked " + producerName + " to " + conveyorName);
            return true;
        } else {
            logger.warning("Link failed: producer or conveyor not found.");
            return false;
        }
    }

    @Override
    public void execute(float f, Logger logger) {
        tryExecute(logger); // 예전 인터페이스 유지용 래퍼
    }
}
