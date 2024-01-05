import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test {
//    static WebDriver driver;

//    public static void main(String[] args) {
        /* This will not only create an instance of chrome and open it but will also navigate to the
        *  provided web address.
        *  The chrome instance will continue to exist until we terminate the
        *  program.
        *  Simply closing the chrome will not terminate the created instance it will only
        *  close it.
        */
//        WebDriverManager.chromedriver().create().get("https://www.google.com");


//    }
    @org.junit.jupiter.api.Test
    public void test(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--use-fake-device-for-media-stream");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/get-user-media.html");
    }
}
