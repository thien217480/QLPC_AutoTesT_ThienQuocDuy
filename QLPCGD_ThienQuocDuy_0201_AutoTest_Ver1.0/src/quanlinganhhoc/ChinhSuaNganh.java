package quanlinganhhoc;

import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class ChinhSuaNganh {
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
        Thread.sleep(3000);
    }

    @Test(priority = 2, dependsOnMethods = "moTrangNganhHoc")
    public void NutSuaNganh() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String maNganh = "000007810"; // Mã ngành cần chỉnh sửa

        try {
            // Tìm hàng chứa giá trị sorting_1 mong muốn
            WebElement row = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//td[@class='sorting_1' and text()='" + maNganh + "']/parent::tr")
            ));

            // Tìm nút "Chỉnh sửa" trong hàng đó
            WebElement editButton = row.findElement(By.xpath(".//a[contains(@class, 'editRow')]"));

            // Lấy giá trị thuộc tính onclick
            String onClickValue = editButton.getAttribute("onclick");

            // Trích xuất đường dẫn từ popupForm('/Phancong02/Major/Edit/')
            String url = onClickValue.substring(onClickValue.indexOf("('") + 2, onClickValue.indexOf("')"));

            System.out.println("URL Popup: " + url);

            // Dùng JavascriptExecutor để mở popupForm
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("popupForm('" + url + "')");

            System.out.println("✅ Đã mở popup cho ngành có ID: " + maNganh);
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("❌ Không tìm thấy ngành với ID: " + maNganh);
        }
    }

    @Test(priority = 3, dependsOnMethods = "NutSuaNganh")
    public void SuaThongTinNganh() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Nhập "Tên ngành" từ JOptionPane
        String tenNganh = JOptionPane.showInputDialog("Nhập Tên Ngành:");
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("name")));
        nameField.clear();
        nameField.sendKeys(tenNganh);
        System.out.println("✅ Đã nhập Tên ngành: " + tenNganh);
        Thread.sleep(2000);

        // Nhập "Tên viết tắt" từ JOptionPane
        String tenVietTat = JOptionPane.showInputDialog("Nhập Tên Viết Tắt:");
        WebElement abbreviationField = wait.until(ExpectedConditions.elementToBeClickable(By.id("abbreviation")));
        abbreviationField.clear();
        abbreviationField.sendKeys(tenVietTat);
        System.out.println("✅ Đã nhập Tên viết tắt: " + tenVietTat);
        Thread.sleep(2000);

        // Chọn "CTĐT" từ JOptionPane
        String[] options = {"Tiêu chuẩn", "Đặc biệt"};
        int selectedOption = JOptionPane.showOptionDialog(null, "Chọn CTĐT:", "Lựa chọn CTĐT",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        String programValue = (selectedOption == 1) ? "1" : "0";

        WebElement programTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("program_type")));
        Select select = new Select(programTypeDropdown);
        select.selectByValue(programValue);
        System.out.println("✅ Đã chọn CTĐT: " + options[selectedOption]);
        Thread.sleep(2000);

        // Nhấn nút "Lưu"
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Lưu')]")));
        saveButton.click();
        System.out.println("✅ Đã nhấn nút Lưu.");
    }
    @AfterTest
    public void afterTest() {
  	  driver.close();
    }
}

