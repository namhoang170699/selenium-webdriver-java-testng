package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_09_WebBrowser_Commands {
    // Các câu lệnh để thao tác với Browser
    //driver
    WebDriver driver;

    // Các câu lệnh thao tác vs element
    // element.
    WebElement element;

    FirefoxDriver ffDriver;

    ChromeDriver chDriver;

    EdgeDriver eDriver;

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
        // Sele 3/2/1
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //Sele 4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        // Mở ra 1 page url bất kì

        //Set trực tiếp vào
        driver.get("https://www.facebook.com");

        //Khai báo biến rồi gán vào
        // Nếu như biến này chỉ dùng 1 lần thì ko nên tạo biến
        String homePageUrl = "https://www.facebook.com";
        driver.get(homePageUrl);

        // Đóng tab đang active
        driver.close();

        // Đóng browser
        driver.quit();

        // 2 hàm này sẽ bị ảnh hưởng timeout của implicitlyWait

        // Nó sẽ đi tìm vs loại By nào và trả về element nếu như được tìm thấy
        // Ko đc tìm thấy: Fail tại step này - throw exception: NoSuchElementException
        // Trả về 1 element: nếu có nhiều hơn 1 thì sẽ lấy element đầu tiên
        WebElement emailAddressTextbox = driver.findElement(By.id("email"));

        // Nó sẽ đi tìm vs loại By nào và trả về 1 danh sách element nếu như được tìm thấy [List element]
        // Ko đc tìm thấy: ko bị Fail  - trả về 1 list rỗng

        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        checkboxes.get(1).click();
    }

    public void TC_02_() {

    }
    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
