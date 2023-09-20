package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_Xpath_inside_outside_parent {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://live.techpanda.org/index.php/mobile.html");
    }

    @Test
    public void TC_01_index() {
        driver.findElement(By.xpath("(//a[@class='product-image'])[1]"));
        driver.findElement(By.xpath("(//a[@class='product-image'])[2]"));
        driver.findElement(By.xpath("(//a[@class='product-image'])[3]"));
    }

    public void TC_02_() {

    }
    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
