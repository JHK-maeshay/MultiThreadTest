package conveyor;

import item.Product;
import controller.Receiver;

import java.util.Queue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Logger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Conveyor implements Receiver {
    private static int counter = 0;  // 공유 카운터
    private int conveyTotal = 0; // 물건 옮긴 수 카운터

    private final String name;
    private final Queue<Product> queue;
    private float length;
    private int lengthInt;
    private final Logger logger;
    private final long delayMillis;
    private Receiver target = null;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public Conveyor(Queue<Product> queue, float length, Logger logger) {
        this.queue = queue;
        this.length = length;
        this.lengthInt = (int) length;
        this.logger = logger;
        this.delayMillis = (long) length * 1000;
        counter++;  // 객체 생성될 때 counter 증가
        this.name = "c" + counter;
    }

    // Receiver 인터페이스 구현
    @Override
    public boolean receive(Product product) {
        addProduct(product);
        return true;  // 단순화: 항상 true 반환
    }

    @Override
    public void registerCallback(Runnable callback) {
        // Conveyor는 자체 스케줄링 구조이므로 특별한 콜백은 사용하지 않음
        // 필요하면 향후 구현 가능
    }

    public void setTarget(Receiver target) {
        synchronized (this) {
            this.target = target;
            if (target != null) {
                target.registerCallback(this::flushRetryQueue);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void show() {
        System.out.print(name + " - legnth: " + length + " unit -- conveyed : " + conveyTotal);
        System.out.print('\n');
    }

    public void addProduct(Product p) {
        synchronized (queue) {
            if (queue.size() >= lengthInt) {
                return;  // 큐가 가득 찼으면 거부
            }
            queue.add(p);
        }

        scheduler.schedule(() -> {
            deliverProduct(p);
        }, delayMillis, TimeUnit.MILLISECONDS);
    }

    private void deliverProduct(Product p) {
        synchronized (queue) {
            if (!queue.contains(p)) return;

            if (target != null && target.receive(p)) {
                queue.remove(p);
                conveyTotal++;
            }
        }
    }

    private synchronized void flushRetryQueue() {
        Iterator<Product> it = queue.iterator();
        while (it.hasNext()) {
            Product p = it.next();
            if (target.receive(p)) {
                it.remove();
                conveyTotal++;
            } else {
                break;
            }
        }
    }
}
