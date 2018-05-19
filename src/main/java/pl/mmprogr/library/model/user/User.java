package pl.mmprogr.library.model.user;

import java.util.Objects;

public class User {
	private final String firstname;
	private final String lastname;

	public User(UserBuilder userBuilder) {
		this.firstname = userBuilder.firstname;
		this.lastname = userBuilder.lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	@Override
	public String toString() {
		return "firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(firstname, user.firstname) &&
				Objects.equals(lastname, user.lastname);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstname, lastname);
	}
}
