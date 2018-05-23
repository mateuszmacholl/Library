package pl.mmprogr.library.controller.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.mmprogr.library.model.book.Book;
import pl.mmprogr.library.service.book.interfaces.BookService;
import pl.mmprogr.library.service.book.LoadBooksFromFileService;
import pl.mmprogr.library.view.TypePathToBookFileView;

import java.util.List;

@Controller
public class BooksFileFindingController {
	private final TypePathToBookFileView typePathToBookFileView;
	private final LoadBooksFromFileService loadBooksFromFileService;
	private final BookService bookService;

	@Autowired
	public BooksFileFindingController(TypePathToBookFileView typePathToBookFileView, LoadBooksFromFileService loadBooksFromFileService, BookService bookService) {
		this.typePathToBookFileView = typePathToBookFileView;
		this.loadBooksFromFileService = loadBooksFromFileService;
		this.bookService = bookService;
	}

	public String loadBooksDataFromFile() {
		boolean isPathCorrect = false;
		String pathToFile = "";
		while (!isPathCorrect) {
			pathToFile = typePathToBookFileView.getOneLineData();
			isPathCorrect = loadBooks(pathToFile);
		}
		return pathToFile;
	}

	private boolean loadBooks(String pathToFile) {
		List<Book> books = loadBooksFromFileService.load(pathToFile);
		// TODO Zamiast uzależniać logikę programu od nulla, lepiej byłoby napisać walidator który wcześniej upewni się że dany plik istnieje
		if (books != null) {
			bookService.setBooks(books);
			typePathToBookFileView.show(books);
			return true;
		} else {
			typePathToBookFileView.showError();
			return false;
		}
	}
}
