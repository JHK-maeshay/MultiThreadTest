package com.example.java_play_ground.service.app;

import com.example.java_play_ground.service.components.Producer;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateProducerCommand implements Command{
    private final CommandContext context;

    @Autowired
    public CreateProducerCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute(float f, Logger logger){
        //빈 스택 생성
        //second -> milisecond
        int n = (int) (f*1000);
        //100이하일시 강제 설정
        if (n<100){
            n=100;
        }
        //인수 전달해서 객체 생성
        Producer producer = new Producer();
        producer.setProducingTime(n);
        producer.setLogger(logger);
        //Map에 등록
        context.addProducer(producer);
        //스레드에 전달하고 실행
        Thread producerThread = new Thread(producer);
        producerThread.start();
    }
}

