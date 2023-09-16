package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Exercise {
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
        driver.get("https://demo.nopcommerce.com/register");
    }

    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("LastName"));
    }
    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("ajax-loading-block-window"));
    }
    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("description"));
        System.out.println(driver.findElement(By.name("description")));
    }
    @Test
    public void TC_04_Tagname() {
        driver.findElements(By.tagName("select"));
        System.out.println(driver.findElements(By.tagName("select")));
    }
    @Test
    public void TC_05_LinkText() {
        driver.findElements(By.linkText("Register"));
    }
    @Test
    public void TC_06_Partial_LinkText() {
        driver.findElements(By.partialLinkText("Regi"));
    }
    @Test
    public void TC_07_Css() {
        //Css vs ID
        driver.findElements(By.cssSelector("input[id='LastName']"));
        driver.findElements(By.cssSelector("input#LastName"));
        driver.findElements(By.cssSelector("#LastName"));
        //Css vs Class
        driver.findElements(By.cssSelector("div[class='bar-notification-container']"));
        driver.findElements(By.cssSelector("div.bar-notification-container"));
        driver.findElements(By.cssSelector(".bar-notification-container"));
        //Css vs Name
        driver.findElements(By.cssSelector("select[name='customerCurrency']"));
        //Css vs tagName
        driver.findElements(By.cssSelector("select"));
        //Css vs Linktext
        driver.findElements(By.cssSelector("a[href='/login?returnUrl=%2Fregister']"));
        //Css vs Partial Linktext
        driver.findElements(By.cssSelector("a[href$='/login']"));
    }
    @Test
    public void TC_08_Xpath() {
        //Css vs ID
        driver.findElements(By.xpath("//input[@id='LastName']"));
        //Css vs Class
        driver.findElements(By.xpath("//div[@class='bar-notification-container']"));
        //Css vs Name
        driver.findElements(By.xpath("//select[@name='customerCurrency']"));
        //Css vs tagName
        driver.findElements(By.xpath("//select"));
        //Css vs Linktext
        driver.findElements(By.xpath("//a[@href='/login?returnUrl=%2Fregister']"));
        driver.findElements(By.xpath("//a[text()='Log in']"));
        //Css vs Partial Linktext
        driver.findElements(By.xpath("//a[contains(@href,'/login')]"));
        driver.findElements(By.xpath("//a[contains(text(),'Log in')]"));

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
