package pl.mmprogr.library.view;

import org.springframework.stereotype.Component;
import pl.mmprogr.library.model.book.Book;
import pl.mmprogr.library.view.input.InputView;
import pl.mmprogr.library.view.interfaces.ShowData;

import java.util.List;

@Component
public abstract class FindBookView extends View implements ShowData {
	public FindBookView(InputView inputView) {
		super(inputView);
	}

	@Override
	public void showError() {
		System.out.println("We have no book, that you want to find! ");
	}

	@Override
	public void showSuccess() {
		System.out.println("Books has been found");
	}

	@Override
	public void show(List<Book> books) {
		for (Book book : books) {
			System.out.println(book.toString());
		}
	}

	public abstract String takeDataToFindByTitle();

	public abstract String takeDataToFindByAuthor();

	public abstract String takeDataToFindByIsbnNumber();

	public abstract String takeDataToFindByWeeksThatHasntBeenLent();
}
