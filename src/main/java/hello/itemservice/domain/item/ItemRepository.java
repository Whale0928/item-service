package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /**아이디로 아이템 찾기
     * Find by id item.
     *
     * @param id the id
     * @return the item
     */
    public Item findById(Long id){
        return store.get(id);
    }

    /**맵을 리스트로 한번 감싸 전체 조회하는 메서드
     * Find all list.
     *
     * @return the list
     */
    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    /**업데이트 기능
     * Update.
     *
     * @param id          the id
     * @param updateParam the update param
     */
    public void update(Long id , Item updateParam){
        Item findItem = findById(id);
        //프로젝트 규모에 따라 업데이트용 dto를 만드는 것이 바람직하다.
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }
}

