package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    private final String baseUrl = "http://localhost:8080/";

    @Test
    void initialProductListPage_isCorrect (ChromeDriver driver) {
        driver.get(baseUrl + "list");
        String windowTitle = driver.findElement(By.tagName("h2")).getText();
        String tbodyContent = driver.findElement(By.tagName("tbody")).getText();

        assertEquals("Product List", windowTitle);
        assertEquals("",tbodyContent);
    }

    @Test
    void createProduct(ChromeDriver driver) {
        driver.get(baseUrl + "list");
        driver.findElement(By.linkText("Create Product")).click();
        driver.findElement(By.name("productName")).sendKeys("Air Murni Alam");
        driver.findElement(By.name("productQuantity")).sendKeys("17");
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
        String tbodyContent = driver.findElement(By.tagName("td")).getText();
        assertEquals("Air Murni Alam",tbodyContent);
    }
    @Test
    void cancelCreateProduct(ChromeDriver driver) {
        driver.get(baseUrl + "list");
        driver.findElement(By.linkText("Create Product")).click();
        driver.findElement(By.name("productName")).sendKeys("Air Murni Sungai");
        driver.findElement(By.name("productQuantity")).sendKeys("17");
        driver.get(baseUrl + "list");
        String tbodyContent = driver.findElement(By.tagName("tbody")).getText();
        assertNotEquals("Air Murni Sungai",tbodyContent);
    }
}
