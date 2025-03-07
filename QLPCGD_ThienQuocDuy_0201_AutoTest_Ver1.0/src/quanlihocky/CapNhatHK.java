package quanlihocky;

import org.testng.annotations.Test;
import authen.Authen;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class CapNhatHK {
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
        Thread.sleep(4000);
        
        // Khởi tạo WebDriverWait để đợi phần tử
        wait = new WebDriverWait(driver, 10);

    }

    @Test(priority = 1)
    public void AuthenTk() throws InterruptedException {
        // Gọi phương thức đăng nhập từ class Authen
        Authen.AuthenTK(driver);
        Thread.sleep(5000);
    }


    @Test(priority = 2, dependsOnMethods = "AuthenTk")
    public void updateHocKy() throws InterruptedException {
        // Chuyển đến trang cập nhật học kỳ
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[2]/a"))).click();

        // Click vào nút cập nhật học kỳ đầu tiên
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[9]/a[1]"))).click();

        // Chọn năm bắt đầu
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/form/div[2]/div/span/span[1]/span/span[2]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/form/div[2]/div/span[2]/span/span[2]/ul/li[10]"))).click();

        // Chọn năm kết thúc
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/form/div[3]/div/span/span[1]/span/span[2]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/form/div[3]/div/span[2]/span/span[2]/ul/li[10]"))).click();
    }

    @Test(priority = 3, dependsOnMethods = "updateHocKy")
    public void batDau() throws InterruptedException {
        // Chọn tuần bắt đầu
        WebElement week = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/form/div[4]/div/span[2]/button")));
        for (int a = 0; a < 3; a++) {
            week.click();
        }

        // Chọn ngày tháng năm bắt đầu
        WebElement yearStart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/form/div[5]/input[2]")));
        yearStart.click();

        // Xóa năm cũ
        WebElement yearInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[1]/div/div/div/input")));
        yearInput.clear();
        yearInput.sendKeys("2023");

        // Chọn ngày
        WebElement day = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/span[15]")));
        day.click();
    }

    @Test(priority = 4, dependsOnMethods = "batDau")
    public static void selectClassMaximum() throws InterruptedException {
        // Tiết tối đa
        WebElement tietToiDa = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/form/div[6]/div[1]/div/span[1]/button")));
        for (int i = 0; i < 3; i++) {
            tietToiDa.click();
        }

        // Tuần tối đa
        WebElement tuanToiDa = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/form/div[6]/div[2]/div/span[2]/button")));
        for (int i = 0; i < 3; i++) {
            tuanToiDa.click();
        }

        // Click Lưu
        WebElement btnLuu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/form/div[7]/button[2]")));
        btnLuu.click();
    }

    @AfterTest
    public void afterTest() throws InterruptedException {
        Thread.sleep(2000);  // Chờ để logs được hiển thị trước khi đóng trình duyệt
        if (driver != null) {
            driver.quit();
        }
    }
}
