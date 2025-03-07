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

public class TaoHK_TrongThongTin {
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

	//Nhập Học Kỳ 
	@Test(priority = 2, dependsOnMethods = "AuthenTk")
	public void addTerm() throws InterruptedException {
		
		//Click "Quản lí Học Kỳ"
		WebElement ClickHocKy = driver.findElement(By.xpath("//*[@id=\"main-menu-navigation\"]/li[2]/a/span"));
		 ClickHocKy.click();
		 Thread.sleep(2000);
		
		//Bấm nút tạo học kỳ
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[2]/button")).click();
        Thread.sleep(1000);

        //Nhập học kỳ Rỗng

	}
	
	//Chọn năm bắt đầu 
	@Test(priority = 3, dependsOnMethods = "addTerm")
	public void selectYearBegins() throws InterruptedException  {
		WebElement RongHK = driver.findElement(By.xpath("//*[@id=\\\"id\\\"]"));
		 RongHK.sendKeys("");
		 Thread.sleep(2000);
	}

	//Chọn năm kết thúc 
	@Test(priority = 4, dependsOnMethods = "selectYearBegins")
	public void selectYearEnds() throws InterruptedException {

	}

	//Chọn tuần bắt đầu
	@Test(priority = 5, dependsOnMethods = "selectYearEnds")
	public void selectWeek() throws InterruptedException {

	}

	//Chọn ngày bắt đầu Rỗng
	@Test(priority = 6, dependsOnMethods = "selectWeek")
	public void selectDay() throws InterruptedException {

	}

	//Chọn tiết và lớp tối đa
	@Test(priority = 7, dependsOnMethods = "selectDay")
	public void selectClassMaximum() throws InterruptedException {

	

	//click button "Lưu"
	WebElement ButtonLuu = driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[7]/button[2]"));
	ButtonLuu.click();
	Thread.sleep(2000);

	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
