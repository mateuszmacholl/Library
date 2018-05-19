package pl.mmprogr.library.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mmprogr.library.model.book.Book;
import pl.mmprogr.library.model.user.User;
import pl.mmprogr.library.repo.book.BookRepo;
import pl.mmprogr.library.service.book.interfaces.BookService;

import java.util.List;

@Service
public class MyBookService implements BookService {
	private final BookRepo bookRepo;

	@Autowired
	public MyBookService(BookRepo bookRepo) {
		this.bookRepo = bookRepo;
	}

	@Override
	public void setBooks(List<Book> books) {
		bookRepo.setBooks(books);
	}

	@Override
	public List<Book> findAllBooks() {
		return bookRepo.findAllBooks();
	}

	@Override
	public void add(Book book) {
		bookRepo.add(book);
	}

	@Override
	public void remove(Book book) {
		bookRepo.remove(book);
	}

	@Override
	public List<Book> findByAuthor(String author) {
		return bookRepo.findByAuthor(author);
	}

	@Override
	public List<Book> findByTitle(String title) {
		return bookRepo.findByTitle(title);
	}

	@Override
	public List<Book> findByIsbnNumber(String IsbnNumber) {
		return bookRepo.findByIsbnNumber(IsbnNumber);
	}

	@Override
	public List<Book> findBooksThatHaventBeenLentFor(int w) {
		return bookRepo.findBooksThatHaventBeenLentFor(w);
	}

	@Override
	public List<User> getBooksWithLender() {
		return bookRepo.getBooksWithLender();
	}

	@Override
	public Book findBookWithoutLenderFrom(List<Book> books) {
		return bookRepo.findBookWithoutLenderFrom(books);
	}

	@Override
	public void updateBook(Book oldBook, Book newBook) {
		bookRepo.updateBook(oldBook, newBook);
	}
}
