package app;

import conveyor.*;
import factory.*;
import warehouse.*;

import java.util.Map;
import java.util.TreeMap;

public class CommandContext {
    public final Map<String, Producer> producersMap = new TreeMap<>();
    public final Map<String, Conveyor> conveyorsMap = new TreeMap<>();
    public final Map<String, Warehouse> warehousesMap = new TreeMap<>();
    public final Map<String, Distributor> distributorsMap = new TreeMap<>();

    public void addProducer(Producer producer) {
        producersMap.put(producer.getName(), producer);
    }
    public void showProducer(){
        for (Producer producer : producersMap.values()) {
            producer.show();
        }
    }
}