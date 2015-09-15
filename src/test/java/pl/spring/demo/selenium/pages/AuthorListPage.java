package pl.spring.demo.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import pl.spring.demo.selenium.AbstractPageObject;

public class AuthorListPage extends AbstractPageObject{
	@FindBy(xpath="//input")
	private WebElement author;
	@FindBy(xpath="//table")
	private WebElement table;
	@FindBy(tagName="select")
	private WebElement selectpicker;

	public AuthorListPage(WebDriver driver) {
		super(driver);
	}
	
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	public AuthorListPage setAuthor(String in) {
		author.sendKeys(in);
		return this;
	}	
	
	public AuthorListPage selectSearchCriteria(String visibleText) {
		new Select(selectpicker).selectByVisibleText(visibleText);
		return this;
	}	
	
	public int getAuthorsRows() {
		return table.findElements(By.tagName("tr")).size()-1;
	}

}
