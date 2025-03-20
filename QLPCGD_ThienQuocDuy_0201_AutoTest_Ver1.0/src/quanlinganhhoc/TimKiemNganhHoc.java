package quanlinganhhoc;

import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ChinhSuaHoSo.Authen;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TiemKiemNganhHoc {
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
    public void moTrangNganhHoc() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,10);

            // Tìm và nhấn vào menu "Học Kỳ và Ngành"
            WebElement menuHocKyvaNganh = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/Phancong02/Term']")));
            menuHocKyvaNganh.click();
            Thread.sleep(3000);
            // Nhấn vào menu "Ngành học"
            WebElement menuNganh = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/Phancong02/Major']")));
            menuNganh.click();
            }
    @Test(priority = 2, dependsOnMethods = "moTrangNganhHoc")
    public void TimKiemNganhHoc() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Tìm ô nhập tìm kiếm bằng XPath hoặc CSS Selector
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search' and @aria-controls='tblMajor']")));

        // Đặt từ khóa tìm kiếm cố định là "CNTT"
        String keyword = "CNTT";
        searchBox.clear();
        searchBox.sendKeys(keyword);
        System.out.println("🔍 Đã nhập từ khóa tìm kiếm: " + keyword);
        Thread.sleep(2000);

        // Chờ kết quả hiển thị
        List<WebElement> searchResults = driver.findElements(By.xpath("//table[@id='tblMajor']//tbody/tr"));

        if (!searchResults.isEmpty()) {
            System.out.println("✅ Kết quả tìm kiếm:");
            for (WebElement row : searchResults) {
                System.out.println("📌 " + row.getText());
            }
        } else {
            System.out.println("❌ Không tìm thấy ngành học nào phù hợp.");
        }
    }
    
}


