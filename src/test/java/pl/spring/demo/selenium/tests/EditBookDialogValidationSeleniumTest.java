package pl.spring.demo.selenium.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import pl.spring.demo.selenium.AbstractSelenium;
import pl.spring.demo.selenium.pages.EditBookModalPage;

public class EditBookDialogValidationSeleniumTest extends AbstractSelenium{

	private EditBookModalPage editBookModalPage;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		editBookModalPage = openLibrary().clickBookList().clickFindBook().clickEditBook(1);
	}
	
	@Test
	public void testShouldCheckIfTitleIsRequired() {
		editBookModalPage.clickSubmit();
		assertTrue(editBookModalPage.isElementPresented(By.xpath("//p[contains(.,'Podaj')]")));
	}
	
}
