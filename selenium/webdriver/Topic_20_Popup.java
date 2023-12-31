package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_20_Popup {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    Actions actions;

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
    public void TC_01_Fixed_Popup_InDom(){
        driver.get("https://ngoaingu24h.vn/");
        actions.click(driver.findElement(By.cssSelector("button.login_"))).perform();
        sleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]")).isDisplayed());
        actions.sendKeys(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#account-input")),"automationfc").perform();
        actions.sendKeys(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#password-input")),"automationfc").perform();
        actions.click(driver.findElement(By.xpath("//div[@id='modal-login-v1'][@style]//button[text()='Đăng nhập']"))).perform();
        sleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.error-login-panel")).isDisplayed());
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
        sleepInSeconds(2);
        Assert.assertFalse(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]")).isDisplayed());
    }
    @Test
    public void TC_02_Fixed_Popup_InDom() {
        driver.get("https://skills.kynaenglish.vn/");
        actions.click(driver.findElement(By.cssSelector("a.login-btn"))).perform();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.right")).isDisplayed());
        sleepInSeconds(3);
        actions.sendKeys(driver.findElement(By.cssSelector("input#user-login")),"automation@gmail.com").perform();
        actions.sendKeys(driver.findElement(By.cssSelector("input#user-password")),"123456").perform();
        actions.click(driver.findElement(By.cssSelector("button#btn-submit-login"))).perform();
        sleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("div#password-form-login-message")).isDisplayed());
        actions.click(driver.findElement(By.cssSelector("button[class='k-popup-account-close close']"))).perform();
        sleepInSeconds(3);
        Assert.assertFalse(driver.findElement(By.cssSelector("div.right")).isDisplayed());

    }

    @Test
    public  void TC_03_Fixed_Popup_Not_InDom() {
        driver.get("https://tiki.vn/");
        driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][1]")).getText(),"Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][2]")).getText(),"Mật khẩu không được để trống");
        driver.findElement(By.cssSelector("button.btn-close")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(),0);

    }
    @Test
    public  void TC_04_Fixed_Popup_Not_InDom() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']/ancestor::div[@class='_8ien']")).isDisplayed());
        driver.findElement(By.xpath("//div[text()='Sign Up']/ancestor::div[@class='_8ien']/img")).click();
        sleepInSeconds(3);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']/ancestor::div[@class='_8ien']")).size(),0);
    }
    @Test
    public  void TC_05_Random_Popup_Not_InDom() {
        driver.get("https://www.javacodegeeks.com/");
        List<WebElement> popUp = driver.findElements(By.cssSelector("div#lepopup-popup-122>div:not([style^='display:none'])"));
        if (popUp.size() > 0 && popUp.get(0).isDisplayed()) {
            System.out.println("Pop up hiển thị");
            driver.findElement(By.cssSelector("div.lepopup-element-23 a[onclick*='lepopup_close']")).click();

        } else {
            System.out.println("Pop up không hiển thị");
        }
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
        driver.findElement(By.cssSelector("button#search-submit")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.post-114701 h2>a")).getText(),"Agile Testing Explained");
    }
    @Test
    public  void TC_06_Random_Popup_InDom() {
        driver.get("https://vnk.edu.vn/");
        By marketingPopUp = By.cssSelector("div.tve-leads-conversion-object");
        if (driver.findElement(marketingPopUp).isDisplayed()) {
            System.out.println("Pop up hiển thị");
            driver.findElement(By.cssSelector("div.tve_ea_thrive_leads_form_close")).click();
        } else {
            System.out.println("Pop up không hiển thị");
        }
    }
    @Test
    public  void TC_07_Random_Popup_InDom() {
        driver.get("https://dehieu.vn/");
        sleepInSeconds(10);
        By popUp = By.cssSelector("div.popup-content");
        if (driver.findElements(popUp).size() > 0 && driver.findElements(popUp).get(0).isDisplayed()){
            System.out.println("Pop up hiển thị");
            driver.findElement(By.cssSelector("button#close-popup")).click();
        } else {
            System.out.println("Pop up ko hiển thị");
        }
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
