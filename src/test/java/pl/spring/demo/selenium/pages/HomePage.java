package pl.spring.demo.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pl.spring.demo.selenium.AbstractPageObject;

public class HomePage extends AbstractPageObject{
	@FindBy(linkText="Book List")
	private WebElement bookList;
	@FindBy(linkText="Author List")
	private WebElement authorList;
	@FindBy(linkText="Dialog A")
	private WebElement dialogA;
	@FindBy(linkText="Dialog B")
	private WebElement dialogB;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver.get("http://localhost:9721/workshop");
	}
	
	public BookListPage clickBookList() {
		bookList.click();
		return PageFactory.initElements(driver, BookListPage.class);
	}

	public AuthorListPage clickAuthorList() {
		authorList.click();
		return PageFactory.initElements(driver, AuthorListPage.class);
	}
	
	public DialogAPage clickDialogA() {
		dialogA.click();
		return PageFactory.initElements(driver, DialogAPage.class);
	}
	
	public DialogBPage clickDialogB() {
		dialogB.click();
		return PageFactory.initElements(driver, DialogBPage.class);
	}
	
}
