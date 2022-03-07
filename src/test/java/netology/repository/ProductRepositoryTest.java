package netology.repository;

import static org.junit.jupiter.api.Assertions.*;

import netology.domain.Smartphone;
import org.junit.jupiter.api.Test;
import netology.domain.Book;
import netology.domain.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Book coreJava = new Book();
    private Smartphone smartphone = new Smartphone();

    @Test
    public void shouldSaveOneItem() {
        repository.save(coreJava);
        repository.save(smartphone);
        Product[] expected = new Product[]{coreJava, smartphone};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
}