package quanlihocky;

import org.testng.annotations.Test;
import authen.Authen;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;

import java.util.Random;

public class Xoa_HK {
    public String baseUrl = "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/";
    public WebDriver driver;

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
        Thread.sleep(4000);
    }

    @Test(priority = 1)
    public void AuthenTk() throws InterruptedException {
        // Gọi phương thức đăng nhập từ class Authen
        Authen.AuthenTK(driver);
        Thread.sleep(5000);
    }

    // Kiểm tra xóa học kỳ với xác nhận (priority = 0)
    @Test(priority = 0, dependsOnMethods = "AuthenTk")
    public void HKvaN1() throws InterruptedException {
        openHKPage();
        deleteHKAction(true);
    }

    // Kiểm tra xóa học kỳ nhưng nhấn hủy (priority = 1)
    @Test(priority = 1, dependsOnMethods = "AuthenTk")
    public void HKvaN() throws InterruptedException {
        openHKPage();
        deleteHKAction(false);
    }

    @AfterTest
    public void afterTest() throws InterruptedException {
        Thread.sleep(2000);  // Chờ để logs được hiển thị trước khi đóng trình duyệt
        driver.close();
    }

    // Mở trang Học kỳ và Nhóm môn
    private void openHKPage() throws InterruptedException {
        WebElement HKvaN = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[2]/a/span"));
        HKvaN.click();
        Thread.sleep(3000);
    }

    // Hành động xóa học kỳ, có thể chọn "Xác nhận" hoặc "Hủy"
    private void deleteHKAction(boolean confirm) throws InterruptedException {
        Random random = new Random();
        int randomIndex = random.nextInt(10) + 1;

        String deleteXPath = "/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/table/tbody/tr[" 
                            + randomIndex + "]/td[9]/a[2]";
        
        // Click vào nút Xóa
        driver.findElement(By.xpath(deleteXPath)).click();
        Thread.sleep(3000);

        if (confirm) {
            // Xác nhận xóa
            driver.findElement(By.xpath("/html/body/div[3]/div/div[6]/button[3]")).click();
            System.out.println(">>> Đã xóa học kỳ tại hàng: " + randomIndex);
        } else {
            // Hủy xóa
            driver.findElement(By.xpath("/html/body/div[3]/div/div[6]/button[1]")).click();
            Thread.sleep(3000);
            // Lấy thông tin học kỳ vừa chọn
            String selectedHK = driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/table/tbody/tr[" 
                        + randomIndex + "]/td[1]"))
                .getText();
            System.out.println(">>> Bạn đã chọn học kỳ: HK" + selectedHK + " nhưng không xóa.");
        }

        System.out.println("-----------------------------------------------------------------------------");
    }
}
