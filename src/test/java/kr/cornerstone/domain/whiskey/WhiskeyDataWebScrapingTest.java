package kr.cornerstone.domain.whiskey;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


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

    @Test
    @DisplayName("위스키 브랜드 정보 조회")
    void getWhiskeyBrands() {
        driver.get("https://www.whiskybase.com/whiskies/brands");
        List<WebElement> rows = driver.findElements(By.cssSelector("#compositor-material > table > tbody > tr"));

        for(int i = 0; i < 2; i++){
            String brandDetailLink = rows.get(i).findElement(By.cssSelector("td.clickable > a")).getAttribute("href");
            String brand = rows.get(i).findElement(By.className("clickable")).getText();
            String country = rows.get(i).findElement(By.className("data")).getText();
            String rating = rows.get(i).findElement(By.cssSelector("td:nth-child(5)")).getText();
            String rank = rows.get(i).findElement(By.cssSelector("td.data.text-center")).getText();

//            System.out.println(brandDetailLink);
//            System.out.println(brand);
//            System.out.println(country);
//            System.out.println(rating);
//            System.out.println(rank);

            assertThat(brand).isNotNull();
        }

    }

}