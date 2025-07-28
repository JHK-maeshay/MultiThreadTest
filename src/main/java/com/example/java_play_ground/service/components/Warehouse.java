package com.example.java_play_ground.service.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.java_play_ground.service.controller.Receiver;
import com.example.java_play_ground.service.item.Product;

public class Warehouse implements Receiver {
    private static int counter = 0;  // 공유 카운터
	
    private final String name;
    private final List<Product> storage;
    private int capacity;
    private Runnable onSpaceAvailable = null;

    public Warehouse() {
        counter++;  // 객체 생성될 때 counter 증가
        this.name = "w" + counter;
        this.storage = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public int getCapacity() {
    		return this.capacity;
    }
    
    public void setCapacity(int capacity) {
    		this.capacity = capacity;
    }
    
    public void show() {
        System.out.print(name + " - storage: " + storage.size() + "/" + capacity);
        System.out.print('\n');
    }

    @Override
    public synchronized boolean receive(Product p) {
        cleanExpired();
        if (storage.size() >= capacity) {
            return false; // 저장 공간 부족
        }
        storage.add(p);
        return true;
    }

    @Override
    public synchronized void registerCallback(Runnable callback) {
        this.onSpaceAvailable = callback;
    }

    // 유통기한 지난 제품 제거
    private synchronized void cleanExpired() {
        Iterator<Product> it = storage.iterator();
        boolean removed = false;
        while (it.hasNext()) {
            Product p = it.next();
            if (p.isExpired()) {
                it.remove();
                removed = true;
            }
        }
        if (removed && onSpaceAvailable != null) {
            onSpaceAvailable.run(); // 공간 생김 알림
        }
    }

    public synchronized int getSize() {
        return storage.size();
    }

    public synchronized List<Product> getStoredProducts() {
        return new ArrayList<>(storage);
    }
}