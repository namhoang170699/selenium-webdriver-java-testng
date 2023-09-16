package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
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
    //TestNG: Order testcase theo Alphabet
    // Firstname textbox - HTML code
    // HTML
    /*
    *<input type="text" data-val="true" data-val-required="First name is required." id="FirstName" name="FirstName">
     */

    @Test
    public void TC_01_ID() {
        // Tìm element có id là FirstName
        driver.findElement(By.id("FirstName")).sendKeys("Namprovip");
        System.out.println(driver.findElement(By.id("FirstName")));
    }
    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("header-logo"));
    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("DateOfBirthDay"));
    }
    @Test
    public void TC_04_Tagname() {
        driver.findElements(By.tagName("input"));
    }
    @Test
    public void TC_05_LinkText() {
        // Độ chính xác cao = tuyệt đối = lấy toàn bộ
        driver.findElement(By.linkText("Shipping & returns"));
    }
    @Test
    public void TC_06_Partial_Link() {
        // Độ chính xa ko cao = tương đối = lấy 1 phần
        driver.findElement(By.partialLinkText("vendor account"));
    }
    @Test
    public void TC_07_CSS() {
    // Css vs ID
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("input[id=FirstName]"));
        driver.findElement(By.cssSelector("#FirstName"));
    // Css vs Class
        driver.findElement(By.cssSelector("div[class='page-title"));
        driver.findElement(By.cssSelector("div.page-title"));
        driver.findElement(By.cssSelector(".page-title"));
     // Css vs Name
        driver.findElement(By.cssSelector("input[name='FirstName']"));
     // Css vs tagname
        driver.findElement(By.cssSelector("input"));
     // Css vs Partial link
        driver.findElement(By.cssSelector("a[href*='addresses']"));
//        driver.findElement(By.cssSelector("a[href^='addresses']"));
//        driver.findElement(By.cssSelector("a[href$='addresses']"));

    }
    @Test
    public void TC_08_Xpath() {
        // Xpath vs ID
        driver.findElement(By.xpath("//input[@id='FirstName']"));
        // Xpath vs Class
        driver.findElement(By.xpath("//div[@class='page-title']"));
        // Css vs Name
        driver.findElement(By.xpath("//input[@name='FirstName']"));
        // Xpath vs TagName
        driver.findElement(By.xpath("//input"));
        // Xpath vs Link
        driver.findElement(By.xpath("//a[@href='/customer/addresses']"));
        driver.findElement(By.xpath("//a[text()='Addresses']"));

        // Xpath vs Partial link
        driver.findElement(By.xpath("//a[contains(@href,'addresses')]"));
        driver.findElement(By.xpath("//a[contains(text(),'Addresses')]"));
    }
}
