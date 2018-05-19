package pl.mmprogr.library.validator;

import org.springframework.stereotype.Service;
import pl.mmprogr.library.service.book.interfaces.BookService;

@Service
public class ValidatorService {
	private final BookService bookService;

	public ValidatorService(BookService bookService) {
		this.bookService = bookService;
	}

	public boolean isNumberInText(String text) {
		return text.chars().anyMatch(Character::isDigit);
	}

	public boolean isNotNumberInText(String text) {
		return text.chars().noneMatch(Character::isDigit);
	}

	public boolean isNotLetterInText(String text) {
		return text.chars().noneMatch(Character::isLetter);
	}

	public boolean hasLength(String text, int length) {
		return text.length() == length;
	}

	public boolean isIsbnNumberAlreadyTyped(String isbnNumber) {
		return bookService.findAllBooks().stream().noneMatch(book -> book.getIsbnNumber().equals(isbnNumber));
	}
}
