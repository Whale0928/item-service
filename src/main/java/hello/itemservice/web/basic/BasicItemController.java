package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    //@RequiredArgsConstructor 으로 인해 생성자가 없어도 자동 주입
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model modle){
        List<Item> items = itemRepository.findAll();
        modle.addAttribute("items",items);
        return "basic/items";
    }

    /**테스트 아이템
     * Init.
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemName",10000,10));
    }
}
