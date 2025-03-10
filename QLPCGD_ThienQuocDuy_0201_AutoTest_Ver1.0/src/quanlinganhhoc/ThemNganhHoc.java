package quanlinganhhoc;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import authen.Authen;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ThemNganhHoc {
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
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        // Tìm và nhấn vào menu "Học Kỳ và Ngành"
        WebElement menuHocKyvaNganh = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/Phancong02/Term']")));
        menuHocKyvaNganh.click();
        Thread.sleep(3000);
        
        // Nhấn vào menu "Ngành học"
        WebElement menuNganh = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/Phancong02/Major']")));
        menuNganh.click();
    }

    @Test(priority = 2,dependsOnMethods = "moTrangNganhHoc")
    public void moFormThemNganh() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement btnCreateNew = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(@class, 'createNew')]")
        ));
        
        btnCreateNew.click();
    }

    @Test(priority = 3, dependsOnMethods = "moFormSuaNganh")
    public void ThemNganh() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-1")));

        // Nhập mã ngành
        String id = JOptionPane.showInputDialog("Nhập mã ngành:");
        WebElement idField = driver.findElement(By.id("id"));
        idField.clear();
        idField.sendKeys(id);
        Thread.sleep(1000);

        // Nhập tên ngành
        String name = JOptionPane.showInputDialog("Nhập tên ngành:");
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.clear();
        nameField.sendKeys(name);
        Thread.sleep(1000);

        // Nhập tên viết tắt
        String abbreviation = JOptionPane.showInputDialog("Nhập tên viết tắt:");
        WebElement abbreviationField = driver.findElement(By.id("abbreviation"));
        abbreviationField.clear();
        abbreviationField.sendKeys(abbreviation);
        Thread.sleep(1000);
        
     // Chọn CTDT
        String[] ctdtOptions = {"---- Chọn CTDT ----", "Tiêu chuẩn", "Đặc biệt"};
        String selectedCTDT = (String) JOptionPane.showInputDialog(
            null, 
            "Chọn chương trình đào tạo:", 
            "Cập nhật CTDT", 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            ctdtOptions, 
            ctdtOptions[0]
        );
        // Click vào Select2 để mở danh sách chọn
        WebElement select2Container = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[@id='select2-program_type-container']")
        ));
        select2Container.click();
        Thread.sleep(1000);

        // Chọn giá trị từ danh sách dựa trên lựa chọn
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[contains(text(),'" + selectedCTDT + "')]")
        ));
        option.click();
        Thread.sleep(2000);
        
        // Nhấn nút lưu
        WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Lưu')]"));
        saveButton.click();
        Thread.sleep(3000);
        
        
        
    }
    
    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
