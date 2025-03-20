package quanlinganhhoc;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ChinhSuaHoSo.Authen;
import io.github.bonigarcia.wdm.WebDriverManager;
public class DanhSachNganh {
	private final String baseUrl = "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login";
    private WebDriver driver;
    
    // Hàm khởi tạo WebDriver
    public WebDriver initializeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\Admin\\AppData\\Local\\Google\\Chrome\\User Data");
        return new ChromeDriver(options);
    }

    @BeforeTest
    public void beforeTest() throws InterruptedException {
        driver = initializeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        
        // Gọi phương thức đăng nhập từ Authen
        Authen.AuthenTK(driver);
    }
    @Test(priority = 1)
    public void moTrangNganhHoc() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,10);

            // Tìm và nhấn vào menu "Học Kỳ và Ngành"
            WebElement menuHocKyvaNganh = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/Phancong02/Term']")));
            menuHocKyvaNganh.click();
            Thread.sleep(3000);
            // Nhấn vào menu "Ngành học"
            WebElement menuNganh = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/Phancong02/Major']")));
            menuNganh.click();
            }
    
    @Test(priority =  2, dependsOnMethods = "moTrangNganhHoc"  )
    public void XemDanhSach() throws InterruptedException  {
    	        WebDriverWait wait = new WebDriverWait(driver, 10);
    	        
    	        // Chờ bảng danh sách ngành hiển thị
    	        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tblMajor_wrapper")));

    	        // Tùy chọn số lượng hiển thị
    	        WebElement dropdown = driver.findElement(By.name("tblMajor_length"));
    	        Select select = new Select(dropdown);

    	        // Kiểm tra với các giá trị khác nhau
    	        for (String value : new String[]{"10", "25", "50", "-1"}) {
    	            select.selectByValue(value);
    	            Thread.sleep(3000); // Đợi tải dữ liệu
    	            
    	            // Lấy danh sách ngành học từ bảng
    	            List<WebElement> rows = driver.findElements(By.xpath("//table[@id='tblMajor']//tbody/tr"));
    	            System.out.println("Số ngành hiển thị (" + value + "): " + rows.size());
    	            
    	            // In tên ngành học
    	            if (value.equals("10")) {
    	                System.out.println("Danh sách ngành học:");
    	                for (WebElement row : rows) {
    	                    List<WebElement> cols = row.findElements(By.tagName("td"));
    	                    if (cols.size() > 1) {
    	                        System.out.println("Ngành: " + cols.get(1).getText());
    	                    }
    	                }
    	            }
    	        }
	}
	    @AfterTest
	    public void afterTest() {
	  	  driver.close();
	    }
	}



