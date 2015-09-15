package pl.spring.demo.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pl.spring.demo.selenium.AbstractPageObject;

public class AddAuthorModalPage extends AbstractPageObject{
	
	@FindBy(name="firstName")
	private WebElement firstName;
	@FindBy(name="lastName")
	private WebElement lastName;
	@FindBy(css="button.btn.btn-success")
	private WebElement submit;
	@FindBy(css="button.btn.btn-warning")
	private WebElement cancel;

	public AddAuthorModalPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean isElementPresented(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public AddAuthorModalPage setFirstName(String in) {
		firstName.sendKeys(in);
		return this;
	}
	
	public AddAuthorModalPage setLastName(String in) {
		lastName.sendKeys(in);
		return this;
	}

	public AddBookPage clickSubmit() {
		submit.click();
		return PageFactory.initElements(driver, AddBookPage.class);
	}
	
	public AddBookPage clickCancel() {
		cancel.click();
		return PageFactory.initElements(driver, AddBookPage.class);
	}

}
