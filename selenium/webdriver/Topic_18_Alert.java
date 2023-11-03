package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_18_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    By result = By.cssSelector("p#result");
    String projectlocation = System.getProperty("user.dir");
    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30)) ;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_accept_alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS Alert");
        alert.accept();
        Assert.assertEquals(driver.findElement(result).getText(),"You clicked an alert successfully");
    }

    public void TC_02_confirm_alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS Confirm");
        alert.dismiss();
        Assert.assertEquals(driver.findElement(result).getText(),"You clicked: Cancel");

    }
    @Test
    public void TC_03_prompt_alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS prompt");
        String text = "uchiha_itachi";
        alert.sendKeys(text);
        alert.accept();
        Assert.assertEquals(driver.findElement(result).getText(),"You entered: " + text);

    }
    @Test
    public void TC_04_Authentication_alert() throws IOException {
//        String url = "http://the-internet.herokuapp.com/basic_auth";
//        url = "http://admin:admin@the-internet.herokuapp.com/basic_auth";
//        driver.get(url);
//        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
        Runtime.getRuntime().exec(new String[] {projectlocation + "\\AutoIT\\authen_firefox.exe", "admin", "admin"});
        driver.get("http://the-internet.herokuapp.com/basic_auth");
        sleepInSeconds(5);
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

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
