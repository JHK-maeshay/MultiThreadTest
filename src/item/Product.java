package item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//생성된 시간을 productionTime에 기록
public class Product {
    private final LocalDateTime productionTime;
    private final LocalDateTime expirationTime;

    public Product(){
        this.productionTime = LocalDateTime.now();
        this.expirationTime = this.productionTime.plusMinutes(2);
    }

    public void show(){
        //시계열 데이터 포매터
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm:ss");
        String ptformatted = productionTime.format(formatter);
        String etformatted = expirationTime.format(formatter);
        System.out.println(ptformatted + " | " + etformatted);
    }

}
