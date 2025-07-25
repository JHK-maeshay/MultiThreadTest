package app;

import java.util.logging.Logger;

import controller.Controller;
import conveyor.Conveyor;
import factory.Producer;

public class LinkProducerToConveyorCommand implements Command{
	private final CommandContext context;

    public LinkProducerToConveyorCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute(float f, Logger logger) {
        // f는 사용되지 않음
        String producerName = context.getTempLinkFrom();
        String conveyorName = context.getTempLinkTo();

        Producer producer = context.getProducerByName(producerName);
        Conveyor conveyor = context.getConveyorByName(conveyorName);

        if (producer != null && conveyor != null) {
            Controller controller = new Controller(
                    java.util.Collections.singletonList(producer),
                    java.util.Collections.singletonList(conveyor)
            );
            context.addController(controller);
            new Thread(controller).start();
            context.addLink(producerName, conveyorName);
            logger.info("Linked " + producerName + " to " + conveyorName);
        } else {
            logger.warning("Link failed: producer or conveyor not found.");
        }
    }
}
