package QuanlyTKB;

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

public class XemchitietTKB {
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


  
  @Test (priority = 2, dependsOnMethods = "AuthenTk")
  public void XemThoiKhoaBieu() throws InterruptedException {
	  //Chọn Mục Thời Khóa Biểu
	  driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[4]/a/span")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//*[@id=\"main-menu-navigation\"]/li[4]/ul/li[3]/a")).click();
	  Thread.sleep(2000);
	  
	  //Nhập tên Giảng Viên
	  driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div[1]/div[3]/div/span[1]/span[1]/span")).click();
	  Thread.sleep(2000);
	  WebElement NhapTenGV = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div[1]/div[3]/div/span[2]/span/span[1]/input"));
	  NhapTenGV.sendKeys("Phan Thị Hồng");
//	  NhapTenGV.click();
	  Thread.sleep(2000);
	//*[@id="select2-week-container"]
	  //click vào cô
	  driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div[1]/div[3]/div/span[2]/span/span[2]/ul/li")).click();
	  Thread.sleep(2000);
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
  }

}
