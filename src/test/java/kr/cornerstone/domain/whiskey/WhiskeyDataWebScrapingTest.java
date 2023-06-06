package kr.cornerstone.domain.whiskey;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

class WhiskeyDataWebScrapingTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("headless");
        driver = new ChromeDriver(chromeOptions);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @DisplayName("위스키 브랜드 정보 조회")
    @Test
    void test() {
        driver.get("https://www.whiskybase.com/whiskies/brands");
        List<WebElement> rows = driver.findElements(By.cssSelector("#compositor-material > table > tbody > tr"));

        for(int i = 0; i < 10; i++){
            String brand = rows.get(i).findElement(By.className("clickable")).getText();
            String country = rows.get(i).findElement(By.className("data")).getText();
            String rating = rows.get(i).findElement(By.cssSelector("td:nth-child(5)")).getText();
            String rank = rows.get(i).findElement(By.cssSelector("td.data.text-center")).getText();

            System.out.println(brand);
            System.out.println(country);
            System.out.println(rating);
            System.out.println(rank);
        }

    }

}