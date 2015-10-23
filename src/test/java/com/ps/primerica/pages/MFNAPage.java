package com.ps.primerica.pages;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.ps.primerica.components.DateComponent;
import com.ps.primerica.model.Contact;

public class MFNAPage extends BasePage {

	private final String ADD_CONTACT_FORM_URL = "https://test.primericaonline.com/wlpol/apps/services/www/Primerica/desktopbrowser/default/index.html#/fna/detail/addContact/privacy-notice";
	private final String CONTACT_LIST_URL = "https://test.primericaonline.com/wlpol/apps/services/www/Primerica/desktopbrowser/default/index.html#/fna/contacts";

	private DateComponent dateComponent;

	private By CLIENT_LOCATION_SELECT = By.cssSelector("[ng-model=\"selectYourState\"]");
	private By RIGHT_BUTTON = By.cssSelector(".right-pill.clickable.pull-right");
	private By PERSONAL_INFO = By.id("personal-info");
	private By NEXT_PAGE_INDICATOR = By.id("nextPageIndicator");
	private By SEARCH_TEXT = By.id("searchText");
			
	
	private By INCLUDE_SPOUSE = By.cssSelector("[on-click=\"handleSpouseCheck(selected)\"]");
	
	private By CLIENT_FIRST_NAME = By.name("firstName");
	private By CLIENT_LAST_NAME = By.name("lastName");
	private By CLIENT_GENGER = By.cssSelector("[ng-model=\"contact.gender\"]");
	private By CLIENT_DOB = By.name("contactDOB");
	
	private By SPOUSE_FIRST_NAME = By.name("spouseFirstName");
	private By SPOUSE_LAST_NAME = By.name("spouseLastName");
	private By SPOUSE_GENGER = By.cssSelector("[ng-model=\"contact.spouseGender\"]");
	private By SPOUSE_DOB = By.name("spouseDOB");
	
	private By fnaComplete = By.cssSelector(".fna-complete");
	private By onPositiveClicked = By.cssSelector("[ng-click=\"onPositiveClicked()\"]");
	
	private By contactList = By.id("contact-list");

	public MFNAPage(WebDriver driver) {
		super(driver);
		this.dateComponent = new DateComponent(driver);
	}
	
	public void goToAddContactForm() {
		driver.get(ADD_CONTACT_FORM_URL);	
		sleep(4000);

	}

	public void goToContactList() {
		driver.get(CONTACT_LIST_URL);	
		sleep(4000);
	}

	public void goToContactForm(Contact contact) {
		String firstName = contact.getPersonalInfo().getClient().getFirstName();
//		String lastName = contact.getPersonalInfo().getClient().getLastName();
		By div = By.xpath("//div[contains(text(),\"" + firstName + "\")]");
		driver.findElement(div).click();
		sleep(4000);
	}

	public void searchContact(String firstName) {
		WebElement searchText = driver.findElement(SEARCH_TEXT);
		searchText.clear();
		searchText.sendKeys(firstName);
		sleep(4000);
	}

	public void selectClientLocation(Contact mfnaContact) {
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(CLIENT_LOCATION_SELECT));
	//	driver.findElement(CLIENT_LOCATION_SELECT).click();
		Select dropdown = new Select(driver.findElement(CLIENT_LOCATION_SELECT));
		dropdown.selectByVisibleText(mfnaContact.getClientLocation());
	//	By option = By.xpath("//option[contains(text(),\""+ mfnaContact.getClientLocation() + "\")]");
	//	driver.findElement(option).click();
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(RIGHT_BUTTON));
		driver.findElement(RIGHT_BUTTON).click();
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(PERSONAL_INFO));
	}

	public void fillRequiredFieldsInContactForm(Contact mfnaContact) {
		sleep(1000);
		WAIT_10.until(ExpectedConditions.elementToBeClickable(INCLUDE_SPOUSE));
		driver.findElement(INCLUDE_SPOUSE).click();
		
		WebElement clientFirstName = driver.findElement(CLIENT_FIRST_NAME);
		clientFirstName.clear();
		clientFirstName.sendKeys(mfnaContact.getPersonalInfo().getClient().getFirstName());

		WebElement clientLastName = driver.findElement(CLIENT_LAST_NAME);
		clientLastName.clear();
		clientLastName.sendKeys(mfnaContact.getPersonalInfo().getClient().getLastName());

		By clientGengerBy = By.xpath("//label[contains(text(),\""+ mfnaContact.getPersonalInfo().getClient().getGender() + "\")]");
//		WebElement clientGenger = driver.findElement(CLIENT_GENGER);
		driver.findElements(clientGengerBy).get(0).click();
				
		WebElement spouseFirstName = driver.findElement(SPOUSE_FIRST_NAME);
		spouseFirstName.clear();
		spouseFirstName.sendKeys(mfnaContact.getPersonalInfo().getSpouse().getFirstName());
		
		WebElement spouseLastName = driver.findElement(SPOUSE_LAST_NAME);
		spouseLastName.clear();
		spouseLastName.sendKeys(mfnaContact.getPersonalInfo().getSpouse().getLastName());
		
		By spouseGengerBy = By.xpath("//label[contains(text(),\""+ mfnaContact.getPersonalInfo().getClient().getGender() + "\")]");
//		WebElement spouseGenger = driver.findElement(SPOUSE_GENGER);
		driver.findElements(spouseGengerBy).get(1).click();
		
	}

	public void fillDatesInContactForm(Contact mfnaContact) {
		WebElement clientDOB = driver.findElement(CLIENT_DOB);
		dateComponent.sendDate(clientDOB, mfnaContact.getPersonalInfo().getClient().getDateOfBirth());

		WebElement spouseDOB = driver.findElement(SPOUSE_DOB);
		dateComponent.sendDate(spouseDOB, mfnaContact.getPersonalInfo().getSpouse().getDateOfBirth());
	}

	public void validateDatesInContactForm(Contact mfnaContact) {
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(CLIENT_DOB));
		WebElement clientDOB = driver.findElement(CLIENT_DOB);
		dateComponent.validateDate("Date of Birth", clientDOB, mfnaContact.getPersonalInfo().getClient().getDateOfBirth());

		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(SPOUSE_DOB));
		WebElement spouseDOB = driver.findElement(SPOUSE_DOB);
		dateComponent.validateDate("Spouse Date of Birth", spouseDOB, mfnaContact.getPersonalInfo().getSpouse().getDateOfBirth());
	}
	
	public void clickOnNextPageIndicator() {
		WAIT_10.until(ExpectedConditions.elementToBeClickable(NEXT_PAGE_INDICATOR));
		driver.findElement(NEXT_PAGE_INDICATOR).click();
	}
	
	public void removeContactIfExists(Contact contact) {
		String firstName = contact.getPersonalInfo().getClient().getFirstName();
		goToContactList();
		searchContact(firstName);
		By editSpan = By.xpath("//span[contains(text(),\"Edit\")]");
		driver.findElement(editSpan).click();
		sleep(2000);
		WebElement contactListEl = driver.findElement(contactList);
		String contactListText = getTextByElement(contactListEl);
		if (contactListText != null && contactListText.contains(firstName)) {
			List<WebElement> elements = driver.findElements(fnaComplete);
			for (int i = elements.size() - 1; i >= 0; i--) {
				elements.get(i).click();
				WAIT_10.until(ExpectedConditions.elementToBeClickable(onPositiveClicked));
				driver.findElement(onPositiveClicked).click();
				sleep(2000);
			}
		}
	}
}
