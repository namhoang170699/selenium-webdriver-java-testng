package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_07_Xpath_Css_Exercise {
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
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();
        String nameErrorMsg = driver.findElement(By.id("txtFirstname-error")).getText();
        Assert.assertEquals(nameErrorMsg,"Vui lòng nhập họ tên");

        String emailErrorMsg = driver.findElement(By.id("txtEmail-error")).getText();
        Assert.assertEquals(emailErrorMsg,"Vui lòng nhập email");

        String cEmailErrorMsg = driver.findElement(By.id("txtCEmail-error")).getText();
        Assert.assertEquals(cEmailErrorMsg,"Vui lòng nhập lại địa chỉ email");

        String paswordErrorMsg = driver.findElement(By.id("txtPassword-error")).getText();
        Assert.assertEquals(paswordErrorMsg,"Vui lòng nhập mật khẩu");

        String cPasswordErrorMsg = driver.findElement(By.id("txtCPassword-error")).getText();
        Assert.assertEquals(cPasswordErrorMsg,"Vui lòng nhập lại mật khẩu");

        String phoneErrorMsg = driver.findElement(By.id("txtPhone-error")).getText();
        Assert.assertEquals(phoneErrorMsg,"Vui lòng nhập số điện thoại.");
    }

    public void TC_02_() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("Joe Biden");
        driver.findElement(By.id("txtEmail")).sendKeys("a@@a.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("a@@a.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456a@");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456a@");
        driver.findElement(By.id("txtPhone")).sendKeys("0394567890");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

        String emailErrorMsg = driver.findElement(By.id("txtEmail-error")).getText();
        Assert.assertEquals(emailErrorMsg,"Vui lòng nhập email");


    }
    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}