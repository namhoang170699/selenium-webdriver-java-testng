package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Selenium_Xpath {
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
        driver.get("https://demo.nopcommerce.com/electronics");
    }

    @Test
    public void TC_01_class() {
        driver.findElement(By.xpath("//button[@class='button-1 search-box-button']"));
    }
    @Test
    public void TC_02_id() {
        driver.findElement(By.xpath("//select[@id='customerCurrency']"));
    }
    @Test
    public void TC_03_name() {
        driver.findElement(By.xpath("//input[@name='NewsletterEmail']"));
    }
    @Test
    public void TC_04_linktext() {
        driver.findElement(By.xpath("//a[contains(@href,'/sitemap')]"));
        driver.findElement(By.xpath("//a[contains(text(),'Sitemap')]"));
        driver.findElement(By.xpath("//a[text()='Sitemap']"));
        driver.findElement(By.xpath("//a[@href='/sitemap']"));

    }
    @Test
    public void TC_05_img() {
        driver.findElement(By.xpath("//img[@src='https://demo.nopcommerce.com/images/thumbs/0000008_accessories_450.jpg']"));

    }
    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
