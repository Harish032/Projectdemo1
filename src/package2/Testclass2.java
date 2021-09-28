package package2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testclass2 {
	 public static WebDriver driver;
	    public static String searchdata="Iphone 13";
	    public static String URL="https://www.amazon.in/";
	 
	    public static void main(String[] args) throws InterruptedException {
	        OpenBrowser();
	        OpenApp();
	        search();
	        getTitle();
	        CloseBrowser();
	    }

	    public static void OpenBrowser() {
	    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\00004946\\Desktop\\Automation\\documentation\\chromedriver.exe");
	        WebDriver driver=new ChromeDriver();
	        driver.manage().window().maximize();
	    }

	    public static void OpenApp() {
	        driver.get(URL);
	    }

	    public static void CloseBrowser() {
	        driver.close();
	    }

	    public static void search() throws InterruptedException {
	        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(searchdata);
	        Thread.sleep(2000);
	        driver.findElement(By.id("nav-search-submit-button")).click();    
	        }

	    public static void getTitle() {
	        String title=driver.getTitle();
	        if(title.contains(searchdata)) {
	            System.out.println("Passed");
	            System.out.println(title);
	        }
	        else {
	            System.out.println("Failed");
	            System.out.println(title);
	        }
	    }
	}

