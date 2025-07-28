package com.example.java_play_ground.service.app;

import java.util.logging.Logger;

class ListProducerCommand implements Command {
    private final CommandContext context;

    public ListProducerCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute(float f, Logger logger) {
        context.showProducer();  // 위임
    }
}
