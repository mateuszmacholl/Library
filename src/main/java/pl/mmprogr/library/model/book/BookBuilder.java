package pl.mmprogr.library.model.book;

import pl.mmprogr.library.model.user.User;

import java.time.LocalDate;

public class BookBuilder {
	String title;
	String author;
	String IsbnNumber;
	LocalDate dateOfLastRental;
	User lender;

	public BookBuilder withTitle(String title) {
		this.title = title;
		return this;
	}

	public BookBuilder withAuthor(String author) {
		this.author = author;
		return this;
	}

	public BookBuilder withIsbnNumber(String isbnNumber) {
		IsbnNumber = isbnNumber;
		return this;
	}

	public BookBuilder withDateOfLastRental(LocalDate dateOfLastRental) {
		this.dateOfLastRental = dateOfLastRental;
		return this;
	}

	public BookBuilder withLender(User lender) {
		this.lender = lender;
		return this;
	}

	public Book build() {
		return new Book(this);
	}
}
