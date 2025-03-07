package quanlinguoidung;

import authen.Authen;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class ThemNguoiDung {
    public String baseUrl = "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/";
    public WebDriver driver;
    public WebDriverWait wait;

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

    @Test(priority = 2, dependsOnMethods = "AuthenTk")
    
        // 1. Click vào menu "Người dùng"
    	public void ThemDSNguoiDung() throws InterruptedException {
    		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[3]/a")).click();
    		Thread.sleep(2000);
        // 2. Click nút "Thêm người dùng"
    		driver.findElement(By.xpath("//*[@id=\"tblUser_wrapper\"]/div[1]/div[2]/div/div[2]/button")).click();
    		Thread.sleep(2000);

    	// 3. nhập mã giảng viên
    	  	Thread.sleep(2000);
    	  	driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[1]/input")).sendKeys("222222");
        // 4. Nhập tên giảng viên
    	  	Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id='full_name']")).sendKeys("Nguyễn Văn B");

        //	5.Nhập email
          	Thread.sleep(2000);
          	driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[3]/input")).sendKeys("b@vanlanguni.vn");
        //  6.click arrow của loại giảng viên
          	Thread.sleep(2000);
          	driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[4]/div/span/span[1]/span/span[2]")).click();
         // 7.Chọn thỉnh giảng
          	Thread.sleep(2000);
          	driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[4]/div/span[2]/span/span[2]/ul/li[2]")).click();
         // 8.click arrow chọn role
          	Thread.sleep(2000);
          	driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[5]/div/span/span[1]/span/span[2]")).click();
         // 9.chọn role
          	Thread.sleep(2000);
          	driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[5]/div/span[2]/span/span[2]/ul/li[2]")).click();
         // 10.Lưu
          	Thread.sleep(2000);
          	driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[7]/button[2]")).click();
          }
          

          @AfterTest
          public void afterTest() {
        	  driver.close(); 
          }

        }

