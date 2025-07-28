package com.example.java_play_ground.service.controller;

import com.example.java_play_ground.service.item.Product;

public interface Receiver {
    boolean receive(Product product); // 성공 여부 반환
    void registerCallback(Runnable callback); // 공간 생겼을 때 호출할 콜백 등록
}