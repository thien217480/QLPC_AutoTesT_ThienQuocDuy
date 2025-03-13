package QuanlyTKB;

import org.testng.annotations.Test;

import authen.Authen;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;

public class ExportTKB {
	public String baseUrl = "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/";
	String driverPath = "c:\\chromedriver.exe";
	public WebDriver driver;

	@BeforeTest
	public void beforeTest() throws InterruptedException {
		WebDriverManager.chromedriver().setup();

		// Đường dẫn đến thư mục User Data của Chrome để sử dụng thông tin đăng nhập đã
		// lưu
		String key = "C:\\Users\\Admin\\AppData\\Local\\Google\\Chrome\\User Data";

		// Tạo đối tượng ChromeOptions để cấu hình Chrome
		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=" + key);

		// Khởi tạo đối tượng WebDriver cho Chrome với các tùy chọn đã cấu hình
		driver = new ChromeDriver(options);

		// Mở trang web cần kiểm tra đăng nhập
		driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");

		// Mở rộng cửa sổ trình duyệt
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void AuthenTk() throws InterruptedException {
		// Gọi phương thức đăng nhập từ class Authen
		Authen.AuthenTK(driver);
		Thread.sleep(2000);
	}

	@Test(priority = 2, dependsOnMethods = "AuthenTk")
	public void exportTKB() throws InterruptedException, AWTException {
		// Click vào button thời khóa biểu
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[4]")).click();
		Thread.sleep(2000);
		// click Phan cong
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[4]/ul/li[2]/a")).click();
		Thread.sleep(10000);
		// Arrow_chonhocky
		driver.findElement(By.xpath(
						"/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div[1]/div[1]/div/span/span[1]/span"))
						.click();
		Thread.sleep(2000);
		// Chọn học kỳ
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div[1]/div[1]/div/span[2]/span/span[2]/ul/li[2]")).click();
		Thread.sleep(2000);
		// Arrow_NGÀNH
		driver.findElement(By.xpath(
						"/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div[1]/div[2]/div/span/span[1]/span"))
						.click();
		Thread.sleep(2000);
		// Chọn NGÀNH
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div[1]/div[2]/div/span[2]/span/span[2]/ul/li[3]")).click();
		Thread.sleep(5000);	
		// Chọn EXPORT
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div[2]/div[2]/div[2]/div/button")).click();
		Thread.sleep(5000);
		
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
