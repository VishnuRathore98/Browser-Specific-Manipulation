import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LocalizationTest {

    WebDriver driver;
    String lang;
    Map<String, Object>  prefs;
    ChromeOptions options;

    @BeforeAll
    public void setup(){
        lang = "es-ES";
        prefs = new HashMap<>();

        options = new ChromeOptions();

//      We specify European Spanish as the preferred language in Chrome.
        prefs.put("intl.accept_languages", lang);

        options.setExperimentalOption("prefs",prefs);

        driver = new ChromeDriver(options);
    }

    @Test
    public void test(){
//      We open a practice page that supports multilanguage (English and Spanish).
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/multilanguage.html");

//      We read the text translations using a resource bundle. You can find these strings in the file strings_es.properties
//      in the project folder src/test/resources.
        ResourceBundle strings = ResourceBundle.getBundle("strings", Locale.forLanguageTag(lang));

        String home = strings.getString("home");
        String content = strings.getString("content");
        String about = strings.getString("about");
        String contact = strings.getString("contact");

        String bodyText = driver.findElement(By.tagName("body")).getText();

//      We assert that the document body contains all the expected strings.
        assertThat(bodyText).contains(home).contains(content).contains(about).contains(contact);
    }

    @Disabled
    @AfterAll
    public void teardown() throws InterruptedException{
        Thread.sleep(3000);
        driver.quit();

    }

}


















