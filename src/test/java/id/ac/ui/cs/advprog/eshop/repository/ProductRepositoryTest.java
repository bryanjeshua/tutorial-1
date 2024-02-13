package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        // Your setup code goes here
    }

    @Test
    void testCreateAndFindById() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);
        Product productFound = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(productFound.getProductId(), product.getProductId());
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }
    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct1 = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct1.getProductId());

        Product savedProduct2 = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct2.getProductId());

        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteAndFoundOther() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        productRepository.delete("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct1 = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct1.getProductId());
    }

    @Test
    void failDelete() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        assertFalse(productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd7"));
    }

    @Test
    void testDeleteAndNotFoundItself() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct1 = productIterator.next();
        assertNotEquals(product1.getProductId(), savedProduct1.getProductId());
    }

    @Test
    void testEditName_And_NotFound_InitialData_AfterEditing() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product1_rev = new Product();
        product1_rev.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1_rev.setProductName("Sampo Cap Bambang Jilid 2");
        product1_rev.setProductQuantity(100);

        productRepository.update(product1_rev);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct1 = productIterator.next();
        assertNotEquals(product1, savedProduct1.getProductId());
    }
    @Test
    void testEditQuantityAndFoundChangedData() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product1_rev = new Product();
        product1_rev.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1_rev.setProductName("Sampo Cap Bambang");
        product1_rev.setProductQuantity(30);

        productRepository.update(product1_rev);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct1 = productIterator.next();
        assertEquals(product1_rev.getProductQuantity(), savedProduct1.getProductQuantity());
    }
    @Test
    void testEditButFailToFindProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product1_rev = new Product();
        product1_rev.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product1_rev.setProductName("Sampo Cap Bambang");
        product1_rev.setProductQuantity(30);

        assertNull(productRepository.update(product1_rev));
    }
}
