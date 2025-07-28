package com.example.java_play_ground.controller;

import com.example.java_play_ground.dto.ComponentStatusDto;
import com.example.java_play_ground.service.app.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/components")
public class ComponentController {

    private final CommandContext context;
    private final CreateConveyorCommand createConveyorCommand;
    private final CreateProducerCommand createProducerCommand;
    private final CreateDistributorCommand createDistributorCommand;
    private final CreateWarehouseCommand createWarehouseCommand;

    @Autowired
    public ComponentController(
        CommandContext context,
        CreateConveyorCommand createConveyorCommand,
        CreateProducerCommand createProducerCommand,
        CreateDistributorCommand createDistributorCommand,
        CreateWarehouseCommand createWarehouseCommand
    ) {
        this.context = context;
        this.createConveyorCommand = createConveyorCommand;
        this.createProducerCommand = createProducerCommand;
        this.createDistributorCommand = createDistributorCommand;
        this.createWarehouseCommand = createWarehouseCommand;
    }

 // 🔹 Conveyor 생성
    @PostMapping("/conveyor")
    public List<ComponentStatusDto> addConveyor(@RequestParam(name = "value") Optional<Float> value) {
    		float length = value.filter(v -> v > 0).orElse(2.5f);
        createConveyorCommand.execute(length, Logger.getGlobal());
        return context.conveyorsMap.values().stream().map(conveyor -> {
            ComponentStatusDto dto = new ComponentStatusDto(conveyor.getName());
            dto.setLength(conveyor.getLength());
            dto.setConveyTotal(conveyor.getConveyedCount());
            return dto;
        }).toList();
    }

 // 🔹 Producer 생성
    @PostMapping("/producer")
    public List<ComponentStatusDto> addProducer(@RequestParam(name = "value") Optional<Float> value) {
        float producingTimeSec = value.filter(v -> v > 0).orElse(7.5f);
        createProducerCommand.execute(producingTimeSec, Logger.getGlobal());
        return context.producersMap.values().stream().map(producer -> {
            ComponentStatusDto dto = new ComponentStatusDto(producer.getName());
            dto.setProducingTime(producer.getProducingTime() / 1000f);
            dto.setProduceTotal(producer.getProduceTotal());
            return dto;
        }).toList();
    }

    // 🔹 Distributor 생성
    @PostMapping("/distributor")
    public List<ComponentStatusDto> addDistributor() {
        createDistributorCommand.execute(0f, Logger.getGlobal());
        return context.distributorsMap.values().stream().map(distributor -> {
            ComponentStatusDto dto = new ComponentStatusDto(distributor.getName());
            return dto;
        }).toList();
    }

 // 🔹 Warehouse 생성
    @PostMapping("/warehouse")
    public List<ComponentStatusDto> addWarehouse(@RequestParam(name = "value") Optional<Float> value) {
    		float capacity = value.filter(v -> v > 0).orElse(120f);
        createWarehouseCommand.execute(capacity, Logger.getGlobal());
        return context.warehousesMap.values().stream().map(warehouse -> {
            ComponentStatusDto dto = new ComponentStatusDto(warehouse.getName());
            dto.setStorageSize(warehouse.getSize());
            dto.setCapacity(warehouse.getCapacity());
            return dto;
        }).toList();
    }
    
    @GetMapping("/status/{type}")
    public List<ComponentStatusDto> getComponentStatus(@PathVariable("type") String type) {
        List<ComponentStatusDto> result;

        switch (type) {
            case "producer":
                result = context.producersMap.values().stream().map(producer -> {
                    ComponentStatusDto dto = new ComponentStatusDto(producer.getName());
                    dto.setProducingTime(producer.getProducingTime() / 1000f);
                    dto.setProduceTotal(producer.getProduceTotal());
                    return dto;
                }).toList();
                break;

            case "conveyor":
                result = context.conveyorsMap.values().stream().map(conveyor -> {
                    ComponentStatusDto dto = new ComponentStatusDto(conveyor.getName());
                    dto.setLength(conveyor.getLength());
                    dto.setConveyTotal(conveyor.getConveyedCount());
                    return dto;
                }).toList();
                break;

            case "warehouse":
                result = context.warehousesMap.values().stream().map(warehouse -> {
                    ComponentStatusDto dto = new ComponentStatusDto(warehouse.getName());
                    dto.setStorageSize(warehouse.getSize());
                    dto.setCapacity(warehouse.getCapacity());
                    return dto;
                }).toList();
                break;

            case "distributor":
                result = context.distributorsMap.values().stream().map(distributor -> {
                    ComponentStatusDto dto = new ComponentStatusDto(distributor.getName());
                    return dto;
                }).toList();
                break;

            default:
                throw new IllegalArgumentException("Invalid component type: " + type);
        }

        return result;
    }
}
