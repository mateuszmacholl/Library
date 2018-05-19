package pl.mmprogr.library.model.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.mmprogr.library.model.user.User;
import pl.mmprogr.library.model.user.UserBuilder;

import java.time.LocalDate;

public class Book {
	private final String title;
	private final String author;
	private final String isbnNumber;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private final LocalDate dateOfLastRental;
	private final User lender;

	public Book(BookBuilder bookBuilder) {
		this.title = bookBuilder.title;
		this.author = bookBuilder.author;
		this.isbnNumber = bookBuilder.IsbnNumber;
		this.dateOfLastRental = bookBuilder.dateOfLastRental;
		this.lender = bookBuilder.lender;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getIsbnNumber() {
		return isbnNumber;
	}

	public LocalDate getDateOfLastRental() {
		return dateOfLastRental;
	}

	public User getLender() {
		return lender;
	}

	@Override
	public String toString() {
		User lender = getLender();
		if (lender == null) {
			lender = new UserBuilder()
					.withFirstname("none")
					.withLastname("none")
					.build();
		}
		return "title='" + title + '\'' +
				", author='" + author + '\'' +
				", IsbnNumber='" + isbnNumber + '\'' +
				", dateOfLastRental=" + dateOfLastRental +
				", lender{" +
				" firstname=" + lender.getFirstname() +
				", lastname=" + lender.getLastname() +
				"}" + "\n";
	}
}
