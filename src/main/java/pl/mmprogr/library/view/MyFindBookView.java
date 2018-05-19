package pl.mmprogr.library.view;

import org.springframework.stereotype.Component;
import pl.mmprogr.library.view.input.InputView;

@Component
public class MyFindBookView extends FindBookView {
	public MyFindBookView(InputView inputView) {
		super(inputView);
	}

	@Override
	public String takeDataToFindByTitle() {
		System.out.println("You chose 'find books by title' option");
		System.out.println("Type title, which books you want to find: ");
		return inputView.getDataFromUser();
	}

	@Override
	public String takeDataToFindByAuthor() {
		System.out.println("You chose 'find books by author' option");
		System.out.println("Type author, whose books you want to find: ");
		return inputView.getDataFromUser();
	}

	@Override
	public String takeDataToFindByIsbnNumber() {
		System.out.println("You chose 'find books by ISBN number' option");
		System.out.println("Type ISBN number, which books you want to find: ");
		return inputView.getDataFromUser();
	}

	@Override
	public String takeDataToFindByWeeksThatHasntBeenLent() {
		System.out.println("You chose 'find books that have't been lent for weeks' option");
		System.out.println("Type number of weeks the book hasn't been lent for: ");
		return inputView.getDataFromUser();
	}
}
