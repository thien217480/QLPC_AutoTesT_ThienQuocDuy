package authen;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Authen {

	public static void AuthenTK(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
//		System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		
//		driver.manage().window().maximize();
//		driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
		
		// Phương thức kiểm tra đăng nhập
		  WebElement XacThucWeb = driver.findElement(By.id("details-button"));
		  XacThucWeb.click();
	      Thread.sleep(2000);
	      
	      WebElement process_web = driver.findElement(By.xpath("//*[@id=\"proceed-link\"]"));
	      process_web.click();
	      Thread.sleep(2000);
	      
	      //click đăng nhập
	      driver.findElement(By.name("provider")).click();
	      Thread.sleep(5000);
	      
	   // Nhập tài khoản thủ công
	      String email = JOptionPane.showInputDialog("Nhập email:");
	      WebElement TaiKhoanVLU = driver.findElement(By.xpath("//input[@type='email']"));
	      TaiKhoanVLU.sendKeys(email);
	      Thread.sleep(2000);

	      driver.findElement(By.xpath("//input[@type='submit']")).click();
	      Thread.sleep(2000);

	      // Nhập mật khẩu thủ công
	      String password = JOptionPane.showInputDialog("Nhập mật khẩu:");
	      WebElement MatKhauVLU = driver.findElement(By.xpath("//input[@type='password']"));
	      MatKhauVLU.sendKeys(password);
	      Thread.sleep(2000);
	      
	      driver.findElement(By.xpath("//*[@id=\"idSIButton9\"]")).click();
	      Thread.sleep(2000);	
	      
	   // Kiểm tra xem có trường nhập mã xác thực 2 yếu tố không
		  
		  driver.findElement(By.xpath("//*[@id=\"signInAnotherWay\"]")).click();
		  Thread.sleep(3000);
		  
		  driver.findElement(By.xpath("//*[@id=\"idDiv_SAOTCS_Proofs\"]/div[3]/div/div/div[2]")).click();
		  Thread.sleep(3000);
		  
		  String MaXTSMS = JOptionPane.showInputDialog("Nhập Mã Xác Thực:");
	      WebElement MaSMS = driver.findElement(By.xpath("//*[@id=\"idTxtBx_SAOTCC_OTC\"]"));
	      MaSMS.sendKeys(MaXTSMS);
	      Thread.sleep(2000);

	      driver.findElement(By.xpath("//*[@id=\"idSubmit_SAOTCC_Continue\"]")).click();
	      Thread.sleep(2000);
	      
	      driver.findElement(By.xpath("//*[@id=\"idSIButton9\"]")).click();
	      Thread.sleep(3000);

	}

}
