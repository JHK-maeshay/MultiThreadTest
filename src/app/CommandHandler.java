package app;

import conveyor.*;
import factory.*;
import item.*;
import utils.*;
import warehouse.*;

import java.util.Map;
import java.util.TreeMap;
import java.util.Stack;
import java.util.logging.Logger;

public class CommandHandler {
    private Map<String, Producer> producersMap = new TreeMap<>();
    private Map<String, Conveyor> conveyorsMap = new TreeMap<>();
    private Map<String, Warehouse> warehousesMap = new TreeMap<>();
    private Map<String, Distributor> distributorsMap = new TreeMap<>();

    public void handleCreateF(float f, Logger logger) {
        //빈 스택 생성
        Stack<Product> productStack = new Stack<>();
        //second -> milisecond
        int n = (int) (f*1000);
        //100이하일시 강제 설정
        if (n<100){
            n=100;
        }
        //인수 전달해서 객체 생성
        Producer producer = new Producer(productStack, n, logger);
        //Map에 등록
        producersMap.put(producer.getName(), producer);
        //스레드에 전달하고 실행
        Thread producerThread = new Thread(producer);
        producerThread.start();
    }

    public void handleCreateW(int n, Logger logger) {

    }

    public void handleCreateC(int n, Logger logger) {

    }

    public void handleCreateD(Logger logger) {

    }

    public void handleListF(Logger logger) {
        for (Producer producer : producersMap.values()) {
            producer.show();
        }
    }

    public void handleListW(Logger logger) {

    }

    public void handleListC(Logger logger) {

    }

    public void handleListD(Logger logger) {

    }
}
