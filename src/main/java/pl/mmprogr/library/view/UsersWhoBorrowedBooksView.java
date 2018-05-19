package pl.mmprogr.library.view;

import org.springframework.stereotype.Component;
import pl.mmprogr.library.model.book.Book;
import pl.mmprogr.library.view.input.InputView;
import pl.mmprogr.library.view.interfaces.ShowData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UsersWhoBorrowedBooksView extends View implements ShowData {

	public UsersWhoBorrowedBooksView(InputView inputView) {
		super(inputView);
	}

	@Override
	public void showError() {
		System.out.println("There aren't users, who borrowed books");
	}

	@Override
	public void showSuccess() {
		System.out.println("Users, who borrowed books: ");
	}

	@Override
	public void show(List<Book> books) {

	}

	public void showReport(HashMap<String, Integer> report) {
		for (Map.Entry<String, Integer> data : report.entrySet()) {
			System.out.format("User: %s", data.getKey());
			System.out.format(" Books: %s %n", data.getValue());
		}
	}
}
