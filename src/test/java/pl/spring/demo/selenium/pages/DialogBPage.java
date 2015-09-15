package pl.spring.demo.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pl.spring.demo.selenium.AbstractPageObject;

public class DialogBPage extends AbstractPageObject{
	@FindBy(xpath="//h2[contains(text(),'Hello')]")
	private WebElement text;

	public DialogBPage(WebDriver driver) {
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
	
	public String getText() {
		return text.getText();
	}

}
