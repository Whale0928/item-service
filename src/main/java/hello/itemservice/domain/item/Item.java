package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data// DTO은 어느정도 괜찮지만 핵심 도메인은 @DATA를 매우 주의하자.
public class Item {
    private Long id;
    private String itemName;

    //Integer 인 이유는 ? 값이 세팅안될 가능성이 있기 때문ㅇ Int 자료형은은 null 을 허용되지 않는다.
    private Integer price;
    private Integer quantity;


    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }


}
