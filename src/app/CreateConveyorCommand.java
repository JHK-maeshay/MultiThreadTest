package app;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

import conveyor.Conveyor;
import item.Product;

public class CreateConveyorCommand implements Command {
    private final CommandContext context;

    public CreateConveyorCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute(float f, Logger logger) {
        Queue<Product> queue = new LinkedList<>();
        Conveyor conveyor = new Conveyor(queue, f, logger);  // target은 나중에 link 단계에서 설정됨
        context.addConveyor(conveyor);
    }
}
