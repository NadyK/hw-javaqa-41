package netology.repository;

import static org.junit.jupiter.api.Assertions.*;

import netology.domain.NotFoundException;
import netology.domain.Smartphone;
import org.junit.jupiter.api.Test;
import netology.domain.Book;
import netology.domain.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Book coreJava = new Book();
    private Smartphone smartphone = new Smartphone();
    Product first = new Smartphone(1, "iPhone-5", 100, "USA");
    Product second = new Book(2, "Стихи", 1000, "Пушкин");
    Product third = new Smartphone(3, "iPhone-6", 200, "USA");
    Product fourth = new Book(4, "Android", 300, "China");

    @Test
    public void shouldSaveOneItem() {
        repository.save(coreJava);
        repository.save(smartphone);
        Product[] expected = new Product[]{coreJava, smartphone};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveByIdFailed() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);

        assertThrows(NotFoundException.class, () -> repository.removeById(5));

    }

    @Test
    void shouldRemoveByIdSuccess() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);

        Product[] expected = new Product[]{second, third, fourth};
        assertArrayEquals(expected, repository.removeById(1));
    }

}