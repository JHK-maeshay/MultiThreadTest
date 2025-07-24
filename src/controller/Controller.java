package controller;

import factory.Producer;
import conveyor.Conveyor;
import conveyor.Distributor;
import item.Product;

import java.util.List;

public class Controller implements Runnable {
    private final List<Producer> producers;
    private final List<Conveyor> outputConveyors;
    private final Distributor distributor;
    private final long intervalMillis = 100;

    private int conveyorIndex = 0;

    public Controller(List<Producer> producers, List<Conveyor> outputConveyors, Distributor distributor) {
        this.producers = producers;
        this.outputConveyors = outputConveyors;
        this.distributor = distributor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Step 1: Producer → Distributor
                for (Producer producer : producers) {
                    Product product = producer.pollProduct();
                    if (product != null) {
                        distributor.receive(product);
                    }
                }

                // Step 2: Distributor → Conveyor
                List<Product> productsToSend = distributor.dispatch();
                for (Product p : productsToSend) {
                    Conveyor target = selectNextConveyor();
                    if (target != null) {
                        target.addProduct(p);
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
        if (outputConveyors.isEmpty()) return null;
        Conveyor conveyor = outputConveyors.get(conveyorIndex);
        conveyorIndex = (conveyorIndex + 1) % outputConveyors.size();
        return conveyor;
    }
}