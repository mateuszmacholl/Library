package pl.mmprogr.library.service.book;

import org.springframework.stereotype.Service;
import pl.mmprogr.library.model.book.Book;
import pl.mmprogr.library.model.user.User;
import pl.mmprogr.library.service.book.interfaces.BookService;

import java.util.HashMap;
import java.util.List;

@Service
public class ShowUsersWhoseBorrowedBooksService {
	private final BookService bookService;

	public ShowUsersWhoseBorrowedBooksService(BookService bookService) {
		this.bookService = bookService;
	}

	public HashMap<String, Integer> getUsersWhoBorrowedBooksService() {
		List<Book> books = bookService.findAllBooks();
		List<User> lenders = bookService.getBooksWithLender();
		return prepareReport(books, lenders);
	}

	private HashMap<String, Integer> prepareReport(List<Book> books, List<User> lenders) {
		HashMap<String, Integer> report = new HashMap<>();
		for (User lender : lenders) {
			report.put(lender.toString(), (int) books.stream()
					.filter(b -> b.getLender() != null)
					.filter(b -> b.getLender().equals(lender))
					.count());
		}
		return report;
	}
}
