package conveyor;

import controller.Receiver;
import item.Product;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Distributor implements Receiver {
    private static int counter = 0;
    private static final int MAX_CAPACITY = 10;

    private final String name;
    private final Queue<Product> buffer = new LinkedList<>();
    private final int maxCapacity;

    private Runnable callback;
    private final List<Receiver> outputTargets = new LinkedList<>();

    private ScheduledExecutorService scheduler = null;
    private final long dispatchIntervalMillis = 100;

    public Distributor() {
        this.maxCapacity = MAX_CAPACITY;
        counter++;
        this.name = "d" + counter;
    }

    public String getName() {
        return name;
    }

    public void show() {
        System.out.println(name + " - Stored products: " + buffer.size());
    }

    // 제품 수신
    @Override
    public synchronized boolean receive(Product product) {
        if (buffer.size() >= maxCapacity) {
            return false;
        }
        buffer.add(product);
        if (callback != null) {
            callback.run();
        }
        return true;
    }

    @Override
    public synchronized void registerCallback(Runnable callback) {
        this.callback = callback;
    }

    // Conveyor 등 수신 대상 추가 및 스케줄러 시작
    public synchronized void addTarget(Receiver receiver) {
        outputTargets.add(receiver);

        // 스케줄러는 처음 target이 추가될 때만 실행
        if (scheduler == null) {
            scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(this::autoDispatchToTargets, 0, dispatchIntervalMillis, TimeUnit.MILLISECONDS);
        }
    }

    // 자동 전달 메서드
    private synchronized void autoDispatchToTargets() {
        if (outputTargets.isEmpty() || buffer.isEmpty()) return;

        Iterator<Receiver> it = outputTargets.iterator();
        while (!buffer.isEmpty()) {
            Product p = buffer.peek();  // 제거하지 않음
            boolean delivered = false;

            while (it.hasNext()) {
                Receiver target = it.next();
                if (target.receive(p)) {
                    buffer.poll();  // 성공 시 제거
                    delivered = true;
                    break;
                }
            }

            // 순회 끝났으면 다시 처음부터 시도
            if (!delivered) break;
        }
    }

    // 선택적으로 직접 꺼내 쓰는 메서드 (기존 유지)
    public synchronized List<Product> dispatch() {
        List<Product> result = new LinkedList<>();
        while (!buffer.isEmpty()) {
            result.add(buffer.poll());
        }
        return result;
    }

    public synchronized List<Product> dispatch(int limit) {
        List<Product> result = new LinkedList<>();
        while (!buffer.isEmpty() && result.size() < limit) {
            result.add(buffer.poll());
        }
        return result;
    }

    public synchronized int getStoredCount() {
        return buffer.size();
    }
}