package package2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testclass1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\00004946\\Desktop\\Automation\\documentation\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");
        Thread.sleep(3000);
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Iphone 13");
        Thread.sleep(2000);
        driver.findElement(By.id("nav-search-submit-button")).click();
        Thread.sleep(2000);
        driver.close();
	}

}
