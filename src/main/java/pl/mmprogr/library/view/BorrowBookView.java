package pl.mmprogr.library.view;

import org.springframework.stereotype.Component;
import pl.mmprogr.library.model.user.User;
import pl.mmprogr.library.model.user.UserBuilder;
import pl.mmprogr.library.validator.ValidatorService;
import pl.mmprogr.library.view.input.InputView;
import pl.mmprogr.library.view.interfaces.GetOneLineDataFromUser;
import pl.mmprogr.library.view.interfaces.GetUserDataFromUser;

@Component
public class BorrowBookView extends View implements GetOneLineDataFromUser, GetUserDataFromUser {
	private final ValidatorService validatorService;

	public BorrowBookView(InputView inputView, ValidatorService validatorService) {
		super(inputView);
		this.validatorService = validatorService;
	}

	@Override
	public void showError() {
		System.out.println("Oh. There is no available book, which you want to borrow. Everyone has been borrowed");
	}

	@Override
	public void showSuccess() {
		System.out.println("Okay. Book is yours");
	}

	@Override
	public String getOneLineData() {
		System.out.println("You chose 'borrow' option");
		System.out.println("Type title, which book you want to borrow: ");
		return inputView.getDataFromUser();
	}

	@Override
	public User getUserData() {
		System.out.println("Book is available for you ");
		System.out.println("I need your credentials ");
		System.out.println("Type your first name: ");
		String firstname = inputView.getDataFromUser();
		while (validatorService.isNumberInText(firstname)) {
			System.out.println("You can not have numbers in first name");
			System.out.println("Type your first name: ");
			firstname = inputView.getDataFromUser();
		}
		System.out.println("Type your last name: ");
		String lastname = inputView.getDataFromUser();
		while (validatorService.isNumberInText(firstname)) {
			System.out.println("You can not have numbers in last name");
			System.out.println("Type your last name: ");
			firstname = inputView.getDataFromUser();
		}
		return new UserBuilder()
				.withFirstname(firstname)
				.withLastname(lastname)
				.build();
	}
}
