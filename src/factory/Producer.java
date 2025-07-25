package factory;

import item.Product;

import java.util.Stack;
import java.util.logging.Logger;

public class Producer implements Runnable{
    private static final int MAX_STACK_SIZE = 10;

    private static int counter = 0;  // 공유 카운터

    private final String name;
    private final Stack<Product> stack;
    private final long producingTime; //millisecond
    private final Logger logger;
    private int produceTotal = 0; //총 생산량

    public Producer(Stack<Product> stack, long producingTime, Logger logger){
        this.stack = stack;
        this.producingTime = producingTime;
        this.logger = logger;
        counter++;  // 객체 생성될 때 counter 증가
        this.name = "f" + counter;
    }

    public String getName() {
        return name;
    }

    //조회시 스택 상태 확인용 플래그
    private volatile boolean isStackFull = false;
    //*이름* - per *생산시간*/s, Total: *총생산량*
    public void show() {
        String ptFormatted = String.format("%.2f", (float) producingTime/1000);
        System.out.print(name + " - per " + ptFormatted + "/s -- Made: "+ produceTotal);
        //스택 동기화 문제 해결
        if (isStackFull)
            System.out.print(" (OUTPUT STUCK!!)");
        System.out.print('\n');
    }

    @Override
    public void run() {
        try {
            while (true) {
                //대기 상태 제어

                //스택 제어
                synchronized (stack) {
                    if (stack.size() >= MAX_STACK_SIZE) {
                        /*System.out.println("Buffer full! Connect conveyor!");*/
                        // 생산을 skip 하고 기다림
                        isStackFull = true;
                        Thread.sleep(producingTime);
                        continue;
                    }
                }

                isStackFull = false;
                //매 producingTime 마다 product 생성후 스택에 추가
                Thread.sleep(producingTime);
                Product p = new Product();
                produceTotal++;
                synchronized (stack) {
                    stack.push(p);
                }


            }
        } catch (InterruptedException e) {
            logger.warning("Producer interrupted");
        }
    }
    
    //스택에 있는 객체 전달
    public synchronized Product pollProduct() {
        if (!stack.isEmpty()) {
            return stack.pop();
        }
        return null;
    }

}
