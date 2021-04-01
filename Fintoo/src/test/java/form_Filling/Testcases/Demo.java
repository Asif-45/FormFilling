package form_Filling.Testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import form_Filling.Utilities.ReadConfig;
import form_Filling.Utilities.XLUtils;

public class Demo extends BaseClass{
	ReadConfig readconfig = new ReadConfig();
	
	@Test
	public void trial() throws InterruptedException, IOException  {
		
		
		String excelPath = readconfig.getExcelPath();
		int rownum = XLUtils.getRowCount(excelPath, "Form");
		int colnum = XLUtils.getCellCount(excelPath, "Form", rownum);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//img[@style='width: 100%;   margin: 4px 0;']")).click();
		
		for ( int i = 1; i <= rownum; i++) {
			try {
					
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Online Pre-Appointment']")).click();
		String name = XLUtils.getCellData(excelPath, "Form", i, 0);
		driver.findElement(By.id("name-input")).sendKeys(name);
		Thread.sleep(3000);
		String cnic = XLUtils.getCellData(excelPath, "Form", i, 1);
		driver.findElement(By.id("cnic_input")).sendKeys(cnic);
		Thread.sleep(3000);
		driver.findElement(By.id("nextBtn")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[text()='Branches']")).click();
		Thread.sleep(3000);
		//Select s = new Select(driver.findElement(By.xpath("//span[text()='Clifton']")));
		
		String branchName = XLUtils.getCellData(excelPath, "Form", i, 2);
	try {
		driver.findElement(By.xpath("//span[text()='"+branchName+"']")).click();}
	catch(Exception e) {
		driver.findElement(By.xpath("//span[text()='"+branchName.toUpperCase()+"']")).click();
	}
		Thread.sleep(3000);
		driver.findElement(By.id("nextBtn")).click();
		Thread.sleep(3000);
		String licenseType = XLUtils.getCellData(excelPath, "Form", i, 3);
		driver.findElement(By.xpath("//a[text()='"+licenseType+"']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("nextBtn")).click();
		
		for(int k=1; k<14;k++) {
			
	    try {
			if(XLUtils.getCellData(excelPath, "Form", i, 8).equalsIgnoreCase("9-10")) {
				driver.findElement(By.xpath("(//i[@aria-hidden='true'])[1]")).click();
				driver.findElement(By.id("nextBtn")).click();
				break;
			}
			
			if(XLUtils.getCellData(excelPath, "Form", i, 8).equalsIgnoreCase("10-11")) {
				driver.findElement(By.xpath("(//i[@aria-hidden='true'])[2]")).click();
				driver.findElement(By.id("nextBtn")).click();
				break;
			}
			
			if(XLUtils.getCellData(excelPath, "Form", i, 8).equalsIgnoreCase("11-12")) {
				driver.findElement(By.xpath("(//i[@aria-hidden='true'])[3]")).click();
				driver.findElement(By.id("nextBtn")).click();
				break;
			}
			
			if(XLUtils.getCellData(excelPath, "Form", i, 8).equalsIgnoreCase("12-13")) {
				driver.findElement(By.xpath("(//i[@aria-hidden='true'])[4]")).click();
				driver.findElement(By.id("nextBtn")).click();
				break;
			}
			
					
			if(XLUtils.getCellData(excelPath, "Form", i, 8).equalsIgnoreCase("14-15")) {
				driver.findElement(By.xpath("(//i[@aria-hidden='true'])[5]")).click();
				driver.findElement(By.id("nextBtn")).click();
				break;
			}
			
			if(XLUtils.getCellData(excelPath, "Form", i, 8).equalsIgnoreCase("15-16")) {
				driver.findElement(By.xpath("(//i[@aria-hidden='true'])[6]")).click();
				driver.findElement(By.id("nextBtn")).click();
				break;
			}
			
			if(XLUtils.getCellData(excelPath, "Form", i, 8).equalsIgnoreCase("16-17")) {
				driver.findElement(By.xpath("(//i[@aria-hidden='true'])[7]")).click();
				driver.findElement(By.id("nextBtn")).click();
				break;
			}
			
			if(XLUtils.getCellData(excelPath, "Form", i, 8).equalsIgnoreCase("17-18")) {
				driver.findElement(By.xpath("(//i[@aria-hidden='true'])[8]")).click();
				driver.findElement(By.id("nextBtn")).click();
				break;
			}
			
			if(XLUtils.getCellData(excelPath, "Form", i, 8).equalsIgnoreCase("18-19")) {
				driver.findElement(By.xpath("(//i[@aria-hidden='true'])[9]")).click();
				driver.findElement(By.id("nextBtn")).click();
				break;
			}
			
			if(XLUtils.getCellData(excelPath, "Form", i, 8).equalsIgnoreCase("19-20")) {
				driver.findElement(By.xpath("(//i[@aria-hidden='true'])[10]")).click();
				driver.findElement(By.id("nextBtn")).click();
				break;
			}
			else {
				driver.findElement(By.xpath("(//i[@aria-hidden='true'])["+k+"]")).click();
				driver.findElement(By.id("nextBtn")).click();
				Thread.sleep(3000);
				if(driver.findElement(By.xpath("//h2[contains(text(),'Seat Slot')]")).isDisplayed())
				break;
				
			}
	    }
	    
	    catch(Exception e) {
	    	
	    }
			
		}

		for(int k=1; k<14;k++) {
		
			driver.findElement(By.xpath("(//button[@class='booking-btn'])["+k+"]")).click();
			driver.findElement(By.id("nextBtn")).click();
			Thread.sleep(3000);
			if(driver.findElement(By.xpath("//h2[text()='Please confirm Pre-Appointment details']")).isDisplayed())
			break;
		
		}
		
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		driver.findElement(By.xpath("//a[text()='Verify Pre-Appointment']")).click();
		driver.findElement(By.id("cnic_no")).sendKeys(cnic);
		driver.findElement(By.xpath("//div[text()='Tomorrow/Next Working Day']")).click();
		driver.findElement(By.id("bs-select-1-1")).click();
		driver.findElement(By.xpath("//input[@value='Verify']")).click();
		
	String tokenNo=	driver.findElement(By.id("final_token1")).getText();
	String bookingDate=	driver.findElement(By.id("final-booking-date")).getText();
	String timeSlot=	driver.findElement(By.id("final-time-slot1")).getText();
	String dealTime=	driver.findElement(By.id("final-dealing-time1")).getText();
		XLUtils.setCellData(excelPath, "Form", i, 5, "Done");
		XLUtils.setCellData(excelPath, "Form", i, 6, tokenNo);
		XLUtils.setCellData(excelPath, "Form", i, 7, bookingDate);
		XLUtils.setCellData(excelPath, "Form", i, 10, timeSlot);
		XLUtils.setCellData(excelPath, "Form", i, 9, dealTime);
		driver.findElement(By.xpath("//a[text()='HOME']")).click();
	}
			
			catch(Exception e) {
				XLUtils.setCellData(excelPath, "Form", i, 5, "Error");
				driver.findElement(By.xpath("//a[text()='HOME']")).click();
			}
		}
		
		
		}
	


}
