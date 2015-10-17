package com.ps.primerica.pages;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ps.primerica.components.DateComponent;

public class CMPage extends BasePage {

	private DateComponent dateComponent;
	
	private By PERSON_LIST = By.id("PersonList");
	private By PERSON_LIST_ITEM = By.cssSelector(".person-list-item.clickable");

	private final String ADD_CONTACT_FORM_URL = "https://test.primericaonline.com/wlpol/apps/services/www/Primerica/desktopbrowser/default/index.html#/cm/contacts/addContact";

	
	public static final String ADD_CONTACT = "Add Contact";
	public static final String CANCEL = "Cancel";
	public static final String IMPORT_CONTACTS = "Import Contacts";
	public static final String GETTING_STARTED_TUTORIAL = "Getting Started tutorial";

	protected String dateString_slash = "01/03/2016";
	protected String dateString_dash = "01-04-2016";
	protected String dateString_dot = "01.05.2016";
	protected String dateString_invalid_format = "12.12/20a5";
	protected String dateString_invalid_range = "12/25/1989";

	protected final String ERROR_MESSAGE_DATE_REQUIRED = "Date required.";
	protected final String ERROR_MESSAGE_DATE_FORMAT = "Invalid date format.";
	protected final String ERROR_MESSAGE_DATE_RANGE = "Date must be between 01/01/1900 and";
	
	private String USERNAME = "Brian";
	
	// Dates
	private By spouseDOB = By.name("spouseDOB");
	private By primaryDOB = By.name("primaryDOB");
	private By primaryLastContact = By.name("primaryLastContact");
	private By childDOB = By.name("childDOB");
	private By apptDate= By.name("apptDate");

	private By panelTitle = By.cssSelector(".panel-title");
	By saveButton = By.cssSelector("button[title='Save']");
	By saveButton2 = By.cssSelector("button[title=\"Save\"]");
	By viewContact = By.cssSelector("[ng-click=\"viewContact()\"]");
	
	public CMPage(WebDriver driver) {
		super(driver);
		this.dateComponent = new DateComponent(driver);
	}

	public void waitForPersonList() {
	    WAIT_60.until(ExpectedConditions.visibilityOfElementLocated(PERSON_LIST));
	    sleep(10000);
	}
	
	public void goToAddContactForm() {
		driver.get(ADD_CONTACT_FORM_URL);	
		sleep(2000);

	}
	
	public void checkPersonList() {
		WAIT_60.until(ExpectedConditions.visibilityOfElementLocated(PERSON_LIST_ITEM));
        List<WebElement> items = driver.findElements(PERSON_LIST_ITEM);
        Assert.assertTrue("Person list should not be empty", CollectionUtils.isNotEmpty(items));
	}
	
	public void fillRequiredFieldsInContactForm() {
		addRequiredContactFormValues(USERNAME);
	}

	public void fillRequiredFieldsInSpouseContactForm() {
		addRequiredSpouseContactFormValues(USERNAME);
	}

	public void fillRequiredFieldsInChildrenContactForm() {
		addRequiredChildrenContactFormValues(USERNAME);
	}

	public void fillDateFieldsInContactForm() {
		sleep(1000);
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(primaryDOB));
		WebElement dateField = driver.findElement(primaryDOB);
		dateComponent.sendDate(dateField, "05/05/1995");
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(primaryLastContact));
		dateField = driver.findElement(primaryLastContact);
		dateComponent.sendDate(dateField, "06/06/1996");
	}

	public void fillFutureDateInLastContactAndValidate() {
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(primaryLastContact));
		WebElement dateField = driver.findElement(primaryLastContact);
		dateComponent.sendDate(dateField, "12/12/2015");
		sleep(2000);
		By errorBy = By.xpath("//*[@name='primaryLastContact']/parent::*/following-sibling::*[1]");
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(errorBy));
		WebElement errorLabel = driver.findElement(errorBy);
		String error = errorLabel.getAttribute("innerText");
        Assert.assertTrue(error.contains(ERROR_MESSAGE_DATE_RANGE));
	// saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
	//	saveButton.click();
	//	sleep(2000);
//		dateField = driver.findElement(By.name("primaryLastContact"));
//        Assert.assertTrue(dateField.isDisplayed());
 	}

	public void fillDateFieldsInSpouseContactForm() {
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(spouseDOB));
		WebElement dateField = driver.findElement(spouseDOB);
		dateComponent.sendDate(dateField, "07/07/1997");
	}

	public void fillDateFieldsInChildrenContactForm() {
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(childDOB));
		WebElement dateField = driver.findElement(childDOB);
		dateComponent.sendDate(dateField, "08/08/1998");
	}
	
	public void fillDateFieldsInAppointmentContactForm() {
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(apptDate));
		WebElement dateField = driver.findElement(apptDate);
		dateComponent.sendDate(dateField, "09/09/2001");
	}

	public void validateDatesInContactView() {
		By lastContactDate = By.cssSelector("[ng-show=\"contact.lastContactDate\"]");
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(lastContactDate));
		WebElement dateField = driver.findElement(lastContactDate);
		dateComponent.validateDateInDiv("Last Contact date", dateField, "06/06/1996");
		By dateOfBirth = By.cssSelector("[ng-show=\"contact.dateOfBirth\"]");
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(dateOfBirth));
		dateField = driver.findElement(dateOfBirth);
		dateComponent.validateDateInDiv("Date of Birth", dateField, "05/05/1995");
	}

	public void validateSpouseDatesInContactView() {
		By spouseDateOfBirth = By.cssSelector("[ng-show=\"contact.spouseDateOfBirth\"]");
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(spouseDateOfBirth));
		WebElement dateField = driver.findElement(spouseDateOfBirth);
		dateComponent.validateDateInDiv("Spouse Date of Birth", dateField, "07/07/1997");
	}

	public void validateAppointmentDatesInContactView() {
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(panelTitle));
		WebElement dateField = driver.findElement(panelTitle);
		dateComponent.validateDateInElement(dateField, "07/07/1997", "span", 0);
	}

	public void validateChildrenDatesInContactView() {
		By dateOfBirth = By.cssSelector("div[ng-show=\"getChildDOB(child.dateOfBirth)\"]");
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(dateOfBirth));
		WebElement dateField = driver.findElement(dateOfBirth);
		dateComponent.validateDateInElement(dateField, "08/08/1998", "span", 1);
	}

	public void validateDatesInContactForm() {
		WebElement dateField = driver.findElement(primaryDOB);
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(primaryDOB));
		dateComponent.validateDate("Date of Birth", dateField, "05/05/1995");
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(primaryLastContact));
		dateField = driver.findElement(primaryLastContact);
		dateComponent.validateDate("Last Contact date", dateField, "06/06/1996");
	}

	public void validateSpouseDatesInContactForm() {
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(spouseDOB));
		WebElement dateField = driver.findElement(spouseDOB);
		dateComponent.validateDate("Spouse Date of Birth", dateField, "07/07/1997");
	}

	public void validateChildrenDatesInContactForm() {
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(childDOB));
		WebElement dateField = driver.findElement(childDOB);
		dateComponent.validateDate("Child Date of Birth", dateField, "08/08/1998");
	}

	public void validateAppointmentDatesInContactForm() {
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(apptDate));
		WebElement dateField = driver.findElement(apptDate);
		dateComponent.validateDate("Appointment Date", dateField, "09/09/2001");
	}

	public void saveForm() {
		sleep(1000);
		hideKeyboard();
		WAIT_10.until(ExpectedConditions.elementToBeClickable(saveButton));
		WebElement button = driver.findElement(saveButton);
		button.click();
		sleep(4000);
	}

	public void saveForm2() {
		WAIT_10.until(ExpectedConditions.elementToBeClickable(saveButton2));
		WebElement button = driver.findElement(saveButton2);
		button.click();
		sleep(4000);
	}

	public void validateDateOfBirthInContactForm() {
		WebElement saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
		WebElement dateField = driver.findElement(primaryDOB);
//		WebElement errorLabel = dateField.findElement(By.xpath("following-sibling::*[1]"));		
		dateComponent.checkInvalid(dateField, saveButton, null);
	}

	public void validateSpouceDateOfBirthInContactForm() {
		WebElement saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
		WebElement dateField = driver.findElement(spouseDOB);
//		WebElement errorLabel = dateField.findElement(By.xpath("following-sibling::*[1]"));		
		dateComponent.checkInvalid(dateField, saveButton, null);
	}

	public void validateLastContactInContactForm() {
		WebElement saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
		WebElement dateField = driver.findElement(primaryLastContact);
//		WebElement errorLabel = dateField.findElement(By.xpath("following-sibling::*[1]"));		
		dateComponent.checkInvalid(dateField, saveButton, null);
	}

	public void addRequiredContactFormValues (String userName) {
		By firstName = By.name("firstName");
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(firstName));
		WebElement element = driver.findElement(firstName);
		element.sendKeys(userName);
	}

	public void addRequiredSpouseContactFormValues (String userName) {
		By spouseFirstName = By.name("spouseFirstName");
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(spouseFirstName));
		driver.findElement(spouseFirstName).sendKeys(userName);
	}

	public void addRequiredChildrenContactFormValues (String userName) {
		By firstName = By.name("firstName");
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(firstName));
		driver.findElement(firstName).sendKeys(userName);
	}

	public void addRequiredAppointmentContactFormValues () {
		By apptType = By.name("apptType");
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(apptType));
		driver.findElement(apptType).sendKeys("Business Overview");
	}

	public void loadAppointmentForm() {
		By appLink = By.cssSelector("#appointments-section > div.row.header-row > div.col-xs-2.right.add-links > a");
		WAIT_10.until(ExpectedConditions.elementToBeClickable(appLink));
		driver.findElement(appLink).click();
		sleep(5000);
	}
	
	public void goToEditAppointment() {
		By editLink = By.cssSelector("[ng-click=\"goToEditAppt(appt)\"]");
		WAIT_10.until(ExpectedConditions.elementToBeClickable(editLink));
		driver.findElement(editLink).click();
		
	}
	
	public void checkInvalidDatesInAppointmentForm() {
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(apptDate));
		WebElement dateField = driver.findElement(apptDate);
		
		WAIT_10.until(ExpectedConditions.elementToBeClickable(saveButton));
		WebElement button = driver.findElement(saveButton);
		
		// Click to initiate field validation
		button.click();			
		//Find the error message, assert is is not hidden
		WebElement error_date = driver.findElement(By.xpath("//*[@id=\"cm-appts-view\"]/form/div/div/div[1]/div[2]/div[1]/div[2]/span[3]"));
		Assert.assertTrue(error_date.isDisplayed());
		Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_REQUIRED, error_date.getText());
		
		sleep(2000);			
	
		// Populate with invalid date and make sure
		// a) The validation error is shown
		dateField.sendKeys(dateString_invalid_format);
		button.click();
		
		if(driver instanceof FirefoxDriver){
			// Invalid format clears up on FireFox, so the error message is still 'Date required'
			error_date = driver.findElement(By.xpath("//*[@id=\"cm-appts-view\"]/form/div/div/div[1]/div[2]/div[1]/div[2]/span[3]"));
			Assert.assertTrue(error_date.isDisplayed());
			Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_REQUIRED, error_date.getText());
		}else if(driver instanceof ChromeDriver){
			// On Chrome the invalid date is being converted to a valid date
			error_date = driver.findElement(By.xpath("//*[@id=\"cm-appts-view\"]/form/div/div/div[1]/div[2]/div[1]/div[2]/span[1]"));
			Assert.assertTrue(error_date.isDisplayed());
			Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_RANGE, error_date.getText());
		}
		
		sleep(2000);	
		
		dateField.sendKeys(dateString_invalid_range);
		button.click();
		sleep(6000);	
		error_date = driver.findElement(By.xpath("//*[@id=\"cm-appts-view\"]/form/div/div/div[1]/div[2]/div[1]/div[2]/span[2]"));
		Assert.assertTrue(error_date.isDisplayed());
		Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_RANGE, error_date.getText());
	}
	
	public void clickOnSaveButtonInContactForm() {
		WebElement saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
	    WAIT_60.until(ExpectedConditions.elementToBeClickable(saveButton));
	}

	public void goToContactProfile() {
		sleep(2000);
		WAIT_10.until(ExpectedConditions.elementToBeClickable(viewContact));
		WebElement element = driver.findElement(viewContact);
		element.click();
		sleep(2000);
	}
}
