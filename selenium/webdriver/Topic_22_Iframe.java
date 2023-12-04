package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_22_Iframe {
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
    public void TC_01_iFrame() {
        driver.get("https://skills.kynaenglish.vn/");
        sleepInSeconds(3);
        WebElement iframe = driver.findElement(By.cssSelector("div.face-content>iframe"));
        driver.switchTo().frame(iframe);
        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'followers')]")).getText(),"170K followers");
        driver.switchTo().defaultContent();
        WebElement iframeChat = driver.findElement(By.cssSelector("iframe#cs_chat_iframe"));
        driver.switchTo().frame(iframeChat);
        driver.findElement(By.xpath("//div[@class='button_text']/following-sibling::div")).click();
        driver.findElement(By.cssSelector("input.input_name")).sendKeys("John Wick");
        driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0393061991");
        driver.findElement(By.cssSelector("select#serviceSelect")).click();
        Select dropDownList = new Select(driver.findElement(By.cssSelector("select#serviceSelect")));
        dropDownList.selectByVisibleText("TƯ VẤN TUYỂN SINH");
        driver.findElement(By.cssSelector("textarea.input_textarea[name='message']")).sendKeys("Java Bootcamp");
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
        driver.findElement(By.cssSelector("button.search-button")).click();
        List<WebElement> searchResults = driver.findElements(By.cssSelector("div.content h4"));
        for (WebElement searchResult : searchResults) {
//            System.out.println(searchResult.getText());
           Assert.assertTrue(searchResult.getText().contains("Excel"));
        }
    }
    @Test
    public void TC_02_iFrame() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("div#imageTemplateContainer")).click();
        WebElement iFrameSurvey = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));
        driver.switchTo().frame(iFrameSurvey);
        new Select(driver.findElement(By.xpath("//label[contains(text(),'Year')]/following-sibling::select"))).selectByVisibleText("Senior");
        new Select(driver.findElement(By.xpath("//label[contains(text(),'Residence')]/following-sibling::select"))).selectByVisibleText("South Dorm");
//        driver.findElement(By.xpath("//label[contains(text(),'Male')]/preceding-sibling::input")).click();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[contains(text(),'Male')]/preceding-sibling::input")));
        driver.findElement(By.cssSelector("input#FSsubmit")).click();
        driver.switchTo().parentFrame();
        WebElement loginButton = driver.findElement(By.cssSelector("nav.header--desktop.js-header a[title='Log in']"));
        executor.executeScript("window.scrollTo(0,358);", loginButton);
        sleepInSeconds(5);
        loginButton.click();
        driver.findElement(By.cssSelector("button#login")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");
    }
    @Test
    public void TC_03_Frame() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='login_page']")));
        driver.findElement(By.cssSelector("div.inputfield>input")).sendKeys("automation");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSeconds(5);
        driver.switchTo().defaultContent();
        Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());
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
