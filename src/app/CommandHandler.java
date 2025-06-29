package app;

import conveyor.*;
import factory.*;
import item.*;
import utils.*;
import warehouse.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Logger;

public class CommandHandler {
    private Map<String, Producer> producers = new HashMap<>();
    private Map<String, Conveyor> conveyors = new HashMap<>();
    private Map<String, Warehouse> warehouses = new HashMap<>();
    private Map<String, Distributor> distributors = new HashMap<>();

    public void handleCreateF(int n, Logger logger) {
        //빈 스택 생성
        Stack<Product> productStack = new Stack<>();
        //second -> milisecond
        n *= 1000;
        //인수 전달해서 객체 생성
        Producer producer = new Producer(productStack, n, logger);
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
}
