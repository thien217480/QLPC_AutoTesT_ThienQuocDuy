package QuanlyTKB;

import org.testng.annotations.Test;

import authen.Authen;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;

public class Import {
	public String baseUrl = "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/";
	String driverPath = "c:\\chromedriver.exe";
	public WebDriver driver;

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

	@Test(priority = 1)
	public void AuthenTk() throws InterruptedException {
		// Gọi phương thức đăng nhập từ class Authen
		Authen.AuthenTK(driver);
		Thread.sleep(2000);
	}

	@Test(priority = 2, dependsOnMethods = "AuthenTk")
	public void ImportTKB() throws InterruptedException, AWTException {
		// Click vào button thời khóa biểu
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[4]/a")).click();
		// Import thời khóa biểu
		WebElement ulElement = driver.findElement(By.xpath("(//ul[@class='menu-content'])"));
		WebElement firstLiElement = ulElement.findElement(By.tagName("li"));
		Actions actions = new Actions(driver);
		actions.moveToElement(firstLiElement).click().perform();
		Thread.sleep(2000);

		// Arrow_chonhocky
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[2]/div[3]/div/section/div[1]/div/div/div[2]/form/div[1]/div[1]/div/span/span[1]/span/span[2]"))
				.click();
		Thread.sleep(2000);
		// Chọn học kỳ
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[2]/div[3]/div/section/div[1]/div/div/div[2]/form/div[1]/div[1]/div/span[2]/span/span[2]/ul/li[1]"))
				.click();
		Thread.sleep(2000);
		// Arrow ngành
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[2]/div[3]/div/section/div[1]/div/div/div[2]/form/div[1]/div[2]/div/span/span[1]/span/span[2]"))
				.click();
		Thread.sleep(2000);
		// Chọn ngành
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[2]/div[3]/div/section/div[1]/div/div/div[2]/form/div[1]/div[2]/div/span[2]/span/span[2]/ul/li[1]"))
				.click();
		Thread.sleep(2000);
		// Import
		driver.findElement(
				By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div[1]/div/div/div[2]/form/div[2]/div")).click();
		Thread.sleep(2000);

		// Sử dụng Robot để tải file lên
		Robot robot = new Robot();
		// Đặt đường dẫn của file muốn tải lên vào Clipboard
		StringSelection ss = new StringSelection(
				"D:\\anhvanlang\\anh.xlsx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		// Thực hiện hành động tải lên
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);

	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
