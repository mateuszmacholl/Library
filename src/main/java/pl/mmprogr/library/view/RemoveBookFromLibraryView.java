package pl.mmprogr.library.view;

import org.springframework.stereotype.Component;
import pl.mmprogr.library.view.input.InputView;
import pl.mmprogr.library.view.interfaces.GetOneLineDataFromUser;

@Component
public class RemoveBookFromLibraryView extends View implements GetOneLineDataFromUser {
	public RemoveBookFromLibraryView(InputView inputView) {
		super(inputView);
	}

	@Override
	public void showError() {
		System.out.println("We have no book with this title! ");
	}

	public void showBookIsBorrowed() {
		System.out.println("You can't delete book, which is borrowed");
	}

	@Override
	public void showSuccess() {
		System.out.println("Successfully removed book from library! ");
	}

	@Override
	public String getOneLineData() {
		System.out.println("You chose 'remove book' option");
		System.out.println("Type book's title, that you want to delete: ");
		return inputView.getDataFromUser();
	}
}
