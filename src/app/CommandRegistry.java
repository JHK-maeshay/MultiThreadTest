package app;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private final Map<String, Command> commandMap = new HashMap<>();

    //객체별 HashMap을 관리하는 콘텍스트 주입
    CommandContext context = new CommandContext();

    public CommandRegistry() {
        // 명령어 등록
        commandMap.put("createWarehouse", new CreateWarehouseCommand(context));
        commandMap.put("createConveyor", new CreateConveyorCommand(context));
        commandMap.put("createDistributor", new CreateDistributorCommand(context));
        commandMap.put("createProducer", new CreateProducerCommand(context));
        commandMap.put("listProducer", new ListProducerCommand(context));
    }

    public Command getCommand(String name) {
        return commandMap.get(name);
    }
}