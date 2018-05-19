package pl.mmprogr.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.mmprogr.library.controller.book.BookController;
import pl.mmprogr.library.controller.book.BookLendingController;
import pl.mmprogr.library.controller.book.BooksFileFindingController;
import pl.mmprogr.library.view.input.InputView;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {
	private final InputView inputView;
	private final BookController bookController;
	private final BookLendingController bookLendingController;
	private final BooksFileFindingController booksFileFindingController;

	@Autowired
	public LibraryApplication(InputView inputView,
	                          BookController bookController,
	                          BookLendingController bookLendingController,
	                          BooksFileFindingController booksFileFindingController) {
		this.inputView = inputView;
		this.bookController = bookController;
		this.bookLendingController = bookLendingController;
		this.booksFileFindingController = booksFileFindingController;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(LibraryApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) {
		System.out.println("----------------------------------------------");
		bookController.setPathToFile(booksFileFindingController.loadBooksDataFromFile());
		while (true) {
			printMenu();
			String line = inputView.getDataFromUser();
			switch (line) {
				case "0": {
					bookController.showAllBooks();
				}
				break;
				case "1": {
					bookController.addBookToLibrary();
				}
				break;
				case "2": {
					bookController.removeBookFromLibrary();
				}
				break;
				case "3":
				case "4":
				case "5":
				case "6": {
					bookController.findBooksBy(line);
				}
				break;
				case "7": {
					bookLendingController.borrowBook();
				}
				break;
				case "8": {
					bookLendingController.showUsersWhoseBorrowedBooks();
				}
				break;
				case "q": {
					return;
				}
			}
		}
	}

	private void printMenu() {
		System.out.println("\n Choose action: ");
		System.out.println("0: show all books ");
		System.out.println("1: add a book ");
		System.out.println("2: remove a book ");
		System.out.println("3: find books by author ");
		System.out.println("4: find books by title ");
		System.out.println("5: find books by Isbn number ");
		System.out.println("6: find books that haven't been lent for x weeks");
		System.out.println("7: borrow a book");
		System.out.println("8: show users, whose borrowed books");
		System.out.println("q: exit");
	}
}
