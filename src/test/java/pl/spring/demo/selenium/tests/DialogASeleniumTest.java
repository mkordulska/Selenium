package pl.spring.demo.selenium.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import pl.spring.demo.selenium.AbstractSelenium;
import pl.spring.demo.selenium.pages.DialogAPage;

public class DialogASeleniumTest extends AbstractSelenium {
	
	DialogAPage dialogAPage;
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
		dialogAPage = openLibrary().clickDialogA();
	}
	
	@Test
	public void testShouldRedirect() {
		assertFalse(dialogAPage.hasError());
		assertEquals("http://localhost:9721/workshop/#/component-1/dialog-a", dialogAPage.getCurrentURL());
	}
	
	@Test
	public void testShouldCheckIfPictureIsPresented() {
		assertTrue(dialogAPage.isElementPresented(By.tagName("img")));
	}
}
