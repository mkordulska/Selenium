package pl.spring.demo.selenium.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import pl.spring.demo.selenium.AbstractSelenium;
import pl.spring.demo.selenium.pages.AddAuthorModalPage;

public class AddAuthorDialogValidationSeleniumTest extends AbstractSelenium{

	private AddAuthorModalPage addAuthorsModalPage;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		addAuthorsModalPage = openLibrary().clickBookList().clickAddBook().clickAddAuthor();
	}
	
	@Test
	public void shouldCheckIfFirstNameIsRequired() {
		addAuthorsModalPage.setLastName("lastName").clickSubmit();
		assertTrue(addAuthorsModalPage.isElementPresented(By.xpath("//p[contains(.,'Podaj imię!')]")));
	}
	
	@Test
	public void shouldCheckIfLastNameIsRequired() {
		addAuthorsModalPage.setFirstName("firstName").clickSubmit();
		assertTrue(addAuthorsModalPage.isElementPresented(By.xpath("//p[contains(.,'Podaj nazwisko!')]")));
	}
	

}
