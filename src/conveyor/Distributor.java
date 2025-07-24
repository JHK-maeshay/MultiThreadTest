package conveyor;

import item.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Distributor {
    private static int counter = 0;  // 공유 카운터
	int MAX_CAPACITY = 10000;
	
    private final String name;
    private final Queue<Product> buffer = new LinkedList<>();
    private final int maxCapacity;

    public Distributor() {
        this.maxCapacity = MAX_CAPACITY;
        this.name = this.getClass().getSimpleName() + counter;
    }
    
    public String getName() {
        return name;
    }
    
    public void show() {
        System.out.print(name + " - ");
        System.out.print('\n');
    }

    // 제품 수신
    public synchronized boolean receive(Product product) {
        if (buffer.size() >= maxCapacity) {
            return false;  // 공간 없음 → 실패
        }
        buffer.add(product);
        return true;
    }

    // 저장된 제품들을 모두 꺼내 전달
    public synchronized List<Product> dispatch() {
        List<Product> result = new LinkedList<>();
        while (!buffer.isEmpty()) {
            result.add(buffer.poll());
        }
        return result;
    }

    // 선택적으로 일부만 꺼내는 버전도 가능
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