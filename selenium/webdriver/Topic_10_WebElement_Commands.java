package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_10_WebElement_Commands {
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
    public void TC_01_Element() {
        // Tìm và trả về 1 element, chưa tương tác
        driver.findElement(By.id(""));

        // Tìm và tương tác lên
        driver.findElement(By.id("")).click();

        // Tìm và lưu vào 1 biến WebElement (chưa tương tác)
        WebElement fullNameTextbox = driver.findElement(By.id(""));

        // Verify 1 checkbox/ radio/ dropdown (default) đã đc chọn hay chưa
        driver.findElement(By.id("")).isSelected();

        // Verify 1 element có đc hiển thị hay ko
        driver.findElement(By.id("")).isDisplayed();

        // Dùng để verify 1 element có thao tác đc lên hay ko (ko phải read only)
        driver.findElement(By.id("")).isEnabled();

        // lấy value của attribute
        driver.findElement(By.id("")).getAttribute("title");

        // tab style, truyền tên lấy value
        driver.findElement(By.id("")).getCssValue("font-size");

        // get vị trí element so vs độ phân giải màn hình
        driver.findElement(By.id("")).getLocation();

        // get size
        driver.findElement(By.id("")).getSize();

        // location + size, kiểu Rectangle
        Rectangle name = driver.findElement(By.id("")).getRect();
        name.getPoint();
        name.getDimension();

        // Shadow Element
        driver.findElement(By.id("")).getShadowRoot();

        // Từ id/ class/ name/ css/ xpath có thể truy ra ngược lại tagname HTML
        driver.findElement(By.xpath("//*'[@class='form-list']")).getTagName();

        // get visible text
        driver.findElement(By.id("")).getText();

        // Chụp hình bị lỗi và lưu dưới dạng BYTE, FILE, BASE64
        driver.findElement(By.id("")).getScreenshotAs(OutputType.FILE);
        driver.findElement(By.id("")).getScreenshotAs(OutputType.BASE64);
        driver.findElement(By.id("")).getScreenshotAs(OutputType.BYTES);

        // Form (element nào là thẻ form hoặc nằm trong thẻ form)
        driver.findElement(By.id("")).submit();
    }

    public void TC_02_() {

    }
    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
