package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    //@RequiredArgsConstructor 으로 인해 생성자가 없어도 자동 주입
    private final ItemRepository itemRepository;

    /**
     * 테스트 아이템
     * Init.
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemNameOne", 15000, 11));
    }

    /* 상품 전체 보기.
     * Items string.
     *
     * @param modle the modle
     * @return the string
     */
    @GetMapping
    public String items(Model modle) {
        List<Item> items = itemRepository.findAll();
        modle.addAttribute("items", items);
        return "basic/items";
    }


    /**
     * 상품 상세보기
     * Detail string.
     *
     * @param itemId the item id
     * @param model  the model
     * @return the string
     */
    @GetMapping("/{itemId}")
    public String detail(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    /**
     * 신규 아이템 화면
     * Add string.
     *
     * @return the string
     */
    @GetMapping("/add")
    public String add() {
        return "basic/addForm";
    }

    /**
     * 신규 아이템 저장
     * Add string.
     *
     * @return the string
     */
    @PostMapping("/add")
    public String save(Model model, @ModelAttribute Item item) {
        Item saveItem = itemRepository.save(item);
        model.addAttribute("item", saveItem);
        return "basic/item";
    }

    @GetMapping("{itemId}/edit")
    public String edit(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @PostMapping("{itemId}/edit")
    public String edit(@PathVariable Long itemId
            , @ModelAttribute Item newItem
            , Model model) {
        itemRepository.update(itemId, newItem);
        Item updateItem = itemRepository.findById(itemId);
        model.addAttribute("item", updateItem);
    return "basic/item";
    }
}
