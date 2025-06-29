package item;

import java.time.LocalDateTime;

//생성된 시간을 productionTime에 기록
public class Product {
    private final LocalDateTime productionTime;
    private final LocalDateTime expirationTime;

    public Product(){
        this.productionTime = LocalDateTime.now();
        this.expirationTime = this.productionTime.plusMinutes(2);
    }


}
