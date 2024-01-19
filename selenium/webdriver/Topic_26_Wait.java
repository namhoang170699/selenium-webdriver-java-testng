package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Time;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Topic_26_Wait {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    String character = Platform.getCurrent().is(Platform.WINDOWS) ? "\\" : "/";

    String seaName01 = "sea1.jpg";
    String seaName02 = "sea2.jpg";
    String seaName03 = "sea3.jpg";
    String image01Path = projectPath + character + "image_file"  + character + seaName01;
    String image02Path = projectPath + character + "image_file"  + character + seaName02;
    String image03Path = projectPath + character + "image_file"  + character + seaName03;

    Actions actions;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        actions = new Actions(driver);
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Implicit_Wait() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("div#start>button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @Test
    public void TC_02_Static_Wait() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        sleepInSeconds(5);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }
    @Test
    public void TC_03_Explicit_Wait_Invisible() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#start>button")));
        driver.findElement(By.cssSelector("div#start>button")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }
    @Test
    public void TC_04_Explicit_Wait_Visible() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#start>button")));
        driver.findElement(By.cssSelector("div#start>button")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }
    @Test
    public void TC_05_Explicit_Wait_telerik() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.demo-container")));
        System.out.println(driver.findElement(By.cssSelector("div.datesContainer span")).getText());
        driver.findElement(By.cssSelector("td[title='Monday, January 08, 2024']>a")).click();
//        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='datesContainer']//span[text()='Monday, January 8, 2024']")));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.raDiv")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[title='Monday, January 08, 2024'][class='rcSelected']")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.datesContainer span")).getText(),"Monday, January 8, 2024");
    }
    @Test
    public void TC_06_Explicit_Wait_Gofile() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://gofile.io/?t=uploadFiles");
        driver.findElement(By.cssSelector("a.ajaxLink>button")).click();
        driver.findElement(By.cssSelector("input#filesUploadInput")).sendKeys(image01Path);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mainUploadSuccess i")));
        String goFileEndPoint = driver.findElement(By.cssSelector("div.mainUploadSuccessLink a[class='ajaxLink']")).getAttribute("href");
        driver.switchTo().newWindow(WindowType.WINDOW);
//        System.out.println("https://gofile.io"+goFileEndPoint);
        driver.get(goFileEndPoint);
//        driver.navigate().to("https://gofile.io" + goFileEndPoint)
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='filesContentTable']//button//span[text()='Download']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='filesContentTable']//button//span[text()='Close']")).isDisplayed());
    }
    @Test
    public void TC_07_Fluent_Wait() {
        driver.get("https://automationfc.github.io/fluent-wait/");


        FluentWait<WebDriver> fluentDriver;
        fluentDriver = new FluentWait<>(driver);
        fluentDriver.withTimeout(Duration.ofSeconds(15))
                        .pollingEvery(Duration.ofSeconds(1))
                                .ignoring(NoSuchElementException.class)
                                    .until(new Function<WebDriver, Boolean>() {

                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        Boolean find = webDriver.findElement(By.cssSelector("div#javascript_countdown_time")).getText().endsWith("00");
                        System.out.println(find);
                       return find;
                    }
                });


    }
    @Test
    public void TC_08_Fluent_Wait() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        FluentWait<WebDriver> fluentWait;
        fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class)
                .until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return webDriver.findElement(By.xpath("//h4[text()='Hello World!']")).getText().equals("Hello World!");
                    }
                });
    }
    @Test
    public void TC_09_Page_Ready() {
        driver.get("https://admin-demo.nopcommerce.com");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        FluentWait<WebDriver> fluentWait;
        fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        fluentWait.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class)
                .until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        System.out.println(driver.getTitle().contains("Login"));
                        return driver.getTitle().contains("Login");
                    }
                });
        Assert.assertEquals(driver.findElement(By.cssSelector("div.title>strong")).getText(),"Welcome, please sign in!");
    }

    @Test
    public void TC_10_Page_Ready() {
        FluentWait fluentWait;
        fluentWait = new FluentWait<>(driver);
        driver.get("https://blog.testproject.io/");
        driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
        Assert.assertTrue(isPageLoadedSuccess());
      fluentWait.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(100)).ignoring(StaleElementReferenceException.class)
                        .until(ExpectedConditions.elementToBeClickable(By.cssSelector("section#search-2 span.glass")));
        driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
        Assert.assertTrue(isPageLoadedSuccess());
        List<WebElement> searchResults = driver.findElements(By.cssSelector("div.post-on-archive-page h3.post-title>a"));
        for (WebElement searchResult : searchResults) {
            Assert.assertTrue(searchResult.getText().contains("Selenium"));
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
    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }
}
