package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;




import java.util.concurrent.TimeUnit;

public class Topic_03_Assert {
    WebDriver driver;

    @Test
    public void verifyTestNG() {
        driver = new FirefoxDriver();

        driver.get("https://www.facebook.com/");

        //Trong Java có nhiều thư viện để verify dữ liệu
        // Testing framework (Unit/ Integration/ UI Automation Test)
        // JUnit 4/ TestNG /JUNit 5/ Hamcrest/ AssertJ..

        // Kiểu dữ liệu nhận vào là boolean (true/ false)
        // Khi mong muốn điều kiện trả về là true thì dùng assertTrue để verify
        Assert.assertTrue(driver.getPageSource().contains(""));

        // Mong muốn điều kiện trả về là sai thì dùng assertFalse
        Assert.assertFalse(driver.getPageSource().contains(""));

        // Các hàm trả về kiểu dữ liệu là boolean
        // Quy tắc: bắt đầu vs tiền tố là isXXX
        // WebElement
        driver.findElements(By.id(""));
    }
}
