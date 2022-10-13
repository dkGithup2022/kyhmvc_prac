package app.controller;

import app.model.Item;
import app.service.repository.ItemRepository;
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
    private final ItemRepository itemRepository;

    @GetMapping
    public  String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }

    /*
    @GetMapping("/{itemId}")
    public  String item(@PathVariable Long id, Model model){
        Item item = itemRepository.findById(id);
        model.addAttribute("item",item);
        return "basic/item";
    }
*/
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }



    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public  String addItemV1(
            @RequestParam String itemName,
            @RequestParam int price,
            @RequestParam Integer quantity,
            Model model
    ){
        Item item = new Item(itemName,price,quantity);
        itemRepository.save(item);
        model.addAttribute("item",item);
        return "basic/item";
    }


    /*
    @ModelAttribute 는 @RequestParam 의 변수를 받아서 객체에 순서대로 setter 로 넣음 .
     */
    //@PostMapping("/add")
    public  String addItemV2(
            @ModelAttribute("item") Item item,
            Model model
    ){
        itemRepository.save(item);
        // model.addAttribute("item",item); 자동 추가됨 .
        return "basic/item";
    }

    /*
    Model attribute 도 생략 가능
     */
    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){
        itemRepository.save(item);
        return "basic/item";
    }

    /*
    그냥 죄다 생략가능
     */
    @PostMapping("/add")
    public String addItemV4(Item item){
        itemRepository.save(item);
        return "redirect:/basic/items/"+item.getId();
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editFor";
    }

    @PostMapping("/{itemId}/edit")
    public  String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId,item);
        return "redirect:/basic/items/{itemId}";
    }


    @PostConstruct
    public void init(){
        itemRepository.save(new Item("item1",1,1));
        itemRepository.save(new Item("item2",2,2));
    }
}
