package pl.mmprogr.library.view;

import org.springframework.stereotype.Component;
import pl.mmprogr.library.model.book.Book;
import pl.mmprogr.library.model.book.BookBuilder;
import pl.mmprogr.library.validator.ValidatorService;
import pl.mmprogr.library.view.input.InputView;
import pl.mmprogr.library.view.interfaces.GetBookDataFromUser;

@Component
public class AddBookToLibraryView extends View implements GetBookDataFromUser {
	private final ValidatorService validatorService;

	public AddBookToLibraryView(InputView inputView, ValidatorService validatorService) {
		super(inputView);
		this.validatorService = validatorService;
	}

	@Override
	public void showError() {

	}

	@Override
	public void showSuccess() {
		System.out.println("The book has been added successfully");
	}

	@Override
	public Book getBookData() {
		System.out.println("You chose 'add book' option");
		String title = getTitleDataFromUser();
		String author = getAuthorDataFromUser();
		String isbnNumber = getIsbnNumberDataFromUser();

		return new BookBuilder()
				.withTitle(title)
				.withAuthor(author)
				.withIsbnNumber(isbnNumber)
				.withDateOfLastRental(null)
				.withLender(null)
				.build();
	}

	@Override
	public String getTitleDataFromUser(){
		System.out.println("Type title: ");
		return inputView.getDataFromUser();
	}
	@Override
	public String getAuthorDataFromUser(){
		System.out.println("Type author: ");
		String author = inputView.getDataFromUser();
		while (validatorService.isNumberInText(author)) {
			System.out.println("Ops! Author name can not contains numbers and can't be empty");
			System.out.println("Type author name again: ");
			author = inputView.getDataFromUser();
		}
		return author;
	}
	@Override
	public String getIsbnNumberDataFromUser(){
		System.out.println("Type ISBN Number: ");
		String isbnNumber = inputView.getDataFromUser();
		while (!validatorService.isNumberInText(isbnNumber)
				|| !validatorService.hasLength(isbnNumber, 13)
				|| !validatorService.isIsbnNumberAlreadyTyped(isbnNumber)) {
			System.out.println("Ops! isbnNumber must contains only numbers, has 13 numbers and be unique");
			System.out.println("Type isbnNumber name again: ");
			isbnNumber = inputView.getDataFromUser();
		}
		return isbnNumber;
	}
}
