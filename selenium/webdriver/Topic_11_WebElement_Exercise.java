package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_11_WebElement_Exercise {
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
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//span[text()='Company']")).getCssValue("line-height");
        System.out.println(driver.findElement(By.xpath("//span[text()='Company']")).getCssValue("line-height"));
    }
    @Test
    public void TC_02_() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("h2 img")).getAttribute("src");
        System.out.println(driver.findElement(By.cssSelector("h2 img")).getAttribute("src"));
    }
    @Test
    public void TC_03_() {
        driver.get("http://live.techpanda.org/index.php/customer/account/create/");
        driver.findElement(By.id("is_subscribed")).click();
        boolean checkbox = driver.findElement(By.id("is_subscribed")).isSelected();
        Assert.assertTrue(checkbox);
    }

    @Test
    public void TC_04_() {
        driver.get("http://live.techpanda.org/index.php/customer/account/create/");
        boolean textBox = driver.findElement(By.id("newsletter")).isEnabled();
        Assert.assertTrue(textBox);
    }
    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
