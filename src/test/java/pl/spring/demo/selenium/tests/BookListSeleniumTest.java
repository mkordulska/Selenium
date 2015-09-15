package pl.spring.demo.selenium.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import pl.spring.demo.selenium.AbstractSelenium;
import pl.spring.demo.selenium.pages.BookListPage;

public class BookListSeleniumTest extends AbstractSelenium {

	private BookListPage bookListPage;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		bookListPage = openLibrary().clickBookList();
	}

	@Test
	public void testShouldRedirect() {
		assertFalse(bookListPage.hasError());
		assertEquals("http://localhost:9721/workshop/#/books/book-list", bookListPage.getCurrentURL());
	}

	@Test
	public void testShouldFindBooks() {
		bookListPage = bookListPage.clickFindBook();
		assertNotEquals(0, bookListPage.getBooksRows());
	}

	@Test
	public void testShouldNavigateToAddBookPage() {
		bookListPage.clickAddBook();
		assertEquals("http://localhost:9721/workshop/#/books/add-book", bookListPage.getCurrentURL());
	}

	@Test
	public void testShouldDeleteBook() {
		addTestBook();
		int tableSizeBeforeDelete = bookListPage.clickFindBook().getBooksRows();
		bookListPage.clickDeleteBook(tableSizeBeforeDelete - 1);
		int tableSizeAfterDelete = bookListPage.getBooksRows();
		assertEquals(tableSizeBeforeDelete - 1, tableSizeAfterDelete);
		assertTrue(bookListPage.isElementPresented(By.xpath("//div[contains(@role,'alert')]")));
		assertTrue(bookListPage.getFlashMessageText().contains("Książka została usunięta."));
	}

	@Test
	public void testShouldCheckIfEditModalIsPresented() {
		bookListPage.setTitle("").clickFindBook().clickEditBook(0);
		assertTrue(bookListPage.isElementPresented(By.className("modal-dialog")));
	}

	@Test
	public void testShouldCheckIfBookIsEdited() {
		addTestBook();
		String newTitle = "Nowy tytuł";
		bookListPage.clickEditBook(bookListPage.clickFindBook().getBooksRows()-1).setTitleModal(newTitle)
				.clickSubmit();
		assertEquals(newTitle, bookListPage.getTitleFromTable(bookListPage.clickFindBook().getBooksRows()+1));
		assertTrue(bookListPage.isElementPresented(By.xpath("//div[contains(@role,'alert')]")));
		assertTrue(bookListPage.getFlashMessageText().contains("Tytuł został zmieniony."));
		removeTestBook();
	}

	@Test
	public void testShouldCheckIfBookIsSaved() {
		int tableSizeBeforeSave = bookListPage.clickFindBook().getBooksRows();
		addTestBook();
		int tableSizeAfterSave = bookListPage.clickFindBook().getBooksRows();
		assertEquals(tableSizeBeforeSave+1, tableSizeAfterSave);
		assertEquals("title", bookListPage.getTitleFromTable(tableSizeAfterSave+1));
		assertEquals("firstName lastName", bookListPage.getAuthorsFromTable(tableSizeAfterSave+1));
		assertTrue(bookListPage.isElementPresented(By.xpath("//div[contains(@role,'alert')]")));
		assertTrue(bookListPage.getFlashMessageText().contains("Książka została dodana."));
		removeTestBook();
	}

	private void addTestBook() {
		bookListPage.clickAddBook().setTitle("title").clickAddAuthor().setFirstName("firstName").setLastName("lastName")
		.clickSubmit().clickSaveBook().navigateToBookList();
	}
	
	private void removeTestBook() {
		bookListPage.clickDeleteBook(bookListPage.clickFindBook().getBooksRows() - 1);
	}
}
