package pl.mmprogr.library.controller.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.mmprogr.library.model.book.Book;
import pl.mmprogr.library.service.book.interfaces.BookService;
import pl.mmprogr.library.service.book.SaveBooksToFileService;
import pl.mmprogr.library.view.AddBookToLibraryView;
import pl.mmprogr.library.view.FindBookView;
import pl.mmprogr.library.view.RemoveBookFromLibraryView;

import java.util.List;

@Controller
public class BookController {
	private final AddBookToLibraryView addBookToLibraryView;
	private final FindBookView findBookView;
	private final RemoveBookFromLibraryView removeBookFromLibraryView;
	private final SaveBooksToFileService saveBooksToFileService;
	private final BookService bookService;
	private String pathToFile;

	@Autowired
	public BookController(AddBookToLibraryView addBookToLibraryView,
	                      FindBookView findBookView,
	                      RemoveBookFromLibraryView removeBookFromLibraryView,
	                      SaveBooksToFileService saveBooksToFileService,
	                      BookService bookService) {
		this.addBookToLibraryView = addBookToLibraryView;
		this.findBookView = findBookView;
		this.removeBookFromLibraryView = removeBookFromLibraryView;
		this.saveBooksToFileService = saveBooksToFileService;
		this.bookService = bookService;
	}

	public void showAllBooks() {
		findBookView.show(bookService.findAllBooks());
	}

	void updateFile() {
		saveBooksToFileService.save(bookService.findAllBooks(), pathToFile);
	}

	public void addBookToLibrary() {
		Book book = addBookToLibraryView.getBookData();
		bookService.add(book);
		updateFile();
		addBookToLibraryView.showSuccess();
	}

	public void removeBookFromLibrary() {
		String titleToRemove = removeBookFromLibraryView.getOneLineData();
		List<Book> bookToRemove = bookService.findByTitle(titleToRemove);
		if (!bookToRemove.isEmpty()) {
			if (bookToRemove.stream().anyMatch(book -> book.getLender() == null)) {
				bookService.remove(bookToRemove.get(0));
				removeBookFromLibraryView.showSuccess();
				updateFile();
			} else {
				removeBookFromLibraryView.showBookIsBorrowed();
			}
		} else {
			removeBookFromLibraryView.showError();
		}
	}

	public void findBooksBy(String typeOfFind) {
		List<Book> books = null;
		switch (typeOfFind) {
			case "3": {
				books = bookService.findByAuthor(findBookView.takeDataToFindByAuthor());
			}
			break;
			case "4": {
				books = bookService.findByTitle(findBookView.takeDataToFindByTitle());
			}
			break;
			case "5": {
				books = bookService.findByIsbnNumber(findBookView.takeDataToFindByIsbnNumber());
			}
			break;
			case "6": {
				books = bookService.findBooksThatHaventBeenLentFor(Integer.parseInt(findBookView.takeDataToFindByWeeksThatHasntBeenLent()));
			}
			break;
		}
		if (books != null && !books.isEmpty()) {
			findBookView.show(books);
		} else {
			findBookView.showError();
		}
	}

	public void setPathToFile(String pathToFile) {
		this.pathToFile = pathToFile;
	}
}
