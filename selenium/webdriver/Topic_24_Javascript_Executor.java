package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_24_Javascript_Executor {
    WebDriver driver;
    JavascriptExecutor js;

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
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Tech_Panda() {
        js.executeScript("window.location = 'http://live.techpanda.org/'");
        sleepInSeconds(3);
        String domain = (String) js.executeScript("return document.domain");
        Assert.assertEquals(domain,"live.techpanda.org");
        String URL = (String) js.executeScript("return document.URL");
        Assert.assertEquals(URL,"http://live.techpanda.org/");
        WebElement mobileButton = driver.findElement(By.xpath("//a[text()='Mobile']"));
        js.executeScript("arguments[0].click();",mobileButton);
        js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div//button[@title='Add to Cart']")));
        sleepInSeconds(2);
        String innerText = js.executeScript("return document.documentElement.innerText;").toString();
        sleepInSeconds(3);
        Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));
        js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[text()='Customer Service']")));
        sleepInSeconds(2);
        js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.cssSelector("input#newsletter")));
        sleepInSeconds(2);
        js.executeScript("arguments[0].setAttribute('value','" + getEmailAddress() + "');",getElement("//input[@id='newsletter']"));
        sleepInSeconds(2);
        clickToElementByJS("//button[@title='Subscribe']");
        sleepInSeconds(2);
        Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
        navigateToUrlByJS("https://www.facebook.com/");
        sleepInSeconds(2);
        domain = (String) executeForBrowser("return document.domain");
        Assert.assertEquals(domain,"facebook.com");
    }
    @Test
    public void TC_02_Ubuntu() {
        navigateToUrlByJS("https://login.ubuntu.com/");
        sleepInSeconds(2);
        if(driver.findElements(By.cssSelector("dialog.cookie-policy")).size() > 0){
            driver.findElement(By.cssSelector("button#cookie-policy-button-accept")).click();
        }
        sendkeyToElementByJS("//div[@class='login-form']//input[@id='id_email']","a");
        sleepInSeconds(2);
        Assert.assertEquals(getElementValidationMessage("//div[@class='login-form']//input[@id='id_email']"),"Please enter an email address.");
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
    public Object executeForBrowser(String javaScript) {
        return js.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) js.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) js.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        js.executeScript("window.location = '" + url + "'");
        sleepInSeconds(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        js.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        js.executeScript("arguments[0].click();", getElement(locator));
        sleepInSeconds(3);
    }

    public void scrollToElementOnTop(String locator) {
        js.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        js.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        js.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        js.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) js.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) js.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) js.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
    public String getEmailAddress() {
        return "nam" + new Random().nextInt(99999) + "@gmail.com";

    }

}
