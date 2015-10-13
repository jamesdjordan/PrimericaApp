package com.ps.primerica.pages;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
        List<WebElement> items = driver.findElements(PERSON_LIST_ITEM);
        Assert.assertTrue("Person list should not be empty", CollectionUtils.isNotEmpty(items));
	}
	
	public void fillRequiredFieldsInContactForm() {
		addRequiredContactFormValues("userName");
	}

	public void fillRequiredFieldsInSpouseContactForm() {
		addRequiredSpouseContactFormValues("userName");
	}

	public void fillRequiredFieldsInChildrenContactForm() {
		addRequiredChildrenContactFormValues("userName");
	}

	public void fillDateFieldsInContactForm() {
		WebElement dateField = driver.findElement(By.name("primaryDOB"));
		dateComponent.sendDate(dateField, "05/05/1995");
		dateField = driver.findElement(By.name("primaryLastContact"));
		dateComponent.sendDate(dateField, "06/06/1996");
	}

	public void fillFutureDateInLastContactAndValidate() {
		WebElement dateField = driver.findElement(By.name("primaryLastContact"));
		dateComponent.sendDate(dateField, "12/12/2015");
		sleep(2000);
		WebElement errorLabel = driver.findElement(By.xpath("//*[@name='primaryLastContact']/parent::*/following-sibling::*[1]"));
		String error = errorLabel.getAttribute("innerText");
        Assert.assertTrue(error.contains(ERROR_MESSAGE_DATE_RANGE));
	// saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
	//	saveButton.click();
	//	sleep(2000);
//		dateField = driver.findElement(By.name("primaryLastContact"));
//        Assert.assertTrue(dateField.isDisplayed());
 	}

	public void fillDateFieldsInSpouseContactForm() {
		WebElement dateField = driver.findElement(By.name("spouseDOB"));
		dateComponent.sendDate(dateField, "07/07/1997");
	}

	public void fillDateFieldsInChildrenContactForm() {
		WebElement dateField = driver.findElement(By.name("childDOB"));
		dateComponent.sendDate(dateField, "08/08/1998");
	}
	
	public void fillDateFieldsInAppointmentContactForm() {
		WebElement dateField = driver.findElement(By.name("apptDate"));
		dateComponent.sendDate(dateField, "09/09/2001");
	}

	public void validateDatesInContactView() {
		WebElement dateField = driver.findElement(By.cssSelector("[ng-show=\"contact.lastContactDate\"]"));
		dateComponent.validateDateInDiv("Last Contact date", dateField, "06/06/1996");
		dateField = driver.findElement(By.cssSelector("[ng-show=\"contact.dateOfBirth\"]"));
		dateComponent.validateDateInDiv("Date of Birth", dateField, "05/05/1995");
	}

	public void validateSpouseDatesInContactView() {
		WebElement dateField = driver.findElement(By.cssSelector("[ng-show=\"contact.spouseDateOfBirth\"]"));
		dateComponent.validateDateInDiv("Spouse Date of Birth", dateField, "07/07/1997");
	}

	public void validateAppointmentDatesInContactView() {
		WebElement dateField = driver.findElement(By.cssSelector(".panel-title"));
		dateComponent.validateDateInElement(dateField, "07/07/1997", "span", 0);
	}

	public void validateChildrenDatesInContactView() {
		WebElement dateField = driver.findElement(By.cssSelector("[ng-show=\"getChildDOB(child.dateOfBirth)\"]"));
		dateComponent.validateDateInElement(dateField, "08/08/1998", "span", 1);
	}

	public void validateDatesInContactForm() {
		WebElement dateField = driver.findElement(By.name("primaryDOB"));
		dateComponent.validateDate("Date of Birth", dateField, "05/05/1995");
		dateField = driver.findElement(By.name("primaryLastContact"));
		dateComponent.validateDate("Last Contact date", dateField, "06/06/1996");
	}

	public void validateSpouseDatesInContactForm() {
		WebElement dateField = driver.findElement(By.name("spouseDOB"));
		dateComponent.validateDate("Spouse Date of Birth", dateField, "07/07/1997");
	}

	public void validateChildrenDatesInContactForm() {
		WebElement dateField = driver.findElement(By.name("childDOB"));
		dateComponent.validateDate("Child Date of Birth", dateField, "08/08/1998");
	}

	public void validateAppointmentDatesInContactForm() {
		WebElement dateField = driver.findElement(By.name("apptDate"));
		dateComponent.validateDate("Appointment Date", dateField, "09/09/2001");
	}

	public void saveForm() {
		WebElement saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
		saveButton.click();
		sleep(4000);
	}

	public void validateDateOfBirthInContactForm() {
		WebElement saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
		WebElement dateField = driver.findElement(By.name("primaryDOB"));
//		WebElement errorLabel = dateField.findElement(By.xpath("following-sibling::*[1]"));		
		dateComponent.checkInvalid(dateField, saveButton, null);
	}

	public void validateSpouceDateOfBirthInContactForm() {
		WebElement saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
		WebElement dateField = driver.findElement(By.name("spouseDOB"));
//		WebElement errorLabel = dateField.findElement(By.xpath("following-sibling::*[1]"));		
		dateComponent.checkInvalid(dateField, saveButton, null);
	}

	public void validateLastContactInContactForm() {
		WebElement saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
		WebElement dateField = driver.findElement(By.name("primaryLastContact"));
//		WebElement errorLabel = dateField.findElement(By.xpath("following-sibling::*[1]"));		
		dateComponent.checkInvalid(dateField, saveButton, null);
	}

	public void addRequiredContactFormValues (String userName) {
		driver.findElement(By.name("firstName")).sendKeys(userName);
	}

	public void addRequiredSpouseContactFormValues (String userName) {
		driver.findElement(By.name("spouseFirstName")).sendKeys(userName);
	}

	public void addRequiredChildrenContactFormValues (String userName) {
		driver.findElement(By.name("firstName")).sendKeys(userName);
	}

	public void addRequiredAppointmentContactFormValues () {
		driver.findElement(By.name("apptType")).sendKeys("Business Overview");
	}

	public void loadAppointmentForm() {
		driver.findElement(By.cssSelector("#appointments-section > div.row.header-row > div.col-xs-2.right.add-links > a")).click();
		sleep(5000);
	}
	
	public void goToEditAppointment() {
		driver.findElement(By.cssSelector("[ng-click=\"goToEditAppt(appt)\"]")).click();
		
	}
	
	public void checkInvalidDatesInAppointmentForm() {
		WebElement dateField = driver.findElement(By.name("apptDate"));
		WebElement saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
		
		// Click to initiate field validation
		saveButton.click();			
		//Find the error message, assert is is not hidden
		WebElement error_date = driver.findElement(By.xpath("//*[@id=\"cm-appts-view\"]/form/div/div/div[1]/div[2]/div[1]/div[2]/span[3]"));
		Assert.assertTrue(error_date.isDisplayed());
		Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_REQUIRED, error_date.getText());
		
		sleep(2000);			
	
		// Populate with invalid date and make sure
		// a) The validation error is shown
		dateField.sendKeys(dateString_invalid_format);
		saveButton.click();
		
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
		saveButton.click();
		sleep(6000);	
		error_date = driver.findElement(By.xpath("//*[@id=\"cm-appts-view\"]/form/div/div/div[1]/div[2]/div[1]/div[2]/span[2]"));
		Assert.assertTrue(error_date.isDisplayed());
		Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_RANGE, error_date.getText());
	}
	
	public void clickOnSaveButtonInContactForm() {
		WebElement saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
	    WAIT_60.until(ExpectedConditions.elementToBeClickable(saveButton));
	}
}
