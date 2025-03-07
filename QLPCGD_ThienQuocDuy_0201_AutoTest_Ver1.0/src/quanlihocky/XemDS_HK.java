package quanlihocky;

import org.testng.annotations.Test;
import authen.Authen;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class XemDS_HK {
    public String baseUrl = "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/";
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeTest
    public void beforeTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        // Đường dẫn đến User Data để sử dụng thông tin đăng nhập
        String key = "C:\\Users\\Admin\\AppData\\Local\\Google\\Chrome\\User Data";

        // Cấu hình ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + key);

        // Khởi tạo WebDriver với ChromeOptions
        driver = new ChromeDriver(options);
        driver.get(baseUrl);
        driver.manage().window().maximize();
        System.out.println(">>> Trình duyệt đã mở và vào trang: " + baseUrl);

        // Khởi tạo WebDriverWait để đợi phần tử
        wait = new WebDriverWait(driver, 10);
    }

    @Test(priority = 1)
    public void AuthenTk() throws InterruptedException {
        // Gọi phương thức đăng nhập từ class Authen
        Authen.AuthenTK(driver);
        System.out.println(">>> Đã thực hiện đăng nhập.");
    }

    @Test(priority = 2, dependsOnMethods = "AuthenTk")
    public void xemdanhsach() throws InterruptedException {
        System.out.println(">>> Kiểm tra hiển thị danh sách học kỳ.");

        // Bấm vào menu để xem danh sách học kỳ
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-menu-navigation\"]/li[2]/a/span"))).click();
        Thread.sleep(3000);

        // Chờ trang danh sách học kỳ tải hoàn tất
        wait.until(ExpectedConditions.urlToBe("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Term"));
        Thread.sleep(3000);

        // Kiểm tra URL
        String expectedUrl = "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Term";
        String actualUrl = driver.getCurrentUrl();

        if (actualUrl.equalsIgnoreCase(expectedUrl)) {
            System.out.println(">>> Hiển Thị Danh Sách Ngành - PASS");
        } else {
            System.out.println(">>> Không Hiển Thị - Thất bại");
        }
        Thread.sleep(2000);
    }

    @AfterClass
    public void teardown() {
        System.out.println(">>> Đóng trình duyệt.");
        if (driver != null) {
            driver.quit();
        }
    }
}
