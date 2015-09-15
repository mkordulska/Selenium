package pl.spring.demo.selenium.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import pl.spring.demo.selenium.AbstractSelenium;
import pl.spring.demo.selenium.pages.DialogBPage;

public class DialogBSeleniumTest extends AbstractSelenium{
	
	DialogBPage dialogBPage;
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
		dialogBPage = openLibrary().clickDialogB();
	}
	
	@Test
	public void testShouldRedirect() {
		assertFalse(dialogBPage.hasError());
		assertEquals("http://localhost:9721/workshop/#/component-2/dialog-b", dialogBPage.getCurrentURL());
	}
	
	@Test
	public void testShouldCheckIfTextIsPresented() {
		assertFalse(dialogBPage.hasError());
		assertTrue(dialogBPage.isElementPresented(By.tagName("h2")));
		assertEquals("Hello from Dialog B!", dialogBPage.getText());
	}

}
