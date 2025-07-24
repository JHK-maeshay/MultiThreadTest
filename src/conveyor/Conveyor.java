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

public class Conveyor{
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

    public Conveyor(Queue<Product> queue, float length, Logger logger){
    		this.queue = queue;
        this.length = length;
        this.lengthInt = (int)length;
        this.logger = logger;
        this.delayMillis = (long)length*1000;
        counter++;  // 객체 생성될 때 counter 증가
        this.name = this.getClass().getSimpleName() + counter;
    }
    //length 미지정시 디폴트 10 -> 구현안함
    //
    
    //link로 target 변경시 반드시 호출 필요
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
        System.out.print(name + " - legnth: " + length + " unit -- conveyed : "+ conveyTotal);
        System.out.print('\n');
    }

 // Conveyor 입력부
    public void addProduct(Product p) {
        synchronized (queue) {
            if (queue.size() >= lengthInt) {
                // 큐가 가득 찼으면 거부
                return;
            }
            queue.add(p);
        }

        // 지정된 시간(delayMillis) 후 전달 시도
        scheduler.schedule(() -> {
            deliverProduct(p);
        }, delayMillis, TimeUnit.MILLISECONDS);
    }

    // 실제 전달 로직 (delay 후 또는 flush에서 사용)
    private void deliverProduct(Product p) {
        synchronized (queue) {
            if (!queue.contains(p)) return; // 이미 처리됐거나 제거됨

            if (target != null && target.receive(p)) {
                queue.remove(p);
                conveyTotal++;
            }
            // 실패한 경우는 queue에 그대로 남기고, flushRetryQueue가 나중에 다시 시도
        }
    }

    // 출력 재시도 메소드
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
