package pl.mmprogr.library.controller.book;

import org.springframework.stereotype.Controller;
import pl.mmprogr.library.model.book.Book;
import pl.mmprogr.library.model.book.BookBuilder;
import pl.mmprogr.library.model.user.User;
import pl.mmprogr.library.service.book.ShowUsersWhoseBorrowedBooksService;
import pl.mmprogr.library.service.book.interfaces.BookService;
import pl.mmprogr.library.view.BorrowBookView;
import pl.mmprogr.library.view.FindBookView;
import pl.mmprogr.library.view.UsersWhoBorrowedBooksView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Controller
public class BookLendingController {
	private final UsersWhoBorrowedBooksView usersWhoBorrowedBooksView;
	private final BorrowBookView borrowBookView;
	private final FindBookView findBookView;
	private final BookController bookController;
	private final BookService bookService;
	private final ShowUsersWhoseBorrowedBooksService showUsersWhoseBorrowedBooksService;

	public BookLendingController(UsersWhoBorrowedBooksView usersWhoBorrowedBooksView,
	                             BorrowBookView borrowBookView,
	                             FindBookView findBookView,
	                             BookController bookController,
	                             BookService bookService,
	                             ShowUsersWhoseBorrowedBooksService showUsersWhoseBorrowedBooksService) {
		this.usersWhoBorrowedBooksView = usersWhoBorrowedBooksView;
		this.borrowBookView = borrowBookView;
		this.findBookView = findBookView;
		this.bookController = bookController;
		this.bookService = bookService;
		this.showUsersWhoseBorrowedBooksService = showUsersWhoseBorrowedBooksService;
	}

	public void borrowBook() {
		List<Book> potentiallyBooksToLend = getPotentiallyBooksToLend();

		if (potentiallyBooksToLend.isEmpty()) {
			findBookView.showError();
		} else {
			Book book = bookService.findBookWithoutLenderFrom(potentiallyBooksToLend);
			if (book != null) {
				User user = borrowBookView.getUserData();

				Book borrowedBook = new BookBuilder()
						.withAuthor(book.getAuthor())
						.withIsbnNumber(book.getIsbnNumber())
						.withTitle(book.getTitle())
						.withDateOfLastRental(LocalDate.now())
						.withLender(user)
						.build();

				bookService.updateBook(book, borrowedBook);
				bookController.updateFile();
				borrowBookView.showSuccess();
				return;
			}
		}
		borrowBookView.showError();
	}

	public void showUsersWhoseBorrowedBooks() {
		HashMap<String, Integer> report = showUsersWhoseBorrowedBooksService.getUsersWhoBorrowedBooksService();
		if (report != null) {
			usersWhoBorrowedBooksView.showSuccess();
			usersWhoBorrowedBooksView.showReport(report);
		} else {
			usersWhoBorrowedBooksView.showError();
		}
	}

	private List<Book> getPotentiallyBooksToLend() {
		return bookService.findByTitle(borrowBookView.getOneLineData());
	}
}
