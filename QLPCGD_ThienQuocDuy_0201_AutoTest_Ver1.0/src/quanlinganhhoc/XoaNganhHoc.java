package QuanLyHocKy;

import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.Alert;
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

import ChinhSuaHoSo.Authen;
import io.github.bonigarcia.wdm.WebDriverManager;

public class XoaNganhHoc {
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
		    public void buttonXoaNganhHoc() throws InterruptedException {
			     WebDriverWait wait = new WebDriverWait(driver, 10);
			     String maNganh = "000000000001"; // Mã ngành cần xóa

			     // Tìm nút Xóa theo mã ngành
			     WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
			         By.xpath("//tr[td[contains(text(), '" + maNganh + "')]]//a[contains(@class, 'deleteRow')]")
			     ));

			     // Nhấn vào nút Xóa ngành
			     deleteButton.click();
			     System.out.println("✅ Đã nhấn vào nút Xóa ngành có mã: " + maNganh);
			     Thread.sleep(2000);

			     // Kiểm tra sự xuất hiện của cửa sổ cảnh báo SweetAlert2
			     try {
			         WebElement swal2Popup = wait.until(ExpectedConditions.visibilityOfElementLocated(
			             By.xpath("//div[contains(@class, 'swal2-popup')]")
			         ));
			         System.out.println("✅ Hộp thoại xác nhận đã xuất hiện.");

			         // Nhấn vào nút "Xóa" trong SweetAlert2
			         WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
			             By.xpath("//button[contains(@class, 'swal2-confirm')]")
			         ));
			         confirmButton.click();
			         System.out.println("✅ Đã xác nhận xóa ngành.");

			     } catch (Exception e) {
			         System.out.println("⚠️ Không tìm thấy hộp thoại cảnh báo.");
			     }
			 }
		 @AfterTest
		    public void afterTest() {
		  	  driver.close();
		    }
		}
