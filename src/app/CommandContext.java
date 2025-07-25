package app;

import controller.*;
import conveyor.*;
import factory.*;
import warehouse.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CommandContext {
    public final Map<String, Producer> producersMap = new TreeMap<>();
    public final Map<String, Conveyor> conveyorsMap = new TreeMap<>();
    public final Map<String, Warehouse> warehousesMap = new TreeMap<>();
    public final Map<String, Distributor> distributorsMap = new TreeMap<>();
    private final List<Controller> controllers = new ArrayList<>();
    private final Map<String, String> universalLinks = new HashMap<>();
    private String tempLinkFrom;
    private String tempLinkTo;
    
 // Producer 관련 메서드
    public void addProducer(Producer producer) {
        producersMap.put(producer.getName(), producer);
    }
    public void showProducer(){
        for (Producer producer : producersMap.values()) {
            producer.show();
        }
    }
    public Producer getProducerByName(String name) {
        return producersMap.get(name);
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
    public Conveyor getConveyorByName(String name) {
        return conveyorsMap.get(name);
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
    public Warehouse getWarehouseByName(String name) {
        return warehousesMap.get(name);
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
    public Distributor getDistributorByName(String name) {
        return distributorsMap.get(name);
    }

 // Controller 관련 메서드
    public void addController(Controller controller) {
        controllers.add(controller);
    }
    
 // 링크 관련
    public void setTempLinkFrom(String name) {
        this.tempLinkFrom = name;
    }
    public void setTempLinkTo(String name) {
        this.tempLinkTo = name;
    }
    public String getTempLinkFrom() {
        return tempLinkFrom;
    }
    public String getTempLinkTo() {
        return tempLinkTo;
    }
    public void addLink(String fromName, String toName) {
        universalLinks.put(fromName, toName);
    }
    public void showAllLinks() {
        for (Map.Entry<String, String> entry : universalLinks.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }
}