package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Topic_17_Button_Radio_Checkbox {
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
    public void TC_01_Fahasa_button() {
        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        sleepInSeconds(3);

        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));
        Assert.assertFalse(loginButton.isEnabled());

        Color loginButtonColor = Color.fromString(loginButton.getCssValue("background-color"));
        Assert.assertEquals(loginButtonColor.asHex().toUpperCase(),"#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("logintest@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        sleepInSeconds(3);

        loginButtonColor = Color.fromString(loginButton.getCssValue("background-color"));
        Assert.assertEquals(loginButtonColor.asHex().toUpperCase(),"#C92127");

    }
    @Test
    public void TC_02_Telerik_checkbox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        WebElement dualZoneCheckbox = driver.findElement(By.cssSelector("input#eq5"));
        dualZoneCheckbox.click();
        Assert.assertTrue(dualZoneCheckbox.isSelected());
        dualZoneCheckbox.click();
        Assert.assertFalse(dualZoneCheckbox.isSelected());

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        WebElement radioButton = driver.findElement(By.cssSelector("input#engine3"));
        radioButton.click();
        radioButton.isSelected();
        if (radioButton.isSelected()) {
            System.out.println("Radio button is selected");
        } else{
            radioButton.click();
        };


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
