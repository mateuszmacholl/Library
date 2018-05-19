package pl.mmprogr.library.view.interfaces;

import pl.mmprogr.library.model.book.Book;

public interface GetBookDataFromUser {
	Book getBookData();
	String getTitleDataFromUser();
	String getAuthorDataFromUser();
	String getIsbnNumberDataFromUser();
}
