import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.html5.LocationContext;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.CheckReturnValue;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Geolocation {

    WebDriver driver;

    @BeforeAll
    public void setup(){
//        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        Map<String ,Object> preference = new HashMap<>();
        preference.put("profile.default_content_setting_values.geolocation" ,1);
        options.setExperimentalOption("prefs",preference);
        driver = WebDriverManager.chromedriver().capabilities(options).create();
    }
    @Test
    public void test() throws InterruptedException {
        LocationContext location = (LocationContext) driver;
        location.setLocation(new Location(27.5916, 86.5640,0));

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/geolocation.html");
        driver.findElement(By.id("get-coordinates")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement coordinates = driver.findElement(By.id("coordinates"));
        wait.until(ExpectedConditions.visibilityOf(coordinates));
        System.out.println("\n"+coordinates.getText());
        Thread.sleep(3000);
    }
    @AfterAll
    public void teardown(){
//        driver.quit();
    }

}
