package conveyor;

import java.util.Queue;
import java.util.LinkedList;

import item.Product;

public class Conveyor implements Runnable{
    private static int counter = 0;  // 공유 카운터

    private final String name;
    private final Queue<Product> queue = new LinkedList<Product>();
    private long length;

    public Conveyor(long length){
        this.length = length;
        counter++;  // 객체 생성될 때 counter 증가
        this.name = this.getClass().getSimpleName() + counter;
    }
    //length 미지정시 디폴트 10
    public Conveyor(){
        this(10);
    }

    public String getName() {
        return name;
    }

    //Conveyor 입력부
    public synchronized void addProduct(Product p){
        queue.add(p);
    }

    @Override
    public void run(){
        //스레드구현
    }
}
