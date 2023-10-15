package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_16_Custom_Dropdown {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Jquery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInDropdown("span#speed-button", "ul#speed-menu div", "Medium");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Medium");

        selectItemInDropdown("span#files-button", "ul#files-menu div", "Some other file with a very long option text");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button span.ui-selectmenu-text")).getText(),"Some other file with a very long option text");

        selectItemInDropdown("span#number-button", "ul#number-menu li", "19");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),"19");

        selectItemInDropdown("span#salutation-button", "ul#salutation-menu div", "Other");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(),"Other");


    }
    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDropdown("div.ui.fluid.selection.dropdown", "div.visible.menu div", "Justen Kitsune");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(),"Justen Kitsune");

    }
    @Test
    public void TC_03_Vue() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu li", "Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Third Option");
        System.out.println(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText());

    }
    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectItemInDropdown_sendkey("input.search", "div.item span", "Benin");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(),"Benin");

        selectItemInDropdown_sendkey("input.search", "div.item span", "Angola");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(),"Angola");

    }
    @AfterClass
    public void afterClass() {

        driver.quit();
    }

    public void selectItemInDropdown(String parentCss, String childCss, String dropdownValue) {
        driver.findElement(By.cssSelector(parentCss)).click();

        // Vừa wait vừa tìm element
        List<WebElement> itemList = explicitWait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

        for ( WebElement item : itemList) {
            String textItem = item.getText();
            System.out.println("Dropdown value = " + textItem);
            if (textItem.equals(dropdownValue)) {
                item.click();
                break;
            }
        }
    }

    public void selectItemInDropdown_sendkey(String parentCss, String childCss, String dropdownValue) {
        driver.findElement(By.cssSelector(parentCss)).click();
        driver.findElement(By.cssSelector(parentCss)).clear();
        driver.findElement(By.cssSelector(parentCss)).sendKeys(dropdownValue);

        // Vừa wait vừa tìm element
        List<WebElement> itemList = explicitWait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

        for ( WebElement item : itemList) {
            String textItem = item.getText();
            System.out.println("Dropdown value = " + textItem);
            if (textItem.equals(dropdownValue)) {
                item.click();
                break;
            }
        }
    }
}
