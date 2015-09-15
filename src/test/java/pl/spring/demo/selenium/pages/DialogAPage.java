package pl.spring.demo.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import pl.spring.demo.selenium.AbstractPageObject;

public class DialogAPage extends AbstractPageObject {

	public DialogAPage(WebDriver driver) {
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

}
