package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_23_Windows_Tab {
    WebDriver driver;
    WebDriverWait explicitWait;
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
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30)) ;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Window_Google() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        String parentID = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        switchWindow(parentID);
        sleepInSeconds(3);
        Assert.assertEquals(driver.getTitle(),"Google");
        driver.switchTo().window(parentID);
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        switchMultipleWindows(parentID,"Facebook – log in or sign up");
        sleepInSeconds(3);
        Assert.assertEquals(driver.getTitle(),"Facebook – log in or sign up");
        sleepInSeconds(3);
        driver.switchTo().window(parentID);
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        switchMultipleWindows(parentID, "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        Assert.assertEquals(driver.getTitle(),"Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        switchWindows_close(parentID);
        sleepInSeconds(3);
        Assert.assertEquals(driver.getCurrentUrl(),"https://automationfc.github.io/basic-form/index.html");
    }
    @Test
    public void TC_02_Kyna() {
        driver.get("https://skills.kynaenglish.vn/");
        String parentID = driver.getWindowHandle();
        clickToElementByJS("div#k-footer img[alt='facebook']");
        clickToElementByJS("div#k-footer img[alt='youtube']");
        switchMultipleWindows(parentID, "Kyna.vn - YouTube");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.youtube.com/user/kynavn");
        switchMultipleWindows(parentID,"Kyna.vn | Ho Chi Minh City  | Facebook");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/kyna.vn");
        Assert.assertTrue(switchWindows_close(parentID));
    }
    @Test
    public void TC_03_TechPanda() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[@title='Xperia']/following-sibling::div//a[@class='link-compare']")).click();
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.success-msg")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Sony Xperia has been added to comparison list.");
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div//a[@class='link-compare']")).click();
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.success-msg")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Samsung Galaxy has been added to comparison list.");
        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        sleepInSeconds(2);
        String parentID = driver.getWindowHandle();
        switchWindow(parentID);
        Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");
        switchWindows_close(parentID);
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The comparison list was cleared.");

    }
    @Test
    public void TC_04_Dictionary() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://dictionary.cambridge.org/vi/");
//        if(driver.findElements(By.cssSelector("div#card")).size() > 0) {
//            driver.findElement(By.cssSelector("div#card div#dismiss-button")).click();
//        }
        if(driver.findElements(By.xpath("//div[contains(text(),'Start your literary journey with the Cambridge Book Club. Find out more:')]/ancestor::div[@aria-label='Survey']")).size() > 0){
            driver.findElement(By.xpath("//div[contains(text(),'Start your literary journey with the Cambridge Book Club. Find out more:')]/ancestor::div[@aria-label='Close']")).click();
        }
        driver.findElement(By.cssSelector("span.cdo-login-button")).click();
        String parentID = driver.getWindowHandle();
        switchWindow(parentID);
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("input[value='Log in']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'login')]//input[@placeholder='Email *']/following-sibling::span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'login')]//input[@placeholder='Password *']/following-sibling::span")).isDisplayed());
        switchWindows_close(parentID);

        driver.findElement(By.cssSelector("input#searchword")).sendKeys("automation");
        driver.findElement(By.cssSelector("button.cdo-search-button")).click();
        sleepInSeconds(3);
    }
    @Test
    public void TC_05_() {

    }
    @AfterClass
    public void afterClass() {

        driver.quit();
    }
    public void switchWindow(String parentID){
        Set<String> tabs = driver.getWindowHandles();
        for (String tab : tabs) {
            if(!tab.equals(parentID)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        sleepInSeconds(3);
    }
    public void switchMultipleWindows(String parentID, String expectedTitle){
        Set<String> tabsID = driver.getWindowHandles();
        for (String tabID : tabsID) {
            if(!tabID.equals(parentID)) {
                driver.switchTo().window(tabID);
                sleepInSeconds(3);
                String currentTitle = driver.getTitle();
                if(currentTitle.equals(expectedTitle)){
                    break;
                }
            }
        }

    }
    public boolean switchWindows_close(String parentID){
        Set<String> tabs = driver.getWindowHandles();
        for (String tab : tabs) {
            if(!tab.equals(parentID)) {
                driver.switchTo().window(tab);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        if(driver.getWindowHandles().size() == 1) {
            return true;
        } else {
            return false;
        }
    }
    public void clickToElementByJS(String locator) {
        WebElement element = driver.findElement(By.cssSelector(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",element);
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
