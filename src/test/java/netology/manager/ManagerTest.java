package netology.manager;

import netology.domain.Book;
import netology.domain.NotFoundException;
import netology.domain.Product;
import netology.domain.Smartphone;
import netology.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    private final ProductRepository repository = new ProductRepository();
    private final Manager manager = new Manager(repository);
    Product first = new Smartphone(1, "iPhone-5", 100, "USA");
    Product second = new Book(2, "Стихи", 1000, "Пушкин");
    Product third = new Smartphone(3, "iPhone-6", 200, "USA");
    Product fourth = new Book(4, "Android", 300, "China");

    @Test
    void shouldRemoveByIdSuccess() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);

        Product[] expected = new Product[]{second, third, fourth};
        assertArrayEquals(expected, repository.removeById(1));
    }

    @Test
    void shouldRemoveByIdFailed() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);

        assertThrows(NotFoundException.class, () -> repository.removeById(5));

    }


    @Test
    void shouldAdd() {

        manager.add(first);
        manager.add(second);

        Product[] expected = new Product[]{first, second};
        assertArrayEquals(expected, repository.findAll());

    }

    @Test
    void shouldSearchByOneProduct() {

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);

        Product[] expected = new Product[]{second};
        assertArrayEquals(expected, manager.searchBy("Пушкин"));

    }

    @Test
    void shouldSearchByTwoProduct() {

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);

        Product[] expected = new Product[]{first, third};
        assertArrayEquals(expected, manager.searchBy("USA"));

    }

    @Test
    void shouldSearchByProductNothing() {

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);

        Product[] expected = new Product[]{};
        assertArrayEquals(expected, manager.searchBy("Тургенев"));
    }

    @Test
    void shouldSearchByProductName() {

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);

        Product[] expected = new Product[]{fourth};
        assertArrayEquals(expected, manager.searchBy("Android"));
    }

    @Test
    void shouldSearchByProductNamePart() {

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);

        Product[] expected = new Product[]{first, third};
        assertArrayEquals(expected, manager.searchBy("iPhone"));
    }

    @Test
    void shouldMatches1() {
        assertTrue(manager.matches(first, "iPhone"));
    }

    @Test
    void shouldMatches2() {
        assertTrue(manager.matches(first, "iPho"));
    }

    @Test
    void shouldMatches3() {
        assertTrue(manager.matches(first, "USA"));
    }

    @Test
    void shouldMatches4() {
        assertFalse(manager.matches(first, "Пушкин"));
    }
}