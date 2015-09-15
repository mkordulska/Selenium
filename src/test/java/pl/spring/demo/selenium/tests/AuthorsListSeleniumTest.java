package pl.spring.demo.selenium.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.spring.demo.selenium.AbstractSelenium;
import pl.spring.demo.selenium.pages.AuthorListPage;

public class AuthorsListSeleniumTest extends AbstractSelenium{
	
	AuthorListPage authorListPage;
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
		authorListPage = openLibrary().clickAuthorList();
	}
	
	@Test
	public void testShouldRedirect() {
		assertFalse(authorListPage.hasError());
		assertEquals("http://localhost:9721/workshop/#/authors/author-list", authorListPage.getCurrentURL());
	}

	@Test
	public void testShouldStartWithAllAuthors() {
		assertNotEquals(0, authorListPage.getAuthorsRows());
	}

	@Test
	public void testShouldFindAllAuthorsIfInputEmpty() {
		authorListPage.setAuthor("");
		assertNotEquals(0, authorListPage.getAuthorsRows());
	}
	
	@Test
	public void testShouldCheckIfFindAuthorsByFirstName() {
		int tableSizeBefore = authorListPage.getAuthorsRows();
		int tableSizeAfter = authorListPage.selectSearchCriteria("Imię").setAuthor("JAN").getAuthorsRows();
		assertNotEquals(tableSizeAfter, tableSizeBefore);
		assertTrue(tableSizeAfter<tableSizeBefore);
	}
	
	@Test
	public void testShouldCheckIfFindAuthorsByLastName() {
		int tableSizeBefore = authorListPage.getAuthorsRows();
		int tableSizeAfter = authorListPage.selectSearchCriteria("Nazwisko").setAuthor("JAN").getAuthorsRows();
		assertNotEquals(tableSizeAfter, tableSizeBefore);
		assertTrue(tableSizeAfter<tableSizeBefore);
	}
	
	@Test
	public void testShouldCheckIfFindAuthorsByFirstOrLastName() {
		int tableSizeBefore = authorListPage.getAuthorsRows();
		int tableSizeAfter = authorListPage.selectSearchCriteria("Imię i nazwisko").setAuthor("JAN").getAuthorsRows();
		assertNotEquals(tableSizeAfter, tableSizeBefore);
		assertTrue(tableSizeAfter<tableSizeBefore);
	}
	
	@Test
	public void testShouldCheckIfFindNoAuthorsForInvalidInput() {
		int tableSizeAfter = authorListPage.selectSearchCriteria("Imię i nazwisko").setAuthor("123").getAuthorsRows();
		assertEquals(0, tableSizeAfter);
	}
	
}
