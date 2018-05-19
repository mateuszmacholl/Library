package pl.mmprogr.library.view.input;

import org.springframework.stereotype.Component;
import pl.mmprogr.library.validator.ValidatorService;

import java.util.Scanner;

@Component
public class InputView {
	private final ValidatorService validatorService;
	private final Scanner scanner = new Scanner(System.in);

	public InputView(ValidatorService validatorService) {
		this.validatorService = validatorService;
	}

	public String getDataFromUser() {
		String line = scanner.nextLine();
		while (validatorService.isNotNumberInText(line) && validatorService.isNotLetterInText(line)) {
			System.out.println("Field can't be empty");
			line = scanner.nextLine();
		}
		return line;
	}
}
