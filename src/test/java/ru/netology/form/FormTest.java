package ru.netology.form;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormTest {

 private WebDriver driver;

    @BeforeAll
    public static void setUpAll() {
//        System.setProperty("webdriver.chrome.driver", "./driver/win/chromedriver.exe");

    WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options =new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @org.junit.jupiter.api.Test
    public void shouldsendForm() throws InterruptedException {
        driver.get("http://localhost:9999/");


        driver.findElement(By.cssSelector("[data-test-id=\"name\"] input")).sendKeys("Арчи Марчи");
        driver.findElement(By.cssSelector("[data-test-id=\"phone\"] input")).sendKeys("+79024560869");//        List<WebElement> textfields = driver.findElements(By.className("input__control"));
//        textfields.get(0).sendKeys("Арчи Марчи");
//        textfields.get(1).sendKeys("+79024567896");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();
        String actualAnswer = driver.findElement(By.className("Success_successBlock__2L3Cw")).getText().trim();
        String expectedAnswer = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expectedAnswer, actualAnswer, "текст ответа не совпадает");

    }
}
