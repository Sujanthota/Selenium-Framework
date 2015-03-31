package repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegistrationCheckPage {
	WebDriver driver;
	public By IamAlreadyMember = By
			.xpath("//label[contains(.,'I’m already a MedicAlert member')]");
	public By IamNotaMemberYet = By
			.xpath("//label[contains(.,'I’m not a member yet')]");
	public By Select_Title = By
			.xpath("//select[contains(@id,'ddlMemberTitle')]");
	public By FirstName = By.xpath("//input[contains(@id,'txtFirstName1')]");
	public By MiddleName = By.xpath("//input[contains(@id,'txtMiddleName1')]");
	public By LastName = By.xpath("//input[contains(@id,'txtLastName1')]");
	public By Gender_Male = By.xpath("//label[contains(.,'Male')]");
	public By Gender_Female = By.xpath("//label[contains(.,'Female')]");
	public By Day = By.xpath("//select[contains(@class,'select-day')]");
	public By Month = By.xpath("//select[contains(@class,'select-month')]");
	public By Year = By.xpath("//select[contains(@class,'select-year')]");
	public By PhoneArea = By
			.xpath("//input[contains(@id,'txtMemberPhoneAreaHome')]");
	public By PhonePrefix = By
			.xpath("//input[contains(@name,'txtMemberPhonePrefixHome')]");
	public By PhoneSuffix = By
			.xpath("//input[contains(@id,'txtMemberPhoneSuffixHome')]");
	public By Prefferedlanguage_English = By
			.xpath("//label[contains(.,'English')]");
	public By Prefferedlanguage_French = By
			.xpath("//label[contains(.,'French')]");
	public By Email = By.xpath("//input[@placeholder='Email']");
	public By ConfirmEmail = By.xpath("//input[@placeholder='Confirm Email']");
	public By SecurityQuestion = By
			.xpath("//select[contains(@id,'ddlMemberChallenge')]");
	public By SecurityAnswer = By
			.xpath("//input[contains(@placeholder,'Answer')]");
	public By Password = By
			.xpath("//input[contains(@class,'TextboxField RelPassword')]");
	public By ConfirmPassword = By
			.xpath("//input[contains(@id,'txtConfirmPsswd')]");
	public By Agreement = By.xpath("//input[contains(@name,'chkAgreement')]");
	public By Continue = By.xpath("//input[contains(@id,'btnContinue')]");
	public By BackToShoppingCart = By.xpath("//input[@tabindex='47']");
	public By Header = By.xpath("//span[contains(@id,'lblStepTitle')]");

	public String title_Err = "//span[contains(.,' Please select title.')]";
	public String firstname_Err = "//span[contains(.,' Please enter first name.')]";
	public String lastname_Err = "//span[contains(.,' Please enter last name')]";
	public String gender_Err = "//span[contains(.,' Please select gender.')]";
	public String day_Err = "//span[contains(@id,'rfvDay')]";
	public String month_Err = "//span[contains(@id,'rfvMonth')]";
	public String year_Err = "//span[contains(@id,'rfvYear')]";
	public String phonearea_Err = "//span[contains(@id,'rfvMemberPhoneArea')]";
	public String phoneprefix_Err = "//span[contains(@id,'rfvMemberPhonePrefix')]";
	public String phonesuffix_Err = "//span[contains(@id,'rfvMemberPhoneSuffix')]";
	public String email_Err = "//span[contains(@id,'rfvEmail')]";
	public String confirmemail_Err = "//span[contains(@id,'rfvConfirmationEmail')]";
	public String securityQue_Err = "//span[contains(@id,'rfvMemberChallenge')]";
	public String securityans_Err = "//span[contains(@id,'rfvUpdateResponse')]";
	public String password_Err = "//span[contains(@id,'rfvPassword')]";
	public String confirmpasswd_Err = "//span[contains(@id,'rfvconfirmPassword')]";
	public String agreement_Err = "//span[contains(@id,'lblMarkAgree')]";

	public RegistrationCheckPage(WebDriver driver) {
		this.driver = driver;
	}

	public void Click_Im_Not_MembetYet() throws InterruptedException {
		driver.findElement(IamNotaMemberYet).click();
		Thread.sleep(4000);
	}

	public void Select_Title(String Title) {
		Select s1 = new Select(driver.findElement(Select_Title));
		s1.selectByVisibleText(Title);

	}

	public void Type_FirstName(String First) {
		driver.findElement(FirstName).sendKeys(First);
	}

	public void Type_MiddleName(String Middle) {
		driver.findElement(MiddleName).sendKeys(Middle);
	}

	public void Type_LastName(String Last) {
		driver.findElement(LastName).sendKeys(Last);
	}

	public void Click_GenderMale() {
		driver.findElement(Gender_Male);

	}

	public void Click_GenderFemale() {
		driver.findElement(Gender_Female);

	}

	public void Select_Day(String day) {
		Select s2 = new Select(driver.findElement(Day));
		s2.selectByVisibleText(day);
	}

	public void Select_Month(String month) {
		Select s3 = new Select(driver.findElement(Month));
		s3.selectByVisibleText(month);
	}

	public void Select_Year(String year) {
		Select s4 = new Select(driver.findElement(Year));
		s4.selectByVisibleText(year);
	}

	public void Type_PhoneArea(String ph1) {
		driver.findElement(PhoneArea).sendKeys(ph1);
	}

	public void Type_Phoneprefix(String ph2) {
		driver.findElement(PhonePrefix).sendKeys(ph2);
	}

	public void Type_Phonesuffix(String ph3) {
		driver.findElement(PhoneSuffix).sendKeys(ph3);
	}

	public void Click_PrefferedLanguage() {
		driver.findElement(Prefferedlanguage_French).click();
	}

	public void Type_Email(String email) {
		driver.findElement(Email).sendKeys(email);
	}

	public void Type_ConfirmEmail(String confirmEmail) {
		driver.findElement(ConfirmEmail).sendKeys(confirmEmail);
	}

	public void Select_SecurityQuestion(int question) {
		Select s5 = new Select(driver.findElement(SecurityQuestion));
		s5.selectByIndex(question);
	}

	public void Type_SecurityAnswer(String securityanswer) {
		driver.findElement(SecurityAnswer).sendKeys(securityanswer);
	}

	public void Type_Password(String passwd) {
		driver.findElement(Password).sendKeys(passwd);
	}

	public void Type_ConfirmPassword(String confrmPass) {
		driver.findElement(ConfirmPassword).sendKeys(confrmPass);
	}

	public void Click_AgreementCheckbox() {
		driver.findElement(Agreement).clear();
	}

	public void Click_Continue() {
		driver.findElement(Continue).click();
	}

	public void Click_BackToCart() {
		driver.findElement(BackToShoppingCart).click();
	}

	public void Header_Title() {
		if (driver.findElement(Header).getText().contains("Registration")) {
			System.out.println("RegistrationCheck Page is Displayed ");
		}
	}

}
