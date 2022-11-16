package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {
    final ItemRepository itemRepository = new ItemRepository();

    /**
     * 테스트마다 새로운 객체로 테스트 해야 하기 때문에.
     * After each.
     */
    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    @DisplayName("아이템 저장")
    void save() {
        //given
        Item item = new Item("TestItem",1000,100);
        //when
        Item save = itemRepository.save(item);
        //then
        assertThat(save).isNotNull();
    }

    @Test
    @DisplayName("특정 아이디로 회원 찾기")
    void findById() {
        //given
        Item item1 = new Item("Target",1000,100);
        Item item2 = new Item("TestItem",1000,100);
        //when
        itemRepository.save(item1);
        itemRepository.save(item2);
        //then
        Item getItem = itemRepository.findById(item1.getId());
        assertThat(getItem).isEqualTo(item1);
    }
    @Test
    @DisplayName("특정 아이디로 회원 찾기 실패 케이스")
    void notFindById() {
        //given
        Item item1 = new Item("Target",1000,100);
        Item item2 = new Item("TestItem",1000,100);
        //when
        itemRepository.save(item1);
        itemRepository.save(item2);
        //then
        Item getItem = itemRepository.findById(item1.getId());
        assertThat(getItem).isNotEqualTo(item2);
    }

    @Test
    @DisplayName("모든 회원 목록 조회")
    void findAll() {
        Item item1 = new Item("Target",1000,100);
        Item item2 = new Item("TestItem",1000,100);
        //when
        itemRepository.save(item1);
        itemRepository.save(item2);
        //when
        List<Item> all = itemRepository.findAll();
        //then
        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(item1,item2);
    }

    @Test
    @DisplayName("업데이트 수행")
    void updateCase() {
        //given
        Item old = new Item("old", 1000, 10);
        Item newItem = new Item("newItem", 1200, 10);
        itemRepository.save(old);
        //when
        itemRepository.update(old.getId(),newItem);
        //then
        Item target = itemRepository.findById(old.getId());
        assertThat(target.getItemName()).isEqualTo(newItem.getItemName());
        assertThat(target.getQuantity()).isEqualTo(newItem.getQuantity());
    }
}