package chromedevtools;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v118.performance.Performance;
import org.openqa.selenium.devtools.v118.performance.model.Metric;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PerformanceMetricsTest {
    WebDriver driver;
    DevTools devTools;
    List<Metric> metrics;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        devTools = ((ChromeDriver)driver).getDevTools();
        devTools.createSession();
    }

    @Test
    public void test(){
//      We enable collecting metrics.
        devTools.send(Performance.enable(Optional.empty()));
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

//      We gather all metrics.
        metrics = devTools.send(Performance.getMetrics());
        assertThat(metrics).isNotEmpty();
        metrics.forEach(metric -> System.out.println("Name: "+metric.getName()+"\n Value: "+metric.getValue()));
    }

    @AfterEach
    public void teardown()throws InterruptedException{
        Thread.sleep(3000);
        devTools.close();
        driver.quit();
    }

}
