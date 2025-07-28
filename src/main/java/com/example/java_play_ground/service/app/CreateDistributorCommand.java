package com.example.java_play_ground.service.app;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.java_play_ground.service.components.Distributor;

@Component
public class CreateDistributorCommand implements Command {
    private final CommandContext context;
    
    @Autowired
    public CreateDistributorCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute(float f, Logger logger) {
        Distributor distributor = new Distributor();
        context.addDistributor(distributor);
    }
}

