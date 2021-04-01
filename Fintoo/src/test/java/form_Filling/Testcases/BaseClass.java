package form_Filling.Testcases;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import form_Filling.Utilities.ReadConfig;

public class BaseClass extends TestListenerAdapter {

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	ITestResult tr;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	ReadConfig readconfig = new ReadConfig();
	public String baseURL = readconfig.getApplicationURL();
	public static WebDriver driver;

	public String browser = readconfig.getBrowser();

	@BeforeSuite
	public void start() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		String repName = "Test-Report-" + timeStamp + ".html";

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/extent-report/" + repName);// specify
																											// location
																											// of the
																											// report
		htmlReporter.setAppendExisting(true);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "Asif");

		htmlReporter.config().setDocumentTitle("Finance Test Project"); // Tile of report
		htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
		// logger=extent.createTest("Testing");

		// extentTest.set(logger);

	}

	@BeforeClass
	public void setReport() {
		 
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			
			String downloadFilepath = "//D:/Download/";
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.prompt_for_download", "false");
			chromePrefs.put("download.default_directory", downloadFilepath);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			 driver = new ChromeDriver(cap);
			//driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
		driver.manage().window().maximize();

	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		//driver.quit();
		// extent.flush();
		if (result.getStatus() == ITestResult.FAILURE) {

			htmlReporter.setAppendExisting(true);
			WebDriver driver = null;
			extentTest.get().fail(result.getThrowable());
			Object testObject = result.getInstance();
			Class clazz = result.getTestClass().getRealClass();
			try {
				driver = (WebDriver) clazz.getDeclaredField("driver").get(testObject);
			} catch (Exception e) {
				// TODO Auto-generated catch block

			}
			try {
				captureScreen(result.getMethod().getMethodName());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\"
					+ result.getMethod().getMethodName() + ".png";

			File f = new File(screenshotPath);

			if (f.exists()) {
				try {
					extentTest.get()
							.fail("Screenshot is below:" + extentTest.get().addScreenCaptureFromPath(screenshotPath));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.get().log(Status.PASS,
					MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.GREEN)); // send the passed
																										// information
			}
		if (result.getStatus() == ITestResult.SKIP) {
			extentTest.get().log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
		}

	}

	@AfterClass
	public void close() {
		driver.close();
	}
	@AfterSuite
	public void down() {
		extent.flush();
	}

	public void captureScreen(String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}

	public String randomestring() {
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return (generatedstring);
	}

	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}

}