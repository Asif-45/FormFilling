package form_Filling.Utilities;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class functions {

	public static WebDriver driver;
	
	
	public functions(WebDriver driver) {
		functions.driver=driver;
	}
	
	public static boolean waitTillElementisDisplayed(WebElement element,long timeOutInSeconds) {
		WebDriverWait wait=new WebDriverWait(driver,timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	
		return true;
	}
	
	public String isFileDownloaded() throws Exception {
	   /* final int SLEEP_TIME_MILLIS = 1000;
	  String  filePath= "//C:/Users/Hp/Downloads/minty";
	    File file = new File(filePath);
	    final int timeout = 60* SLEEP_TIME_MILLIS;
	    int timeElapsed = 0;
	    while (timeElapsed<timeout){
	        if (file.exists()) {
	            System.out.println(file.getName()+"  is present");
	            return true;
	        } else {
	            timeElapsed +=SLEEP_TIME_MILLIS;
	            Thread.sleep(SLEEP_TIME_MILLIS);
	        }
	    }
	    return false;*/
		
		String folderName = "//D:/Download/"; // Give your folderName
		File[] listFiles = new File(folderName).listFiles();
		String fileName="null";
		for (int i = 0; i < listFiles.length; i++) {

		    if (listFiles[i].isFile()) {
		         fileName = listFiles[i].getName();
		        if (fileName.startsWith("Invoice")
		                && fileName.endsWith(".pdf")) {
		            System.out.println("found file" + " " + fileName);
		            
		        }
		    }
		}
		return fileName;
		
	}
	

	
}
