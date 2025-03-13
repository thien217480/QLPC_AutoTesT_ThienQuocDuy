package thien;

import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class capnhatthongtin {
    public String baseUrl = "https://mwc.com.vn/";
    String driverPath = "c:\\chromedriver.exe";
    public WebDriver driver;

    @BeforeTest
    public void beforeTest() throws InterruptedException {
        System.out.println("CHẠY TRÌNH DUYỆT CHROME");
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }

    @Test
    // Test Để trống thông tin đăng nhập và nhấn "Đăng Nhập" - TC_KTMWC_01
    public void DangNhapTK() throws InterruptedException {
        WebElement loginBTN = driver.findElement(By.className("account-handle-icon"));
        loginBTN.click();
        Thread.sleep(2000);

        WebElement loginKey = driver.findElement(By.name("UserName"));
        loginKey.sendKeys("thien123456");
        Thread.sleep(2000);

        WebElement password = driver.findElement(By.name("Password"));
        password.sendKeys("thien123");
        Thread.sleep(2000);

        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"form_login\"]/input[3]"));
        loginButton.click();
        Thread.sleep(3000);

        WebElement iconUser = driver.findElement(By.id("account-handle"));
        iconUser.click();
        Thread.sleep(3000);
    }

    @Test
    // Test Nhập thông tin (Tên, Email, Giới tính, Địa chỉ, Tỉnh/Thành phố) 
    public void NhapThongTin() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5); // Dành cho Selenium 3

        // Nhập họ tên
        WebElement tenField = driver.findElement(By.id("FullName"));
        tenField.clear();
        tenField.sendKeys("Lê Công Thiện");
        Thread.sleep(1000);

        // Nhập email
        WebElement emailField = driver.findElement(By.id("Email"));
        emailField.clear();
        emailField.sendKeys("congthien2810@gmail.com");
        Thread.sleep(1000);

        // Chọn giới tính Nam
        WebElement gioiTinhNam = driver.findElement(By.xpath("//div[@class='stardust-radio' and @data-value='1']"));
        gioiTinhNam.click();
        Thread.sleep(1000);

        // Cuộn xuống để thấy ô nhập địa chỉ
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
        Thread.sleep(1000);

        // Nhập thông tin vào ô địa chỉ
        WebElement diaChiField = driver.findElement(By.id("Address"));
        diaChiField.clear();
        diaChiField.sendKeys("123 Đường ABC, Quận 1, TP HCM");
        Thread.sleep(1000);

        // Mở dropdown tỉnh/thành phố
        WebElement tinhThanhDropdown = driver.findElement(By.xpath("//*[@id=\"provinceOptions\"]"));
        tinhThanhDropdown.click();
        Thread.sleep(1000);

        // Chờ dropdown xuất hiện và chọn TP Hồ Chí Minh
        WebElement thanhPhoHCM = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id=\"provinceOptions\"]/option[3]") // Kiểm tra tên chính xác trong dropdown
        ));
        thanhPhoHCM.click();
        Thread.sleep(1000);
        
        
        // Mở dropdown Quận/Huyện
        WebElement quanHuyenDropdown = driver.findElement(By.xpath("//*[@id=\"districtSelect\"]"));
        quanHuyenDropdown.click();
        Thread.sleep(2000);

        // Chờ dropdown xuất hiện và chọn Quận/Huyện
        WebElement quanHuyen = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id=\"districtSelect\"]/option[4]") // Thay đổi giá trị cho đúng quận/huyện bạn muốn chọn
        ));
        quanHuyen.click();
        Thread.sleep(2000);
        
        // Mở dropdown Phường/Xã
        WebElement phuongXaDropdown = driver.findElement(By.xpath("//*[@id=\"wardSelect\"]"));
        phuongXaDropdown.click();
        Thread.sleep(2000);

        // Chờ dropdown xuất hiện và chọn Phường/Xã
        WebElement phuongXa = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id=\"wardSelect\"]/option[2]") // T
        ));
        phuongXa.click();
        Thread.sleep(2000);
        
        
        //Button Lưu
        WebElement buttonGui = driver.findElement(By.xpath("/html/body/div[1]/div/section[1]/div/section[2]/div/div/div[2]/div/div/form/div[2]/div[1]/div[11]/button"));
        buttonGui.click();
        Thread.sleep(2000);
        
   
       
        
    }

    @AfterTest
    public void afterTest() {
        driver.close();
    }
}
