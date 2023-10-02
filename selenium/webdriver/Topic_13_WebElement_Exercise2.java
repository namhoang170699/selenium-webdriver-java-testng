package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_13_WebElement_Exercise2 {
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
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if (driver.findElement(By.cssSelector("input#mail")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation Testing");
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }


        if (driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()) {
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }


        if (driver.findElement(By.cssSelector("input#under_18")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }


        if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }


//        Assert.assertTrue(driver.findElement(By.cssSelector("input#mail")).isDisplayed());
//        Assert.assertTrue(driver.findElement(By.cssSelector("label[for='under_18']")).isDisplayed());
//        Assert.assertTrue(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed());
//        Assert.assertFalse(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed());
    }

    @Test
    public void TC_02_() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if (driver.findElement(By.cssSelector("input#mail")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }


        if (driver.findElement(By.cssSelector("textarea#edu")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }


        if (driver.findElement(By.cssSelector("input#under_18")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.cssSelector("select#job1")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.cssSelector("select#job2")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.cssSelector("input#development")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.cssSelector("input#slider-1")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.cssSelector("input#password")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.cssSelector("input#password")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.cssSelector("input#radio-disabled")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.cssSelector("textarea#bio")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.cssSelector("select#job3")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.cssSelector("input#check-disbaled")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.cssSelector("input#slider-2")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }
    }
    @Test
    public void TC_03_() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.cssSelector("input#under_18")).click();
        driver.findElement(By.cssSelector("input#java")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#java")).isSelected());
        driver.findElement(By.cssSelector("input#java")).click();

        Assert.assertFalse(driver.findElement(By.cssSelector("input#java")).isSelected());

    }
    @Test
    public void TC_04_() {
        driver.get("https://login.mailchimp.com/signup/");
        WebElement email = driver.findElement(By.cssSelector("input#email"));
        email.clear();
        email.sendKeys("hoangnam@gmail.com");


        WebElement password = driver.findElement(By.cssSelector("input#new_password"));

        //Number
        password.clear();
        password.sendKeys("1");
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

        //Lowercase
        password.clear();
        password.sendKeys("a");
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

        password.clear();
        password.sendKeys("A");
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

        password.clear();
        password.sendKeys("@");
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

        password.clear();
        password.sendKeys("1234567@aA");
        sleepInSeconds(2);
        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
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
