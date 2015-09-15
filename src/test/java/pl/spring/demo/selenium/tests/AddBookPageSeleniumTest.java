package pl.spring.demo.selenium.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import pl.spring.demo.selenium.AbstractSelenium;
import pl.spring.demo.selenium.pages.AddBookPage;
import pl.spring.demo.selenium.pages.BookListPage;

public class AddBookPageSeleniumTest extends AbstractSelenium {

	private AddBookPage addBookPage;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		addBookPage = openLibrary().clickBookList().clickAddBook();
	}
	
	@Test
	public void testShouldRedirect() {
		assertFalse(addBookPage.hasError());
		assertEquals("http://localhost:9721/workshop/#/books/add-book", addBookPage.getCurrentURL());
	}

	@Test
	public void shouldCheckIfTitleIsRequired() {
		addBookPage.setTitle("").clickSaveBook();
		assertTrue(addBookPage.isElementPresented(By.xpath("//p[contains(.,'Podaj')]")));
	}

	@Test
	public void shouldCheckIfAuthorsAreRequired() {
		addBookPage.setTitle("title").clickSaveBook();
		BookListPage bookListPage = addBookPage.navigateToBookList();
		assertTrue(bookListPage.isElementPresented(By.xpath("//div[contains(@role,'alert')]")));
		assertTrue(bookListPage.getFlashMessageText().contains("Błędne dane."));
	}

	@Test
	public void shouldCheckIfAuthorsModalIsPresented() {
		addBookPage.clickAddAuthor();
		assertTrue(addBookPage.isElementPresented(By.className("modal-dialog")));
	}

	@Test
	public void shouldCheckIfAuthorsAreAdded() {
		int tableSizeBeforeAdd = addBookPage.getAuthorsRows();
		addBookPage.clickAddAuthor().setFirstName("firstName").setLastName("lastName").clickSubmit();
		int tableSizeAfterAdd = addBookPage.getAuthorsRows();
		assertEquals(tableSizeBeforeAdd + 1, tableSizeAfterAdd);
		assertEquals("firstName", addBookPage.getFirstNameFromTable(tableSizeAfterAdd+1));
		assertEquals("lastName", addBookPage.getLastNameFromTable(tableSizeAfterAdd+1));
	}

	@Test
	public void shouldCheckIfAuthorsAreDeleted() {
		addBookPage.clickAddAuthor().setFirstName("firstName").setLastName("lastName").clickSubmit();
		int tableSizeBeforeDelete = addBookPage.getAuthorsRows();
		addBookPage.clickDeleteAuthor(tableSizeBeforeDelete - 1);
		int tableSizeAfterDelete = addBookPage.getAuthorsRows();
		assertEquals(tableSizeBeforeDelete - 1, tableSizeAfterDelete);
	}

}
