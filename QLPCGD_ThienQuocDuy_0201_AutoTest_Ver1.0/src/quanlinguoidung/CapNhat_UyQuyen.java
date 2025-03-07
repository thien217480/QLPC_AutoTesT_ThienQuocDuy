package quanlinguoidung;
	

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import authen.Authen;
import io.github.bonigarcia.wdm.WebDriverManager;

	public class CapNhat_UyQuyen {
	
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
	    public void MoNguoiDung() {
	        WebDriverWait wait = new WebDriverWait(driver, 10);
	
	        // Nhấn vào mục "Hồ sơ cá nhân"
	        WebElement Nguoidung = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,\"/Phancong02/User\")]")));
	        Nguoidung.click();        
		        
		 } 
	    @Test(priority = 2, dependsOnMethods = "MoNguoiDung") 
	    public void CapNhatNguoiDung() throws InterruptedException {
	        WebDriverWait wait = new WebDriverWait(driver, 10);

	        // Nhấn vào biểu tượng "Chỉnh sửa"
	        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//a[contains(@class,'editRow')]")
	        ));
	        editButton.click();

	        // Chờ popup cập nhật xuất hiện
	        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.id("ui-id-1")
	        ));

	        // Nhập mã giảng viên
	        String staffId = JOptionPane.showInputDialog("Nhập mã giảng viên:");
	        WebElement staffIdField = wait.until(ExpectedConditions.elementToBeClickable(By.id("staff_id")));
	        staffIdField.clear();
	        staffIdField.sendKeys(staffId);
	        Thread.sleep(3000); 

	        // Nhập tên giảng viên
	        String fullName = JOptionPane.showInputDialog("Nhập tên giảng viên:");
	        WebElement fullNameField = driver.findElement(By.id("full_name"));
	        fullNameField.clear();
	        fullNameField.sendKeys(fullName);
	        Thread.sleep(3000); 

	        // Nhập email
	        String email = JOptionPane.showInputDialog("Nhập email:");
	        WebElement emailField = driver.findElement(By.id("email"));
	        emailField.clear();
	        emailField.sendKeys(email);
	        Thread.sleep(3000); 

	        // Chọn loại giảng viên
	        String[] loaiGiangVienOptions = {"Cơ hữu (CH)", "Thỉnh giảng (TG)"};
	        String selectedLoaiGV = (String) JOptionPane.showInputDialog(
	            null, "Chọn loại giảng viên:", "Loại giảng viên",
	            JOptionPane.QUESTION_MESSAGE, null, loaiGiangVienOptions, loaiGiangVienOptions[0]
	        );

	        String loaiGiangVienValue = selectedLoaiGV.contains("Cơ hữu") ? "CH" : "TG";
	        Select loaiGiangVienSelect = new Select(driver.findElement(By.id("type")));
	        loaiGiangVienSelect.selectByValue(loaiGiangVienValue);
	        Thread.sleep(3000);


	        // Chọn role (Hiển thị rõ tên)
	        String[] roleOptions = {
	            "BCN khoa", "Bộ môn", "Giảng viên", "Chưa phân quyền"
	        };
	        String selectedRole = (String) JOptionPane.showInputDialog(
	            null, "Chọn role:", "Role",
	            JOptionPane.QUESTION_MESSAGE, null, roleOptions, roleOptions[0]
	        );

	        String roleValue = selectedRole.contains("BCN khoa") ? "5da37603-a272-4b38-8978-422da0b76e0f" :
	                           selectedRole.contains("Bộ môn") ? "8c78995c-c174-4995-9635-fd701054f759" :
	                           selectedRole.contains("Giảng viên") ? "b8046948-0910-41f4-a79d-9474126fce12" :
	                           "c0653144-928b-49bd-b98c-512c9f9391d";

	        Select roleSelect = new Select(driver.findElement(By.id("role_id")));
	        roleSelect.selectByValue(roleValue);
	        Thread.sleep(3000); 
	        
	     // Kiểm tra checkbox "Việt Nam"
	        WebElement vietnameseCheckbox = driver.findElement(By.id("is_vietnamese"));
	        boolean isVietnamese = JOptionPane.showConfirmDialog(null, "Người dùng có phải là người Việt Nam?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
	        if (isVietnamese) {
	            if (!vietnameseCheckbox.isSelected()) {
	                vietnameseCheckbox.click();
	            }
	        } else {
	            if (vietnameseCheckbox.isSelected()) {
	                vietnameseCheckbox.click();
	            }
	        }
	        Thread.sleep(5000);

	        // Nhấn nút "Lưu"
	        WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Lưu')]"));
	        saveButton.click();
	        Thread.sleep(3000); 

	        
	    }
	    }

