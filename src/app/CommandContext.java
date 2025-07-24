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
    
 // Producer 관련 메서드
    public void addProducer(Producer producer) {
        producersMap.put(producer.getName(), producer);
    }
    public void showProducer(){
        for (Producer producer : producersMap.values()) {
            producer.show();
        }
    }
    
 // Conveyor 관련 메서드
    public void addConveyor(Conveyor conveyor) {
        conveyorsMap.put(conveyor.getName(), conveyor);
    }

    public void showConveyor() {
        for (Conveyor conveyor : conveyorsMap.values()) {
            conveyor.show();
        }
    }
    
 // Warehouse 관련 메서드
    public void addWarehouse(Warehouse warehouse) {
        warehousesMap.put(warehouse.getName(), warehouse);
    }

    public void showWarehouse() {
        for (Warehouse warehouse : warehousesMap.values()) {
            warehouse.show();
        }
    }

 // Distributor 관련 메서드
    public void addDistributor(Distributor distributor) {
        distributorsMap.put(distributor.getName(), distributor);
    }

    public void showDistributor() {
        for (Distributor distributor : distributorsMap.values()) {
            distributor.show();
        }
    }
}