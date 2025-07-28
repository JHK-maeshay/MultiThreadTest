package com.example.java_play_ground.service.app;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.java_play_ground.service.components.Conveyor;

@Component
public class CreateConveyorCommand implements Command {
    private final CommandContext context;
    
    @Autowired
    public CreateConveyorCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute(float f, Logger logger) {
        Conveyor conveyor = new Conveyor();  // target은 나중에 link 단계에서 설정됨
        conveyor.setLength(f);
        context.addConveyor(conveyor);
    }
}
