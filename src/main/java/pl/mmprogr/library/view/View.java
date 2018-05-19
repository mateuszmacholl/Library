package pl.mmprogr.library.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.mmprogr.library.view.input.InputView;

@Component
public abstract class View {
	final InputView inputView;

	@Autowired
	public View(InputView inputView) {
		this.inputView = inputView;
	}

	public abstract void showError();

	public abstract void showSuccess();
}
