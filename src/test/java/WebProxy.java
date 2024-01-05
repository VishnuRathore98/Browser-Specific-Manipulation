// A simple proxy setup.

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebProxy {
    WebDriver driver;
    ChromeOptions options;
    Proxy proxy;
    String proxyStr;
    @BeforeAll
    public void setup(){
//      Create an instance of the class Proxy.
        proxy = new Proxy();
//      The syntax required to specify a proxy is host:port.
        proxyStr = "proxy:port";
//      We specify the is used for HTTP connections.
        proxy.setHttpProxy(proxyStr);
//      We also specify the proxy is used for HTTPS connections.
        proxy.setSslProxy(proxyStr);

        options = new ChromeOptions();
//      To accept insecure certifications.
        options.setAcceptInsecureCerts(true);
//      We set proxy as a capability.
//      options.setCapability(CapabilityType.PROXY, proxy);
        options.setProxy(proxy);

        driver = new ChromeDriver(options);
    }
    @Test
    public void test(){
        driver.get("https://www.google.com");
    }
    @AfterAll
    public void teardown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}