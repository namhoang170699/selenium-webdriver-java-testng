package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_05_Xpath_Text {
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
        driver.get("https://automationfc.github.io/basic-form/");
    }

    @Test
    public void TC_01_contains_text() {
        driver.findElement(By.xpath("//h1[contains(text(),'Login or Create an Account')]"));
    }
    @Test
    public void TC_02_text() {
        driver.findElement(By.xpath("//h2[text()='Already registered?']"));
    }
    @Test
    public void TC_03_concat() {
        driver.findElement(By.xpath("//span[text()=concat('Hello \"John\"',\", What's happened?\")]"));
    }
    @Test
    public void TC_04_string() {
        driver.findElement(By.xpath("//h5[@id='eight' and contains(string(),'Michael Jackson')]"));
    }
    @Test
    public void TC_05_getText() {
       String text = driver.findElement(By.xpath("//h5[@id='nested']")).getText();
        System.out.println(text);
        Assert.assertTrue(text.equals("Hello World! (Ignore Me) @04:45 PM"));
    }
    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
