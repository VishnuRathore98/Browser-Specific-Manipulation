import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class Test {
//    static WebDriver driver;

    public static void main(String[] args) {
        /* This will not only create an instance of chrome and open it but will also navigate to the
        *  provided web address.
        *  The chrome instance will continue to exist until we terminate the
        *  program.
        *  Simply closing the chrome will not terminate the created instance it will only
        *  close it.
        */
        WebDriverManager.chromedriver().create().get("https://www.google.com");


    }
}
