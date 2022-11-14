import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver","D:\\Niranjan\\Software Testing\\Selenium - Udemy - Rahul Shetty\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		driver.get("https://www.themahuastore.com/");
		driver.findElement(By.xpath("//span[text()=\"Unisex Round Neck T-Shirts\"]/parent::a")).click();

		AddItemToCart(driver, "the-forest-soul-series-elephant", "M", 2);
		AddItemToCart(driver, "sailor-at-heart", "L", 2);
	}

	public static void AddItemToCart(WebDriver driver, String productName, String size, int qty) {
		
		driver.findElement(By.xpath("//a[@href=\"/products/" + productName + "\"]")).click();
		driver.findElement(By.xpath("//input[@value=\"" + size + "\"]/following-sibling::label[2]")).click();
		
		while(qty>1)
		{
			driver.findElement(By.xpath("//button[@class=\"quantity__button no-js-hidden\"][2]")).click();
			qty--;
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		WebElement Search = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@type=\"submit\"]"))));
		Search.click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[@class='cart-notification__heading caption-large']"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@class='cart-notification__close modal__close-button link link--text focus-inset']"))));

		driver.findElement(By.xpath("//span[text()=\"Unisex Round Neck T-Shirts\"]/parent::a")).click();		
	}

}
