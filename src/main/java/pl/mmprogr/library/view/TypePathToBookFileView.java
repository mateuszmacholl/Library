package pl.mmprogr.library.view;

import org.springframework.stereotype.Component;
import pl.mmprogr.library.model.book.Book;
import pl.mmprogr.library.view.input.InputView;
import pl.mmprogr.library.view.interfaces.ShowData;
import pl.mmprogr.library.view.interfaces.GetOneLineDataFromUser;

import java.util.Arrays;
import java.util.List;

@Component
public class TypePathToBookFileView extends View implements GetOneLineDataFromUser, ShowData {
	public TypePathToBookFileView(InputView inputView) {
		super(inputView);
	}

	@Override
	public void showError() {
		System.out.format("Wrong path to file! %n");
	}

	@Override
	public void showSuccess() {
		System.out.format("Path is correct");
	}

	@Override
	public String getOneLineData() {
		System.out.println("Type path to file with books: ");
		return inputView.getDataFromUser();
	}

	@Override
	public void show(List<Book> books) {
		System.out.format("List of books in library: %n %s %n", Arrays.toString(new List[]{books}));
	}
}
