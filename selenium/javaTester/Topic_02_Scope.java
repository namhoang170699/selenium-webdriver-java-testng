package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Scope {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    String homePageUrl; // Khai báo: Declare

    String fullName= "Auto FC"; //Khai báo + khởi tạo (initial)
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
    public void TC_01_() {
        // Các biến được khai báo trong 1 hàm/ block code -> phạm vi cục bộ (local)
        // Dùng trong cái hàm nó được khai báo/ block code được sinh ra
        String homePageUrl = "https://www.facebook.com/";

        // Trong 1 hàm nếu như có 2 biến cùng tên (Global/ Local) thì nó sẽ ưu tiên lấy biến Local dùng
        // 1 biến Local nếu như gọi tới dùng mà chưa đc khởi tạo thì sẽ bị lỗi
        // Biến local chưa khởi tạo mà gọi ra dùng nó sẽ báo lỗi (compile Code)
        driver.get(homePageUrl);

        // Nếu trong 1 hàm có 2 biến cùng tên (Global/ local) mà mình muốn lấy biến Global ra để dùng
        // Từ khóa this
        // Biến Global chưa khởi tạo mà gọi ra dùng thì ko báo lỗi ở level compile code
        // Level runtime sẽ lỗi
        driver.get(this.homePageUrl);
    }

    public void TC_02_() {

    }
    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
