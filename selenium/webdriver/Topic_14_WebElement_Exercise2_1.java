package webdriver;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_14_WebElement_Exercise2_1 {
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
    public void TC_01_Login_Empty_Email_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
        sleepInSeconds(3);
        driver.findElement(By.xpath("//span[text()='Login']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div#advice-required-entry-email")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),"This is a required field.");

        Assert.assertTrue(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),"This is a required field.");


    }
    @Test
    public void TC_02_Login_Invalid_Email() {
        //Step 01: Open URL
        driver.get("http://live.techpanda.org/");
        //Step 02: click button My Account
        driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
        sleepInSeconds(3);
        //Input invalid email
        driver.findElement(By.cssSelector("input#email")).sendKeys("123434234@12312.123123");
        //Input valid password
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
        //Click login button
        driver.findElement(By.xpath("//span[text()='Login']")).click();
        //Verify error message is displayed
        Assert.assertTrue(driver.findElement(By.cssSelector("div#advice-validate-email-email")).isDisplayed());
        //Verify error message
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");

    }

    @Test
    public void TC_03_Login_Invalid_Password() {
        //Step 01: Open URL
        driver.get("http://live.techpanda.org/");
        //Step 02: Click button My Account
        driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
        //Input valid email
        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
        //Input invalid password
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");
        //Click login button
        driver.findElement(By.xpath("//span[text()='Login']")).click();
        //Verify error message is displayed
        Assert.assertTrue(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).isDisplayed());
        //Verify error message
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");

    }

    @Test
    public void TC_04_Login_Incorrect_Email_Password() {
        //Step 01: Open URL
        driver.get("http://live.techpanda.org/");
        //Step 02: Click button My Account
        driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
        //Input valid email
        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
        //Input incorrect password
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123123123");
        //Click login button
        driver.findElement(By.xpath("//span[text()='Login']")).click();
        //Verify error message is displayed
        Assert.assertTrue(driver.findElement(By.cssSelector("li.error-msg span")).isDisplayed());
        //Verify error message
        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),"Invalid login or password.");

    }

    @Test
    public void TC_05_Register_New_Account() {
        String firstName = "Nam";
        String lastName ="Hoang";
        String password = "123123123";
        String email = getEmailAddress();
        String fullName = firstName +" " + lastName;

        //Step 01: Open URL
        driver.get("http://live.techpanda.org/");
        //Step 02: Click button My Account
        driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
        sleepInSeconds(3);
        //Step 03: Click Create an Account button
        driver.findElement(By.cssSelector("a[title='Create an Account'")).click();
        sleepInSeconds(3);
        //Step 04: Input firstName
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        //Step 05: Input lastName
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        //Step 06: Input email
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(email);
        //Step 07: Input password
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        //Step 08: Input confirm password
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        //Step 09: Click register button
        driver.findElement(By.xpath("//span[text()='Register']")).click();
        sleepInSeconds(5);
        //Step 10: Verify message
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"Thank you for registering with Main Website Store.");
        //Step 11: Verify Firstname, L
        // astname, Email is displayed
        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(),"Hello, " + fullName + "!");

        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div//following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(email));
        //Step 12: Log out
        driver.findElement(By.cssSelector("a.skip-account")).click();
        driver.findElement(By.cssSelector("a[title='Log Out']")).click();
        sleepInSeconds(5);
        //Step 13: Log in
        driver.findElement(By.cssSelector("a.skip-link.skip-account span")).click();
        driver.findElement(By.cssSelector("a[title='Log In']")).click();
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("input#email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#pass")).sendKeys(password);
        driver.findElement(By.xpath("//span[text()='Login']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(),"Hello, " + fullName + "!");

        contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div//following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(email));

        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"),email);


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
