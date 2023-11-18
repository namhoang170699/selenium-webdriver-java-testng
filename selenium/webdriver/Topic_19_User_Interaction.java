package webdriver;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v116.network.Network;
import org.openqa.selenium.devtools.v116.network.model.Headers;
import org.openqa.selenium.devtools.v85.domsnapshot.model.ArrayOfStrings;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static io.ous.jtoml.impl.Token.TokenType.Key;

public class Topic_19_User_Interaction {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    Actions actions;
    JavascriptExecutor javascriptExecutor;
    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        actions = new Actions(driver);
        javascriptExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).pause(2000).perform();
//        WebElement tooltip = driver.findElement(By.cssSelector("div.ui-tooltip-content"));
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");

    }
    @Test
    public void TC_02_() {
        driver.get("http://www.myntra.com/");
        actions.moveToElement(driver.findElement(By.xpath("//a[text()='Kids' and @class='desktop-main']"))).perform();
        actions.moveToElement(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).click().perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("h1.title-title")).getText(),"Kids Home Bath");
    }
    @Test
    public void TC_03_Fahasa(){
        driver.get("https://www.fahasa.com/");
        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        actions.moveToElement(driver.findElement(By.cssSelector("a[title='Sách Trong Nước'"))).perform();
        actions.click(driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Sách Giáo Khoa']"))).perform();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(),"SÁCH GIÁO KHOA");

    }
    @Test
    public void TC_04_Click_and_hold(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> items = driver.findElements(By.cssSelector("li.ui-state-default"));
        actions.clickAndHold(items.get(0)).moveToElement(items.get(3)).release().perform();
        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("li.ui-selected"));

        List<String> allNumberExpected = new ArrayList<String>();
        allNumberExpected.add("1");
        allNumberExpected.add("2");
        allNumberExpected.add("3");
        allNumberExpected.add("4");

        List<String> allNumberActual = new ArrayList<String>();

        for(WebElement element : allNumberSelected) {
            allNumberActual.add(element.getText());
        }

        Assert.assertEquals(allNumberActual,allNumberExpected);

    }
    @Test
    public void TC_05_ClickAndHold(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> items = driver.findElements(By.cssSelector("li.ui-state-default"));
        actions.clickAndHold(items.get(0)).moveToElement(items.get(11)).release().perform();
        actions.keyDown(Keys.CONTROL).perform();
        actions.clickAndHold(items.get(12)).moveToElement(items.get(14)).release().perform();

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("li.ui-selected"));

        List<String> allNumberExpected = new ArrayList<String>();
        allNumberExpected.add("1");
        allNumberExpected.add("2");
        allNumberExpected.add("3");
        allNumberExpected.add("4");

        List<String> allNumberActual = new ArrayList<String>();

        for(WebElement element : allNumberSelected) {
            allNumberActual.add(element.getText());
        }

        Assert.assertEquals(allNumberActual,allNumberExpected);

    }
    @Test
    public void TC_05_Click_and_select(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> items = driver.findElements(By.cssSelector("li.ui-state-default"));
        actions.click(items.get(0)).perform();
        actions.keyDown(Keys.CONTROL).perform();
        actions.click(items.get(2)).perform();
        actions.click(items.get(5)).perform();
        actions.click(items.get(10)).perform();

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("li.ui-selected"));

        List<String> allNumberExpected = new ArrayList<String>();
        allNumberExpected.add("1");
        allNumberExpected.add("3");
        allNumberExpected.add("6");
        allNumberExpected.add("11");

        List<String> allNumberActual = new ArrayList<String>();

        for(WebElement element : allNumberSelected) {
            allNumberActual.add(element.getText());
        }

        Assert.assertEquals(allNumberActual,allNumberExpected);

    }
    @Test
    public void TC_06_Double_click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement doubleClick = driver.findElement(By.xpath("//button[text()='Double click me']"));
        if (driver.toString().contains("firefox")) {
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(false);",doubleClick);

        } else {
            actions.scrollToElement(doubleClick).perform();
        }

        sleepInSeconds(3);
        actions.doubleClick(doubleClick).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");

    }
    @Test
    public void TC_07_Right_click(){
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        WebElement rightClickButton = driver.findElement(By.xpath("//span[text()='right click me']"));
        actions.contextClick(rightClickButton).perform();
        WebElement quitMenu = driver.findElement(By.cssSelector("ul.context-menu-list"));
        Assert.assertTrue(quitMenu.isDisplayed());
        WebElement quitButton = driver.findElement(By.cssSelector("li.context-menu-icon-quit"));
        actions.moveToElement(quitButton).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.context-menu-hover")).getText(),"Quit");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.context-menu-visible")).getText(),"Quit");
        actions.click(quitButton).perform();
        driver.switchTo().alert().accept();
        sleepInSeconds(3);
        Assert.assertFalse(quitMenu.isDisplayed());
    }
    @Test
    public void TC_08_Drag_and_drop(){
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement dragSource = driver.findElement(By.cssSelector("div#draggable"));
        WebElement dropTarget = driver.findElement(By.cssSelector("div#droptarget"));

        actions.dragAndDrop(dragSource,dropTarget).perform();
        Assert.assertEquals(dropTarget.getText(),"You did great!");
        String colorHex = Color.fromString(dropTarget.getCssValue("background-color")).asHex().toUpperCase();
        Assert.assertEquals(colorHex,"#03A9F4");
    }
    @Test
    public void TC_09_Drag_and_drop_html5() {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
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
