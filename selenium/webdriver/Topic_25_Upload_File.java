package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_25_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    Actions actions;

    String character = Platform.getCurrent().is(Platform.WINDOWS) ? "\\" : "/";

    String seaName01 = "sea1.jpg";
    String seaName02 = "sea2.jpg";
    String seaName03 = "sea3.jpg";
    String image01Path = projectPath + character + "image_file"  + character + seaName01;
    String image02Path = projectPath + character + "image_file"  + character + seaName02;
    String image03Path = projectPath + character + "image_file"  + character + seaName03;


    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Upload_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(image01Path +"\n" + image02Path+ "\n" +image03Path);
        sleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + seaName01 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ seaName02 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + seaName03 + "']")).isDisplayed());

        List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']/ancestor::button"));
        for (WebElement startButton : startButtons) {
            startButton.click();
            sleepInSeconds(2);
        }
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + seaName01 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ seaName02 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + seaName03 + "']")).isDisplayed());

    }

    public void TC_02_() {

    }
    @AfterClass
    public void afterClass() {

        driver.quit();
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
