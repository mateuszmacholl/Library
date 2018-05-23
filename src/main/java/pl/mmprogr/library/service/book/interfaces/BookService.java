package pl.mmprogr.library.service.book.interfaces;

import org.springframework.stereotype.Service;
import pl.mmprogr.library.model.book.Book;
import pl.mmprogr.library.model.user.User;

import java.util.List;

@Service
// TODO Jest taka zasada żeby nie tworzyć interfejsów jeżeli posiadać on będzie tylko jedną implementację. Unikamy tworzenia rzeczy na zapas  (trzeba dążyć do jak najmniejszej ilości kodu)
// TODO Jeżeli w przyszłości pojawiłaby się potrzeba na inną implementację BookService to wówczas możnaby się zastanowić nad wydzieleniem wspólnego interfejsu. Nie wcześniej
public interface BookService {
	void setBooks(List<Book> books);

	List<Book> findAllBooks();

	void add(Book book);

	void remove(Book book);

	List<Book> findByAuthor(String author);

	List<Book> findByTitle(String title);

	List<Book> findByIsbnNumber(String IsbnNumber);

	List<Book> findBooksThatHaventBeenLentFor(int w);

	List<User> getBooksWithLender();

	Book findBookWithoutLenderFrom(List<Book> books);

	void updateBook(Book oldBook, Book newBook);
}
