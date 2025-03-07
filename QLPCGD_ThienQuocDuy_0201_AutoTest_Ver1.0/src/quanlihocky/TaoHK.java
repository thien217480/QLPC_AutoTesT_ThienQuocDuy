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

public class TaoHK {
	public String baseUrl = "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/";
	String driverPath = "c:\\chromedriver.exe";
	public WebDriver driver;
	
  
  @BeforeTest
  public void beforeTest() throws InterruptedException {
	 
	  WebDriverManager.chromedriver().setup();

      // Đường dẫn đến thư mục User Data của Chrome để sử dụng thông tin đăng nhập đã lưu
      String key = "C:\\Users\\Admin\\AppData\\Local\\Google\\Chrome\\User Data";

      // Tạo đối tượng ChromeOptions để cấu hình Chrome
      ChromeOptions options = new ChromeOptions();
      options.addArguments("user-data-dir=" + key);

      // Khởi tạo đối tượng WebDriver cho Chrome với các tùy chọn đã cấu hình
      driver = new ChromeDriver(options);
      driver.get(baseUrl);

      // Mở rộng cửa sổ trình duyệt
      driver.manage().window().maximize();
      Thread.sleep(4000);
      
  }
  @Test(priority = 1)
  public void AuthenTk() throws InterruptedException {
      // Gọi phương thức đăng nhập từ class Authen
      Authen.AuthenTK(driver);
      Thread.sleep(5000);
  }
  
  
  @Test(priority = 2)
  public void ClickHocKy() throws InterruptedException {
	  //Click "Quản lí Học Kỳ"
	  WebElement ClickHocKy = driver.findElement(By.xpath("//*[@id=\"main-menu-navigation\"]/li[2]/a/span"));
	  ClickHocKy.click();
	  Thread.sleep(2000);
	  
	  //Click Button "Thêm Học Kỳ"
	  WebElement clickbutton = driver.findElement(By.xpath("//*[@id=\"tblTerm_wrapper\"]/div[1]/div[2]/div/div[2]/button"));
	  clickbutton.click();
	  Thread.sleep(2000);
	  
	  //Nhập "Học Kỳ"
	  WebElement inputHocKy = driver.findElement(By.xpath("//*[@id=\"id\"]"));
	  inputHocKy.sendKeys("936");
	  Thread.sleep(2000);
	  
	  //click dấu  chọn năm bắt đầu
	  WebElement inputNamBatDau2 = driver.findElement(By.xpath("//*[@id=\"term-form\"]/div[2]/div/span/span[1]/span/span[2]/b"));
	  inputNamBatDau2.click();
	  Thread.sleep(2000);
	  
	  //click  chọn năm 2021
	  WebElement inputNamBatDau12 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[2]/div/span[2]/span/span[2]/ul/li[7]"));
	  inputNamBatDau12.click();
	  Thread.sleep(2000);
	  
	  //click dấu  chọn năm kết thúc
	  WebElement inputKetThuc2 = driver.findElement(By.xpath("//*[@id=\"term-form\"]/div[3]/div/span/span[1]/span/span[2]/b"));
	  inputKetThuc2.click();
	  Thread.sleep(2000);
	  
	  //click  chọn năm 2029
	  WebElement inputNamKetThuc12 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[3]/div/span[2]/span/span[2]/ul/li[15]"));
	  inputNamKetThuc12.click();
	  Thread.sleep(2000);
	  
	  //click  ngày Bắt Đầu
	  WebElement clickNgayBatDau = driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[5]/input[2]"));
	  clickNgayBatDau.click();
	  Thread.sleep(2000);
	  
	  //click ngày 20
	  WebElement clickNgay = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/span[25]"));
	  clickNgay.click();
	  Thread.sleep(2000);
	 
	  
	  
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