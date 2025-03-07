package quanlinguoidung;

import authen.Authen;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;

public class XemDSNguoiDung {
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
	@Test
	  public void AuthenTk() throws InterruptedException {
	      // Gọi phương thức đăng nhập từ class Authen
	      Authen.AuthenTK(driver);
	      Thread.sleep(2000);
	}

	@Test
	public void XemDSNguoiDung() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[3]/a")).click();
		Thread.sleep(2000);
		// chọn loại giảng viên
		WebElement selectElement = driver.findElement(By.id("UserType"));
		Thread.sleep(2000);

		// chọn giảng viên cơ hữu
		Select select = new Select(selectElement);
		select.selectByVisibleText("Cơ hữu");

		// Chọn role
		WebElement selectElement1 = driver.findElement(By.id("UserRole"));
		Thread.sleep(2000);

		// chọn BCN khoa
		Select select1 = new Select(selectElement1);
		select1.selectByVisibleText("BCN khoa");

		// chọn hiển thị
		WebElement selectElement2 = driver.findElement(By.name("tblUser_length"));
		Thread.sleep(2000);

		// Chọn số dòng hiển thị
		Select select2 = new Select(selectElement2);
		select2.selectByVisibleText("25");

		// columns
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[2]/div[3]/div/section/div[2]/div[2]/div/div/div[1]/div[1]/div[2]/div/label/div/span[1]/span[1]/span"))
				.click();
		Thread.sleep(2000);
		// delete column
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[2]/div[3]/div/section/div[2]/div[2]/div/div/div[1]/div[1]/div[2]/div/label/div/span[2]/span/span/ul/li[4]"))
				.click();
		Thread.sleep(2000);

		driver.findElement(By
				.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div[2]/div[2]/div/div/div[2]/div[2]/div/ul/li[5]"))
				.click();

	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}

