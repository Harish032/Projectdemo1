package package2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;

public class DDF4 {

	
	public static WebDriver myD;
	public static String vSearch;
	public static String vURL;
	public static int xlRows;
	public static int xlCols;
	public static String xData[][];
	public static String vTitle;
	
	public static void main(String[] args) throws Exception 	
	{
		
		xlRead("C:\\Users\\00004946\\Desktop\\Automation\\AmazonDataFrameworks.xls");
		
		for (int i=1;i<xlRows;i++)
		{
		if (xData[i][1].equalsIgnoreCase("Y"))
		
		vSearch=xData[i][2];
		
		BrowserCall();
		OPENAPP();
		Search();
		getTitle();	
		
		if(vTitle.contains(xData[i][3]))
		{
			xData[i][4]=vTitle;
			xData[i][5]="PASS";
		
		}
		else
		{
			xData[i][4]=vTitle;
			xData[i][5]="FAIL";
			
		}
		
		
		
		CloseAPP();
		}
		
		
		xlwrite("C:\\Users\\00004946\\Desktop\\Automation\\results.xls", xData);
		
		
		}
		
	
	

	public static void BrowserCall()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\00004946\\Desktop\\Automation\\documentation\\chromedriver.exe");
		myD=new ChromeDriver();
		
		myD.manage().window().maximize();
		myD.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	public static void OPENAPP() 
	{
		vURL="https://www.amazon.in";
		myD.get(vURL);
	}
	public static void Search() 	{
		myD.findElement(By.id("twotabsearchtextbox")).sendKeys(vSearch);
		myD.findElement(By.id("nav-search-submit-button")).click();
	}
	
	public static void getTitle()
	{
		vTitle=myD.getTitle();
	}
	
	public static void CloseAPP()
	{
		myD.close();
	}
	
	

	public static void xlRead(String sPath) throws Exception
	{
		File myFile=new File(sPath);
		FileInputStream myStream=new FileInputStream(myFile);
		HSSFWorkbook myworkbook=new HSSFWorkbook(myStream);
		HSSFSheet mySheet=myworkbook.getSheetAt(0);
		xlRows=mySheet.getLastRowNum()+1;
		xlCols=mySheet.getRow(0).getLastCellNum();
		System.out.println("Row Number is "+xlRows);
		System.out.println("Col Number is "+xlCols);
		xData=new String[xlRows][xlCols];
		for(int i=0;i<xlRows;i++)
		{
			HSSFRow row=mySheet.getRow(i);
			for(short j=0;j<xlCols;j++)
			{
				HSSFCell cell=row.getCell(j);
				String value=cellToString(cell);
				xData[i][j]=value;
				System.out.print("-"+xData[i][j]);
			}
			System.out.println();
		}
	}
		@SuppressWarnings("deprecation")
		public static String cellToString(HSSFCell cell)
		{
			int type=cell.getCellType();
			Object result;
			switch(type)
			{
			case HSSFCell.CELL_TYPE_NUMERIC:
			result=cell.getNumericCellValue();
			break;
			case HSSFCell.CELL_TYPE_STRING:
			result=cell.getStringCellValue();
			break;
			case HSSFCell.CELL_TYPE_FORMULA:
			throw new RuntimeException("We cannot evaluate formula");
			case HSSFCell.CELL_TYPE_BLANK:
			result="-";
			case HSSFCell.CELL_TYPE_BOOLEAN:
			result=cell.getBooleanCellValue();
			case HSSFCell.CELL_TYPE_ERROR:
			result="This cell has some error";
			default:
			throw new RuntimeException("We do not support this cell type");
			}
			return result.toString();
			
		}
	
	



public static void xlwrite(String xlpath1,String[][] xData) throws Exception
{
	System.out.println("Inside XL Write");
	File myFile1=new File(xlpath1);
	FileOutputStream fout=new FileOutputStream(myFile1);
	HSSFWorkbook wb=new HSSFWorkbook();
	HSSFSheet mySheet1=wb.createSheet("TestResults");
	for(int i=0;i<xlRows;i++)
	{
		HSSFRow row1=mySheet1.createRow(i);
		for(short j=0;j<xlCols;j++)
		{
			HSSFCell cell1=row1.createCell(j);
			cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell1.setCellValue(xData[i][j]);
		}
	}
	wb.write(fout);
	fout.flush();
	fout.close();
}
}