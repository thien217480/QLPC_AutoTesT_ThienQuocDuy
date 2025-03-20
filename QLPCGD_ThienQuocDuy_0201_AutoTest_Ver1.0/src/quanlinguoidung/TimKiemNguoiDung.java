package quanlinguoidung;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import authen.Authen;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TimKiemNguoiDung {
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
    public void MoNguoiDung() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Nhấn vào mục "Hồ sơ cá nhân"
        WebElement Nguoidung = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,\"/Phancong02/User\")]")));
        Nguoidung.click();                
	 } 
    @Test(priority = 2, dependsOnMethods = "MoNguoiDung")
    public void timkiemnguoidung() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        // Chờ ô tìm kiếm hiển thị
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='search']")
        ));
        
        // Nhập từ khóa tìm kiếm
        String keyword = "Lê Anh Duy"
        searchBox.clear();
        searchBox.sendKeys(keyword);
        Thread.sleep(3000); // Chờ 3 giây để dữ liệu cập nhật
    }
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
	
