package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
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
        return "redirect:/basic/item";
    }

    /**수정 화면 페이지 이동
     * Edit string.
     *
     * @param itemId the item id
     * @param model  the model
     * @return the string
     */
    @GetMapping("{itemId}/edit")
    public String edit(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }


    /**수정 내용 저장
     * Edit string.
     *
     * @param itemId the item id
     * @param item   the item
     * @return the string
     */
    @PostMapping("{itemId}/edit")
    public String edit(@PathVariable Long itemId
            , @ModelAttribute("item") Item item) {
//               스프링이 모델에 객체를 매핑하면서 보관해준다. 모델 안에 지정 해둔 이름으로 뷰에서 사용된다 생략될 경우 클래스명이 이름으로 사용된다. (첫글자는 소문자로 변환)
        itemRepository.update(itemId, item);
        itemRepository.findById(itemId);
//        model.addAttribute("item", updateItem);
    return  "redirect:/basic/items/"+itemId;
    }
}
