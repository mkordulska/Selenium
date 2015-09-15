package pl.spring.demo.selenium.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pl.spring.demo.selenium.AbstractPageObject;

public class AddBookPage extends AbstractPageObject{
	@FindBy(xpath="//input")
	private WebElement title;
	@FindBy(xpath="//button[contains(text(),'Zapisz')]")
	private WebElement save;
	@FindBy(xpath="//button[contains(text(),'Dodaj')]")
	private WebElement addAuthor;
	@FindBy(xpath="//table")
	private WebElement table;
	@FindBy(xpath="//button[contains(text(), 'Usuń')]")
	private List<WebElement> deleteButtons;
	@FindBy(xpath="//div[contains(@role,'alert')]")
	private WebElement flashMessage;
	
	public AddBookPage(WebDriver driver) {
		super(driver);
	}
	
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	public boolean isElementPresented(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public BookListPage navigateToBookList() {
		driver.navigate().to("http://localhost:9721/workshop/#/books/book-list");
		return PageFactory.initElements(driver, BookListPage.class);
	}
	
	public AddBookPage setTitle(String in) {
		title.sendKeys(in);
		return this;
	}
	
	public AddBookPage clickSaveBook() {
		save.click();
		return PageFactory.initElements(driver, AddBookPage.class);
	}
	
	public AddAuthorModalPage clickAddAuthor() {
		addAuthor.click();
		return PageFactory.initElements(driver, AddAuthorModalPage.class);
	}
	
	public AddBookPage clickDeleteAuthor(int index) {
		deleteButtons.get(index).click();
		return PageFactory.initElements(driver, AddBookPage.class);
	}
	
	public int getAuthorsRows() {
		return table.findElements(By.tagName("tr")).size()-1;
	}
	
	public String getFirstNameFromTable(int index) {
		WebElement firstName = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[1]"));
		return firstName.getText();
	}
	
	public String getLastNameFromTable(int index) {
		WebElement lastName = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[2]"));
		return lastName.getText();
	}
	
	public String getFlashMessageText(){
		return flashMessage.getText();
	}

}
