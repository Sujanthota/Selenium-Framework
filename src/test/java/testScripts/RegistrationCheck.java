package testScripts;

import java.awt.Robot;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import repository.AddtoCartPage;
import repository.HomePage;
import repository.Products;
import repository.RegistrationCheckPage;
import repository.ShoppingCart;
import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
		MethodListener.class })
public class RegistrationCheck {
	public ATUTestRecorder record;

	public HomePage home;
	public Products pro;
	public AddtoCartPage ad;
	public ShoppingCart sh;
	public RegistrationCheckPage reg;
	String expected, actual;

	{
		System.setProperty("atu.reporter.config",
				"D:\\eclipse\\Medicalert.ca\\ATUCONFIG\\atu.properties");
	}
	private WebDriver driver;

//	public WebDriver getDriver() {
//		return driver;
//	}

//	public void setDriver(WebDriver driver) {
//		this.driver = driver;
//	}

	@BeforeTest
	public void Launchsite() throws ATUTestRecorderException {
		home = new HomePage(driver);
		pro = new Products(driver);
		sh = new ShoppingCart(driver);
		ad = new AddtoCartPage(driver);
		reg = new RegistrationCheckPage(driver);
		driver = new FirefoxDriver();
		ATUReports.setWebDriver(driver);
		ATUReports.indexPageDescription = " <b>Medicalert is an e-commerce based site, saving dollars  </br> EXECUTING TESTCASES OF THE PROJECT - MEDICALERT.CA </br> Medicalert is an e-commerce based site, saving dollars </br> EXECUTING TESTCASES OF THE PROJECT - MEDICALERT.CA </br> Medicalert is an e-commerce based site, saving dollars  </br> EXECUTING TESTCASES OF THE PROJECT - MEDICALERT.CA </b>";
		ATUReports.currentRunDescription = " <b> Verifying And Validating the RegistrationCheck Flow Fields </b>";
		ATUReports.setAuthorInfoAtClassLevel("Charan-Automation Test  Engineer",
				Utils.getCurrentTime(), "Build 1.0");
		ATUReports.setAuthorInfo("Charan-Automation Test  Engineer",
				Utils.getCurrentTime(), "Build 1");
		driver.manage().window().maximize();
		driver.get("https://beta.medicalert.ca");
		expected = "Bracelets Jewelry & Products - MedicAlert Canada - MedicAlert.ca";
		actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
		System.out.println("MedicAlert Site was Launched");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(home.Close_popup).click();
		ATUReports.setTestCaseReqCoverage("This is Before test");
		if (driver.getTitle() == expected) {
			ATUReports.add("before test", LogAs.PASSED, new CaptureScreen(
					ScreenshotOf.BROWSER_PAGE));
		}

	}

	public void BeforeMethod() throws Exception {
		home.Click_BrowseStyles();
		pro.Click_Product();
		ad.Select_Size(1);
		ad.Click_AddtoCart();
		sh.Click_Checkout();
		reg.Click_Im_Not_MembetYet();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void Takescreenshot(String xpath, String Testcase) throws Exception {
		if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
			ATUReports.add("Validating "+ Testcase+" Field", LogAs.PASSED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		} else {
			ATUReports.add("Validating "+Testcase+" Field", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	@Test(priority = 1)
	public void ValidatingTheField_SelectTitle() throws Exception {

		ATUReports.setAuthorInfo("Charan-Automation Test  Engineer",
				Utils.getCurrentTime(), "Build 1.0");

		home = new HomePage(driver);
		pro = new Products(driver);
		sh = new ShoppingCart(driver);
		ad = new AddtoCartPage(driver);
		reg = new RegistrationCheckPage(driver);
		BeforeMethod();
		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 1;
		Robot r = new Robot();
		r.mouseWheel(3);

		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());
		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_Password(sheet.getCell(13, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		if (!driver.findElement(reg.Agreement).isSelected()) {
			driver.findElement(reg.Agreement).click();
		}
		reg.Click_Continue();

		ATUReports.setTestCaseReqCoverage("<b> This is testcase 1 </b> ");
		Takescreenshot(reg.title_Err, "SelectTitle");
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 2)
	public void ValidatingTheField_FirstName() throws Exception {
		BeforeMethod();
		ATUReports.setAuthorInfo("Charan-Automation Test  Engineer",
				Utils.getCurrentTime(), "Build 1.0");

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 2;
		Robot r = new Robot();
		r.mouseWheel(3);
		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());
		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_Password(sheet.getCell(13, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		if (!driver.findElement(reg.Agreement).isSelected()) {
			driver.findElement(reg.Agreement).click();
		}
		reg.Click_Continue();
		ATUReports.setTestCaseReqCoverage(" <b> This is testcase 2 </b>");
		Takescreenshot(reg.firstname_Err, "FirstName");
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 3)
	public void ValidatingTheField_LastName() throws Exception {
		BeforeMethod();
		ATUReports.setAuthorInfo("Charan-Automation Test  Engineer",
				Utils.getCurrentTime(), "Build 1.0");

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 3;

		Robot r = new Robot();
		r.mouseWheel(2);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());
		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_Password(sheet.getCell(13, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		if (!driver.findElement(reg.Agreement).isSelected()) {
			driver.findElement(reg.Agreement).click();
		}

		reg.Click_Continue();
		ATUReports.setTestCaseReqCoverage("<b> This is testcase 3 </b>");
		Takescreenshot(reg.lastname_Err, "LastName");
		driver.manage().deleteAllCookies();

	}
	@Test(priority = 4)
	public void ValidatingTheField_Gender() throws Exception {
		BeforeMethod();
		ATUReports.setAuthorInfo("Charan-Automation Test Engineer",
				Utils.getCurrentTime(), "Build 1.0");

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 16;

		Robot r = new Robot();
		r.mouseWheel(2);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());
		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_Password(sheet.getCell(13, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		if (!driver.findElement(reg.Agreement).isSelected()) {
			driver.findElement(reg.Agreement).click();
		}

		reg.Click_Continue();
		ATUReports.setTestCaseReqCoverage("<b> This is TestCase 4 </b>");
		Takescreenshot(reg.gender_Err, "Gender");
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 5)
	public void ValidatingTheField_Day() throws Exception {
		BeforeMethod();
		ATUReports.setTestCaseReqCoverage("<b> This is TestCase 5 </b>");

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 4;

		Robot r = new Robot();
		r.mouseWheel(4);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());
		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_Password(sheet.getCell(13, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		driver.findElement(reg.Agreement).click();
		reg.Click_Continue();
		Takescreenshot(reg.day_Err, "day");
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 6)
	public void ValidatingTheField_Month() throws Exception {
		BeforeMethod();
		ATUReports.setTestCaseReqCoverage("<b> This is TestCase 6 </b>");

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 5;

		Robot r = new Robot();
		r.mouseWheel(4);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());
		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_Password(sheet.getCell(13, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		driver.findElement(reg.Agreement).click();
		reg.Click_Continue();
		Takescreenshot(reg.month_Err, "month");
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 7)
	public void ValidatingTheField_Year() throws Exception {
		BeforeMethod();
		ATUReports.setTestCaseReqCoverage("<b> This is TestCase 7 </b>");

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 6;

		Robot r = new Robot();
		r.mouseWheel(4);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());
		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_Password(sheet.getCell(13, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		driver.findElement(reg.Agreement).click();
		reg.Click_Continue();
		Takescreenshot(reg.year_Err, "year");
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 8)
	public void ValidatingTheField_PhoneArea() throws Exception {
		BeforeMethod();
		ATUReports.setTestCaseReqCoverage("<b> This is TestCase 8 </b>");

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 7;

		Robot r = new Robot();
		r.mouseWheel(4);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());
		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_Password(sheet.getCell(13, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		driver.findElement(reg.Agreement).click();
		reg.Click_Continue();
		Takescreenshot(reg.phonearea_Err, "phoneAera");
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 9)
	public void ValidatingTheField_PhonePrefix() throws Exception {
		BeforeMethod();
		ATUReports.setTestCaseReqCoverage("<b> This is TestCase 9 </b>");

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 8;

		Robot r = new Robot();
		r.mouseWheel(4);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());
		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_Password(sheet.getCell(13, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		driver.findElement(reg.Agreement).click();
		reg.Click_Continue();
		Takescreenshot(reg.phoneprefix_Err, "phoneprefix");
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 10)
	public void ValidatingTheField_PhoneSuffix() throws Exception {
		BeforeMethod();
		ATUReports.setTestCaseReqCoverage("<b> This is TestCase 10 </b>");

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 9;

		Robot r = new Robot();
		r.mouseWheel(4);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_Password(sheet.getCell(13, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		driver.findElement(reg.Agreement).click();
		reg.Click_Continue();
		Takescreenshot(reg.phonesuffix_Err, "phoneSuffix");
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 11)
	public void ValidatingTheField_Email() throws Exception {
		BeforeMethod();
		ATUReports.setTestCaseReqCoverage("<b> This is TestCase 11 </b>");

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 10;

		Robot r = new Robot();
		r.mouseWheel(4);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());

		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_Password(sheet.getCell(13, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		driver.findElement(reg.Agreement).click();
		reg.Click_Continue();
		Takescreenshot(reg.email_Err, "email");
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 12)
	public void ValidationTheField_ConfirmEmail() throws Exception {
		BeforeMethod();
		ATUReports.setTestCaseReqCoverage("<b> This is TestCase 12 </b>");

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 11;

		Robot r = new Robot();
		r.mouseWheel(4);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());

		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_Password(sheet.getCell(13, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		driver.findElement(reg.Agreement).click();
		reg.Click_Continue();
		Takescreenshot(reg.confirmemail_Err, "ConfirmEmail");
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 13)
	public void ValidationSecurityQuestion() throws Exception {
		BeforeMethod();
		ATUReports.setTestCaseReqCoverage("<b> This is TestCase 13 </b>");

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 12;

		Robot r = new Robot();
		r.mouseWheel(4);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());

		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_Password(sheet.getCell(13, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		driver.findElement(reg.Agreement).click();
		reg.Click_Continue();
		Takescreenshot(reg.securityQue_Err, "SecQuestion");
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 14)
	public void ValidatingTheField_SecurityAns() throws Exception {
		BeforeMethod();
		ATUReports.setTestCaseReqCoverage("<b> This is TestCase 14 </b>");

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 13;

		Robot r = new Robot();
		r.mouseWheel(4);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());

		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_Password(sheet.getCell(13, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		driver.findElement(reg.Agreement).click();
		reg.Click_Continue();
		Takescreenshot(reg.securityans_Err, "SecAnswer");
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 15)
	public void ValidatingTheField_Password() throws Exception {
		ATUReports.setTestCaseReqCoverage("<b> This is TestCase 15 </b>");

		BeforeMethod();

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 14;

		Robot r = new Robot();
		r.mouseWheel(4);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());

		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		Thread.sleep(4000);
		driver.findElement(reg.Agreement).click();
		reg.Click_Continue();
		Takescreenshot(reg.password_Err, "password");
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 16)
	public void ValidatingTheField_ConfirmPassword() throws Exception {
		BeforeMethod();
		ATUReports.setTestCaseReqCoverage("<b> This is TestCase 16 </b>");

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 15;

		Robot r = new Robot();
		r.mouseWheel(4);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());

		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_Password(sheet.getCell(13, i).getContents());
		driver.findElement(reg.Agreement).click();
		reg.Click_Continue();
		Takescreenshot(reg.confirmpasswd_Err, "Confirmpassword");
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 17)
	public void ValidatingTheField_AgreementCheckBox() throws Exception {
		BeforeMethod();
		ATUReports.setTestCaseReqCoverage("<b> This is TestCase 17 </b>");

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 16;

		Robot r = new Robot();
		r.mouseWheel(4);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());

		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_Password(sheet.getCell(13, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		reg.Click_Continue();
		Takescreenshot(reg.agreement_Err, "Agreement");
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 18)
	public void ValidatingTheField_ContinueButton() throws Exception {
		BeforeMethod();

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 16;

		Robot r = new Robot();
		r.mouseWheel(4);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());

		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_Password(sheet.getCell(13, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		driver.findElement(reg.Agreement).click();
		reg.Click_Continue();
		driver.manage().deleteAllCookies();
	}

	@Test(priority = 19)
	public void ValidatingTheAlert() throws Exception {
		home = new HomePage(driver);
		pro = new Products(driver);
		sh = new ShoppingCart(driver);
		ad = new AddtoCartPage(driver);
		reg = new RegistrationCheckPage(driver);
	
		BeforeMethod();

		FileInputStream file = new FileInputStream(
				"D:/eclipse/Medicalert.ca/TestData/MedicAlertData.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet sheet = wb.getSheet(1);
		int i = 16;

		Robot r = new Robot();
		r.mouseWheel(4);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		reg.Select_Title(sheet.getCell(0, i).getContents());
		reg.Type_FirstName(sheet.getCell(1, i).getContents());
		reg.Type_LastName(sheet.getCell(2, i).getContents());
		if (!driver.findElement(reg.Gender_Female).isSelected()) {
			driver.findElement(reg.Gender_Female).click();
		}
		reg.Select_Day(sheet.getCell(3, i).getContents());
		reg.Select_Month(sheet.getCell(4, i).getContents());
		reg.Select_Year(sheet.getCell(5, i).getContents());
		reg.Type_PhoneArea(sheet.getCell(6, i).getContents());
		reg.Type_Phoneprefix(sheet.getCell(7, i).getContents());
		reg.Type_Phonesuffix(sheet.getCell(8, i).getContents());

		if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
			reg.Click_PrefferedLanguage();
		}
		reg.Type_Email(sheet.getCell(9, i).getContents());
		reg.Type_ConfirmEmail(sheet.getCell(10, i).getContents());
		reg.Select_SecurityQuestion(2);
		reg.Type_SecurityAnswer(sheet.getCell(12, i).getContents());
		reg.Type_Password(sheet.getCell(13, i).getContents());
		reg.Type_ConfirmPassword(sheet.getCell(14, i).getContents());
		driver.findElement(reg.Agreement).click();
		reg.Click_Continue();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		if(driver.findElement(By.xpath("//div[@class='server-errorBox']")).isDisplayed())
			
		{
			driver.findElement(By.xpath("//a[contains(.,'X')]")).click();
			
			int j =17;
			r.mouseWheel(5);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			reg.Select_Title(sheet.getCell(0, j).getContents());
			
			reg.Type_FirstName(sheet.getCell(1, j).getContents());
			reg.Type_LastName(sheet.getCell(2, j).getContents());
			if (!driver.findElement(reg.Gender_Female).isSelected()) {
				driver.findElement(reg.Gender_Female).click();
			}
			reg.Select_Day(sheet.getCell(3, j).getContents());
			reg.Select_Month(sheet.getCell(4, j).getContents());
			reg.Select_Year(sheet.getCell(5, j).getContents());
			reg.Type_PhoneArea(sheet.getCell(6, j).getContents());
			reg.Type_Phoneprefix(sheet.getCell(7, j).getContents());
			reg.Type_Phonesuffix(sheet.getCell(8, j).getContents());

			if (!driver.findElement(reg.Prefferedlanguage_French).isSelected()) {
				reg.Click_PrefferedLanguage();
			}

			reg.Select_SecurityQuestion(2);
			reg.Type_SecurityAnswer(sheet.getCell(12, j).getContents());
			reg.Type_Password(sheet.getCell(13, i).getContents());
			reg.Type_ConfirmPassword(sheet.getCell(14, j).getContents());
			reg.Click_Continue();
		}
}
}