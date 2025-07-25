package controller;

import factory.Producer;
import conveyor.Conveyor;
import item.Product;

import java.util.List;

public class Controller implements Runnable {
    private final List<Producer> producers;
    private final List<Conveyor> conveyors;
    private final long intervalMillis = 100;

    private int conveyorIndex = 0;

    public Controller(List<Producer> producers, List<Conveyor> conveyors) {
        this.producers = producers;
        this.conveyors = conveyors;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Producer → Conveyor
                for (Producer producer : producers) {
                    Product product = producer.pollProduct();
                    if (product != null) {
                        Conveyor target = selectNextConveyor();
                        if (target != null) {
                            target.addProduct(product);
                        }
                    }
                }

                Thread.sleep(intervalMillis);

            } catch (InterruptedException e) {
                System.out.println("Controller interrupted");
                break;
            }
        }
    }

    private Conveyor selectNextConveyor() {
        if (conveyors.isEmpty()) return null;
        Conveyor conveyor = conveyors.get(conveyorIndex);
        conveyorIndex = (conveyorIndex + 1) % conveyors.size();
        return conveyor;
    }
}