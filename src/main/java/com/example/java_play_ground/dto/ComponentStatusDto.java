package com.example.java_play_ground.dto;

public class ComponentStatusDto {
    private String name;

    // Producer fields
    private Float producingTime;
    private Integer produceTotal;

    // Conveyor fields
    private Float length;
    private Integer conveyTotal;

    // Warehouse fields
    private Integer storageSize;
    private Integer capacity;

    // Constructors
    public ComponentStatusDto(String name) {
        this.name = name;
    }

    // Getters and setters (IDE 자동 생성 또는 Lombok 사용 가능)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Float getProducingTime() { return producingTime; }
    public void setProducingTime(Float producingTime) { this.producingTime = producingTime; }

    public Integer getProduceTotal() { return produceTotal; }
    public void setProduceTotal(Integer produceTotal) { this.produceTotal = produceTotal; }

    public Float getLength() { return length; }
    public void setLength(Float length) { this.length = length; }

    public Integer getConveyTotal() { return conveyTotal; }
    public void setConveyTotal(Integer conveyTotal) { this.conveyTotal = conveyTotal; }

    public Integer getStorageSize() { return storageSize; }
    public void setStorageSize(Integer storageSize) { this.storageSize = storageSize; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
}
