package dangnhapdangxuat;

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

public class DangnhapDangxuat {
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
      Thread.sleep(2000);
      
  }
  @Test(priority = 1)
  public void AuthenTk() throws InterruptedException {
      // Gọi phương thức đăng nhập từ class Authen
      Authen.AuthenTK(driver);
      Thread.sleep(2000);
  }
//  @Test (priority = 1)
//  public void XacThucWebSite() throws InterruptedException {
//	  
//	// Phương thức kiểm tra đăng nhập
//	  WebElement XacThucWeb = driver.findElement(By.id("details-button"));
//	  XacThucWeb.click();
//      Thread.sleep(2000);
//      
//      WebElement process_web = driver.findElement(By.xpath("//*[@id=\"proceed-link\"]"));
//      process_web.click();
//      Thread.sleep(2000);
//      
//      //click đăng nhập
//      driver.findElement(By.name("provider")).click();
//      Thread.sleep(5000);
//
//  }
//  
//  @Test (priority = 2, dependsOnMethods = "XacThucWebSite")
//  public void LoginTK() throws InterruptedException {
//	  
//	// Nhập tài khoản thủ công
//      String email = JOptionPane.showInputDialog("Nhập email:");
//      WebElement TaiKhoanVLU = driver.findElement(By.xpath("//input[@type='email']"));
//      TaiKhoanVLU.sendKeys(email);
//      Thread.sleep(2000);
//
//      driver.findElement(By.xpath("//input[@type='submit']")).click();
//      Thread.sleep(2000);
//
//      // Nhập mật khẩu thủ công
//      String password = JOptionPane.showInputDialog("Nhập mật khẩu:");
//      WebElement MatKhauVLU = driver.findElement(By.xpath("//input[@type='password']"));
//      MatKhauVLU.sendKeys(password);
//      Thread.sleep(2000);
//      
//      driver.findElement(By.xpath("//*[@id=\"idSIButton9\"]")).click();
//      Thread.sleep(2000);	      
//  }
//  @Test (priority = 3, dependsOnMethods = "LoginTK")
//  public void AuthenTk() throws InterruptedException {
//	// Kiểm tra xem có trường nhập mã xác thực 2 yếu tố không
//	  
//	  driver.findElement(By.xpath("//*[@id=\"signInAnotherWay\"]")).click();
//	  Thread.sleep(3000);
//	  
//	  driver.findElement(By.xpath("//*[@id=\"idDiv_SAOTCS_Proofs\"]/div[3]/div/div/div[2]")).click();
//	  Thread.sleep(3000);
//	  
//	  String MaXTSMS = JOptionPane.showInputDialog("Nhập Mã Xác Thực:");
//      WebElement MaSMS = driver.findElement(By.xpath("//*[@id=\"idTxtBx_SAOTCC_OTC\"]"));
//      MaSMS.sendKeys(MaXTSMS);
//      Thread.sleep(2000);
//
//      driver.findElement(By.xpath("//*[@id=\"idSubmit_SAOTCC_Continue\"]")).click();
//      Thread.sleep(2000);
//      
//      driver.findElement(By.xpath("//*[@id=\"idSIButton9\"]")).click();
//      Thread.sleep(3000);
//  }
//      try {
//          WebElement AuthenticatorField = driver.findElement(By.xpath("//input[@name='otp']")); // Đổi theo XPath thực tế của trường OTP
//          if (AuthenticatorField.isDisplayed()) {
//              // Nhập mã Authenticator
//              String otpCode = JOptionPane.showInputDialog("Nhập mã Authenticator:");
//              AuthenticatorField.sendKeys(otpCode);
//              Thread.sleep(2000);
//              
//           // Sau khi nhập mã OTP, bấm nút "Yes"
//              WebElement yesButton = driver.findElement(By.xpath("//input[@type='submit']")); // Cập nhật XPath nếu cần
//              if (yesButton.isDisplayed()) {
//                  yesButton.click();
//                  Thread.sleep(20000); // Đợi xác thực và xử lý tiếp
//              } else {
//                  System.out.println("Không tìm thấy nút 'Yes'.");
//              }
//          }
//      } catch (Exception e) {
//          System.out.println("Không có yêu cầu mã xác thực 2 yếu tố.");
//      }
//      
//      try {
//          // Kiểm tra xem có trang Stay Signed In không
//          WebElement staySignedInPage = driver.findElement(By.xpath("//div[contains(text(),'Stay signed in')]"));
//          if (staySignedInPage.isDisplayed()) {
//              System.out.println("Hiển thị trang Stay Signed In.");
//
//              // Tìm và nhấn nút "Yes"
//              WebElement yesButton = driver.findElement(By.xpath("//input[@type='submit' and @value='Yes']"));
//              if (yesButton.isDisplayed()) {
//                  yesButton.click();
//                  System.out.println("Đã nhấn nút Yes.");
//                  Thread.sleep(5000); // Đợi xử lý tiếp tục
//              }
//          }
//      } catch (Exception e) {
//          System.out.println("Không có trang Stay Signed In.");
//      }
  
  @Test (priority = 2, dependsOnMethods = "AuthenTk")
  public void LognOut() throws InterruptedException {

      // Tìm và click vào logo người dùng
      WebElement logo = driver.findElement(By.xpath("//*[@id=\"dropdown-user\"]/span/img"));
      logo.click();

      // Tạm dừng chương trình trong 2 giây để đợi quá trình click vào logo xử lý
      Thread.sleep(2000);

      // Tìm và click vào phần tử có xpath là "/html/body/div[2]/nav/div/ul/li[2]/div/a[2]"
      WebElement out = driver.findElement(By.xpath("/html/body/div[2]/nav/div/ul/li[2]/div/a[2]"));
      out.click();
      Thread.sleep(2000);
  }


  @AfterTest
  public void afterTest() {
	  driver.close();
  }

}
