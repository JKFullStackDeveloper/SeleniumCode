package FrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginEcom {
	
	ExtentReports reports;

	@BeforeTest
	public void ReportConfig() {
		// ExtentSparkReporter, ExtentReports
		
		String path = System.getProperty("user.dir")+"\\Reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Added to Carts Report");
		reporter.config().setDocumentTitle("Cart Details");
		
		reports = new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("Tester", "Juned K");
	}
	
	
	@Test
	public void loginForm() throws InterruptedException {
		
		ExtentTest test = reports.createTest("Cart Demo");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("jm@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Jn@123456789");
		driver.findElement(By.id("login")).click();
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		// System.out.println(driver.findElement(By.cssSelector(".mb-3 b")).getText());
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		// driver.findElement(By.xpath("(//button[@class='btn
		// btn-custom'])[3]")).click();
		driver.close();
		
		reports.flush();
	}

}
