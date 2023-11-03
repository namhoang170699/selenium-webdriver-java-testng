package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Topic_17_Button_Radio_Checkbox {
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
    public void TC_01_Fahasa_button() {
        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        sleepInSeconds(3);

        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));
        Assert.assertFalse(loginButton.isEnabled());

        Color loginButtonColor = Color.fromString(loginButton.getCssValue("background-color"));
        Assert.assertEquals(loginButtonColor.asHex().toUpperCase(),"#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("logintest@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        sleepInSeconds(3);

        loginButtonColor = Color.fromString(loginButton.getCssValue("background-color"));
        Assert.assertEquals(loginButtonColor.asHex().toUpperCase(),"#C92127");

    }
    @Test
    public void TC_02_Telerik_checkbox_radio() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
        if (!isElementSelected(dualZoneCheckbox)){
            elementClick(dualZoneCheckbox);
        }
        Assert.assertTrue(isElementSelected(dualZoneCheckbox));

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        By twoPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
        elementClick(twoPetrolRadio);
        Assert.assertTrue(isElementSelected(twoPetrolRadio));
    }
    @Test
    public void TC_03_Angular_checkbox_radio() {
        driver.get("https://material.angular.io/components/radio/examples");
        elementClick(By.cssSelector("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
        Assert.assertTrue(isElementSelected(By.cssSelector("input#mat-radio-4-input")));

        driver.get("https://material.angular.io/components/checkbox/examples");
        elementClick(By.cssSelector("input#mat-mdc-checkbox-1-input"));
        elementClick(By.cssSelector("input#mat-mdc-checkbox-2-input"));
        Assert.assertTrue(isElementSelected(By.cssSelector("input#mat-mdc-checkbox-1-input")));
        Assert.assertTrue(isElementSelected(By.cssSelector("input#mat-mdc-checkbox-2-input")));

        elementClick(By.cssSelector("input#mat-mdc-checkbox-1-input"));
        elementClick(By.cssSelector("input#mat-mdc-checkbox-2-input"));
        Assert.assertFalse(isElementSelected(By.cssSelector("input#mat-mdc-checkbox-1-input")));
        Assert.assertFalse(isElementSelected(By.cssSelector("input#mat-mdc-checkbox-2-input")));


    }
    @Test
    public void TC_04_Tiemchungcovid19_custom_checkbox_radio() {
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
        WebElement radioButton = driver.findElement(By.cssSelector("input#mat-radio-3-input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",radioButton);
        sleepInSeconds(5);
    }
    @Test
    public void TC_05_Select_multiple_fields() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("div.form-input-wide input.form-checkbox"));
        for (WebElement eachCheckbox: allCheckboxes) {
            if (!isElementSelectedWebElement(eachCheckbox)){
                eachCheckbox.click();
            }
        }

        for (WebElement eachCheckboxIsSelected: allCheckboxes) {
            Assert.assertTrue(eachCheckboxIsSelected.isSelected());
            System.out.println(eachCheckboxIsSelected);
        }

        driver.navigate().refresh();
        driver.manage().deleteAllCookies();
        sleepInSeconds(3);
        allCheckboxes = driver.findElements(By.cssSelector("div.form-input-wide input.form-checkbox"));
        for (WebElement eachCheckbox: allCheckboxes) {
            if (!isElementSelectedWebElement(eachCheckbox) && eachCheckbox.getAttribute("value").equals("Heart Attack") ){
                eachCheckbox.click();
                sleepInSeconds(3);
            }
        }
    }
    @Test
    public void TC_06_Custom_checkbox_radio() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        sleepInSeconds(5);
        By canThoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"),"false");
        driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']")).click();
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"),"true");

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
    public boolean isElementSelected(By by) {
        WebElement element = driver.findElement(by);
        if (element.isSelected()) {
            System.out.println("Element is selected:" + by);
            return true;
        }else {
            System.out.println("Element is not selected:" + by);
            return false;
        }
    }
    public boolean isElementSelectedWebElement(WebElement webElement) {
        WebElement element = webElement;
        if (element.isSelected()) {
            System.out.println("Element is selected:" + webElement);
            return true;
        }else {
            System.out.println("Element is not selected:" + webElement);
            return false;
        }
    }

    public void elementClick(By by) {
        WebElement element = driver.findElement(by);
        element.click();
    }
}
