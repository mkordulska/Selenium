package pl.spring.demo.selenium.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pl.spring.demo.selenium.AbstractPageObject;

public class EditBookModalPage extends AbstractPageObject{
	@FindBy(name="title")
	private WebElement titleModal;
	@FindBy(css="button.btn.btn-success")
	private WebElement submit;
	@FindBy(css="button.btn.btn-warning")
	private WebElement cancel;
	@FindBy(xpath="//p[contains(text(),'Podaj')]")
	private WebElement alert;

	public EditBookModalPage(WebDriver driver) {
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
	
	public EditBookModalPage setTitleModal(String in) {
		titleModal.sendKeys(in);
		return this;
	}
	
	public BookListPage clickSubmit() {
		submit.click();
		return PageFactory.initElements(driver, BookListPage.class);
	}
	
	public BookListPage clickCancel() {
		cancel.click();
		return PageFactory.initElements(driver, BookListPage.class);
	}

}
