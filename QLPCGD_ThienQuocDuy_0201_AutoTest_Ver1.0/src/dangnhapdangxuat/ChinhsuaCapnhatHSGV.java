package dangnhapdangxuat;
import authen.Authen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChinhsuaCapnhatHSGV {
    private final String baseUrl = "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login";
    private WebDriver driver;
    
    // Hàm khởi tạo WebDriver
    public WebDriver initializeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\Admin\\AppData\\Local\\Google\\Chrome\\User Data");
        return new ChromeDriver(options);
    }

    @BeforeTest
    public void beforeTest() throws InterruptedException {
        driver = initializeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        
        // Gọi phương thức đăng nhập từ Authen
        Authen.AuthenTK(driver);
    }

    @Test(priority = 1)
    public void MoFormCapNhatHoSo() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        // Mở dropdown hồ sơ
        WebElement avatarDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("dropdown-user")));
        avatarDropdown.click();

        // Nhấn vào mục "Hồ sơ cá nhân"
        WebElement hoSoCaNhan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/Phancong02/Account/Update')]")));
        hoSoCaNhan.click();
    }

    @Test(priority = 2, dependsOnMethods = "MoFormCapNhatHoSo")
    public void NhapThongTinMoi() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        // Cập nhật Mã giảng viên
        WebElement staffIdField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("staff_id")));
        staffIdField.clear();
        staffIdField.sendKeys("2174802010307");
        
        // Cập nhật Tên giảng viên
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("full_name")));
        nameField.clear();
        nameField.sendKeys("Le Anh Duy");
    }

    @Test(priority = 3, dependsOnMethods = "NhapThongTinMoi")
    public void LuuThongTin() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        // Nhấn nút "Cập nhật"
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Cập nhật')]")));
        saveButton.click();
    }

    @Test(priority = 4, dependsOnMethods = "LuuThongTin")
    public void XacNhanCapNhatThanhCong() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        // Xác nhận thông báo cập nhật thành công
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));
        Assert.assertTrue(successMessage.getText().contains("Cập nhật thành công"), "Cập nhật hồ sơ không thành công!");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
