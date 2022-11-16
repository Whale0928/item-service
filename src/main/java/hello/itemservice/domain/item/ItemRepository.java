package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepository {
//DB 저장 공간
    private static final Map<Long,Item> store = new HashMap<>();// static
    //여러개의 접근이 생기면 HashMap이 접근하면 문제가 발생한다
    // ConcurrentHashMap 을 대체 사용

    private static long sequence = 0l;            //static

    public Item save(Item item){
        item.setId(++sequence);  //저장 전 시퀀스 증가
        store.put(item.getId(),item); // 여기서 시퀀스를 저장 시키면 다른 사용자가 접근할떄 중복 증가 하는 경우가 발생
        return item;
    }
}

