package quanlihocky;

import org.testng.annotations.Test;

import authen.Authen;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Khoa_HK {
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
        System.out.println(">>> Trình duyệt đã mở và vào trang: " + baseUrl);

        // Khởi tạo WebDriverWait để đợi phần tử
        wait = new WebDriverWait(driver, 10);
    }

    @Test(priority = 1)
    public void AuthenTk() throws InterruptedException {
        // Gọi phương thức đăng nhập từ class Authen
        Authen.AuthenTK(driver);
        System.out.println(">>> Đã thực hiện đăng nhập.");
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
  	  inputHocKy.sendKeys("651");
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
    

    @Test(priority = 3, dependsOnMethods = "AuthenTk")
    public void TimKiemHK651() throws InterruptedException {
        System.out.println(">>> Tìm kiếm học kỳ HIỆN CÓ (651)");

        // Mở danh sách học kỳ
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-menu-navigation\"]/li[2]/a/span"))).click();
        Thread.sleep(3000);
        
        // Nhập học kỳ muốn tìm kiếm
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"tblTerm_filter\"]/label/input")));
        searchBox.sendKeys("651");
        Thread.sleep(3000);

        // Kiểm tra nội dung trong bảng
        WebElement table = driver.findElement(By.xpath("//*[@id=\"tblTerm\"]"));
        if (table.getText().contains("651")) {
            System.out.println("Tìm thấy - Pass");
        } else {
            System.out.println("Không tìm thấy - False");
        }
        Thread.sleep(3000);
        
        
   }
    @Test(priority = 4, dependsOnMethods = "AuthenTk")
    public void KhoaHK651() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/table/tbody/tr/td[8]/div/input"))).click();
        Thread.sleep(3000);
        
    	
    	
    	
    }
    
    
    
    
    
   
    
    
   
    
    
    
   
    

    @AfterClass
    public void teardown() {
        System.out.println(">>> Đóng trình duyệt.");
        if (driver != null) {
            driver.quit();
        }
    }
}
