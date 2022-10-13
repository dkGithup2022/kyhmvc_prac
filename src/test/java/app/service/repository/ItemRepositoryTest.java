package app.service.repository;

import app.model.Item;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class ItemRepositoryTest {

    private final ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    public void beforeEachTest() {
        itemRepository.clear();
    }

    @Test
    void save() {
        // given
        Item item = new Item("item", 10, 10);

        // when
        Item saved = itemRepository.save(item);

        //then
        assertEquals(item, saved);
    }

    @Test
    void findById() {
        // given
        Item item = new Item("item", 10, 10);
        Item saved = itemRepository.save(item);

        // when
        Item found = itemRepository.findById(item.getId());

        //then
        assertEquals(found, saved);
        assertEquals(found, item);
    }

    @Test
    void findAll() {

        //given
        Item item1 = new Item("item1", 10, 10);
        Item item2 = new Item("item2", 20, 20);
        Item saved1 = itemRepository.save(item1);
        Item saved2 = itemRepository.save(item2);
        // when
        List<Item> list = itemRepository.findAll();

        assertEquals(list.size(), 2);
        assertTrue(list.contains(saved1) && list.contains(saved2));

    }

    @Test
    void update() {
        //given
        Item item = new Item("item", 10, 10);
        Item saved = itemRepository.save(item);


        // when
        Item updateItem = new Item("updated", 10000, 1000);
        itemRepository.update(saved.getId(), updateItem);
        Item updated = itemRepository.findById(saved.getId());

        //then
        assertEquals(updated.getItemName(), "updated");
        assertEquals(updated.getPrice(), 10000);
    }
}