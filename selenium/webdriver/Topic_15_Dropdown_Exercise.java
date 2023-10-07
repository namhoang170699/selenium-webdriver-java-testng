package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_15_Dropdown_Exercise {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    String firstName = "Nam", lastName = "Hoang";
    String email = getEmailAddress();
    String password = "123123123";
    String day = "1", month  = "May", year = "1980";

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
    public void TC_01_Register() {
        driver.get("https://demo.nopcommerce.com/register");
        Select dayRegister = new Select(driver.findElement(By.name("DateOfBirthDay")));
        Select monthRegister = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        Select yearRegister = new Select(driver.findElement(By.name("DateOfBirthYear")));


        Assert.assertFalse(dayRegister.isMultiple());
        Assert.assertFalse(monthRegister.isMultiple());
        Assert.assertFalse(yearRegister.isMultiple());

        driver.findElement(By.cssSelector("input#gender-male")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);

        Assert.assertEquals(dayRegister.getOptions().size(),32);
        Assert.assertEquals(monthRegister.getOptions().size(),13);
        Assert.assertEquals(yearRegister.getOptions().size(),112);

        dayRegister.selectByVisibleText(day);
        monthRegister.selectByVisibleText(month);
        yearRegister.selectByVisibleText(year);
        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);
        driver.findElement(By.cssSelector("button#register-button")).click();
        sleepInSeconds(5);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");



    }
    @Test
    public void TC_02_Login() {
        driver.get("https://demo.nopcommerce.com/login");
        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        sleepInSeconds(5);
        driver.findElement(By.cssSelector("a.ico-account")).click();
        sleepInSeconds(5);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(),day);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(),month);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(),year);
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

    public String getEmailAddress() {
        return "nam" + new Random().nextInt(99999) + "@gmail.com";

    }
}
