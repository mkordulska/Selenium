package pl.spring.demo.selenium.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pl.spring.demo.selenium.AbstractPageObject;

public class BookListPage extends AbstractPageObject{
	@FindBy(xpath="//input")
	private WebElement title;
	@FindBy(xpath="//button[contains(text(),'Szukaj')]")
	private WebElement search;
	@FindBy(xpath="//button[contains(text(),'Dodaj książkę')]")
	private WebElement add;
	@FindBy(xpath="//table")
	private WebElement table;
	@FindBy(xpath="//button[contains(text(),'Usuń')]")
	private List<WebElement> deleteButtons;
	@FindBy(xpath="//button[contains(text(), 'Edytuj')]")
	private List<WebElement> editButtons;
	@FindBy(xpath="//div[contains(text(),'Edytuj')]")
	private WebElement modal;
	@FindBy(xpath="//div[contains(@role,'alert')]")
	private WebElement flashMessage;
	
	public BookListPage(WebDriver driver) {
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
	
	public BookListPage setTitle(String in) {
		title.sendKeys(in);
		return this;
	}
	
	public BookListPage clickFindBook() {
		search.click();
		return PageFactory.initElements(driver, BookListPage.class);
	}
	
	public AddBookPage clickAddBook() {
		add.click();
		return PageFactory.initElements(driver, AddBookPage.class);
	}

	public int getBooksRows() {
		return table.findElements(By.tagName("tr")).size()-1;
	}
	
	public BookListPage clickDeleteBook(int index) {
		deleteButtons.get(index).click();
		return PageFactory.initElements(driver, BookListPage.class);
	}
	
	public EditBookModalPage clickEditBook(int index) {
		editButtons.get(index).click();
		return PageFactory.initElements(driver, EditBookModalPage.class);
	}

	public String getTitleFromTable(int index) {
		WebElement bookTitle = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[1]"));
		return bookTitle.getText();
	}
	
	public String getAuthorsFromTable(int index) {
		WebElement authors = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[2]"));
		return authors.getText();
	}
	
	public String getFlashMessageText(){
		return flashMessage.getText();
	}

}
