package pl.mmprogr.library.repo.book;

import org.springframework.stereotype.Repository;
import pl.mmprogr.library.model.book.Book;
import pl.mmprogr.library.model.user.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
// TODO BookRepository -> nie ma sensu skracać nazw
public class BookRepo {
	private List<Book> books;

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<Book> findAllBooks() {
		return books;
	}

	public void add(Book book) {
		books.add(book);
	}

	public void remove(Book book) {
		books.remove(book);
	}

	public List<Book> findByAuthor(String author) {
		return books.stream()
				.filter(b -> b.getAuthor().equals(author))
				.collect(Collectors.toList());
	}

	public List<Book> findByTitle(String title) {
		return books.stream()
				.filter(b -> b.getTitle().equals(title))
				.collect(Collectors.toList());
	}

	public List<Book> findByIsbnNumber(String IsbnNumber) {
		return books.stream()
				.filter(b -> b.getIsbnNumber().equals(IsbnNumber))
				.collect(Collectors.toList());
	}

	public List<Book> findBooksThatHaventBeenLentFor(int w) {
		List<Book> allBooks = findAllBooks();
		List<Book> booksToReturn = new ArrayList<>();
		for (Book book : allBooks) {
			if (book.getLender() == null
					|| ChronoUnit.WEEKS.between(book.getDateOfLastRental(), LocalDate.now()) >= w) {
				booksToReturn.add(book);
			}
		}
		return booksToReturn;
	}

	public List<User> getBooksWithLender() {
		List<User> lenders = new ArrayList<>();
		// TODO: Widzę że używasz streamów dlatego nie ma powodu żebyś nie użył ich też tutaj. To może być dobre ćwiczenie :)
		for (Book book : books) {
			User lender = book.getLender();
			// TODO: zamiast sprawdzać czy lender nie jest nullem spróbuj zwrócić z metody book.getLender() typ Optional<User>. Dzięki temu zawsze będziesz chroniony od niechcianego nulla
			if (lender != null) {
				lenders.add(lender);
			}
		}
		return lenders;
	}

	public Book findBookWithoutLenderFrom(List<Book> books) {
		for (Book book : books) {
			if (book.getLender() == null) {
				return book;
			}
		}
		// TODO Od javy 8 zalecane jest używanie klasy Optional jeżeli zwracana wartość może być nullem
		return null;
	}

	public void updateBook(Book oldBook, Book newBook){
		remove(oldBook);
		add(newBook);
	}
}
