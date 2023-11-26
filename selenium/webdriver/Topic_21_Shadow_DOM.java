package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_21_Shadow_DOM {
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
    public void TC_01_Shadow_DOM() {
        driver.get("https://automationfc.github.io/shadow-dom/");
        WebElement shadowHost = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        Assert.assertEquals(shadowRoot.findElement(By.cssSelector("span.info")).getText(),"some text");

        shadowHost = shadowRoot.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowRoot = shadowHost.getShadowRoot();
        Assert.assertEquals(nestedShadowRoot.findElement(By.cssSelector("div#nested_shadow_content>div")).getText(),"nested text");
    }
    @Test
    public void TC_02_Shadow_DOM() {
        driver.get("https://shopee.vn/");
        sleepInSeconds(5);
        WebElement shadowHost = driver.findElement(By.cssSelector("div.home-page>shopee-banner-popup-stateful"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        shadowRoot = shadowRoot.findElement(By.cssSelector("div.home-popup__content>shopee-banner-simple")).getShadowRoot();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (shadowRoot.findElements(By.cssSelector("div.simple-banner img")).size() > 0 && shadowRoot.findElements(By.cssSelector("div.simple-banner img")).get(0).isDisplayed()){
            System.out.println("Pop up hiển thị");
            shadowRoot = shadowHost.getShadowRoot();
            shadowRoot.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
        }


    }


        public void TC_02_() {

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
