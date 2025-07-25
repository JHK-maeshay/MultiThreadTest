package controller;

import factory.Producer;
import conveyor.Conveyor;
import conveyor.Distributor;

import java.util.*;

public class Manager {
    private final Map<String, Producer> producers = new HashMap<>();
    private final Map<String, Conveyor> conveyors = new HashMap<>();
    private final Map<String, Distributor> distributors = new HashMap<>();
    private final List<Thread> controllers = new ArrayList<>();

    // 연결 정보: producerName → conveyorNames
    private final Map<String, List<String>> links = new HashMap<>();

    public void addProducer(String name, Producer p) {
        producers.put(name, p);
    }

    public void addConveyor(String name, Conveyor c) {
        conveyors.put(name, c);
    }

    public void addDistributor(String name, Distributor d) {
        distributors.put(name, d);
    }

    // 명령어 기반 연결 처리
    public void link(String producerName, String conveyorName) {
        links.computeIfAbsent(producerName, k -> new ArrayList<>()).add(conveyorName);
    }

    // 연결 정보 기반으로 Controller 생성

    // 선택적으로 모든 스레드 종료 (중단 시그널 등 추가 가능)
    public void shutdown() {
        for (Thread t : controllers) {
            t.interrupt();
        }
    }
}
