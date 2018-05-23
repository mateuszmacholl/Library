package pl.mmprogr.library.service.book;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mmprogr.library.model.book.Book;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class LoadBooksFromFileService {
	private final Gson gson;

	@Autowired
	public LoadBooksFromFileService(Gson gson) {
		this.gson = gson;
	}

	public List<Book> load(String pathToFile) {
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(pathToFile));
		} catch (FileNotFoundException e) {
			// TODO Dużo bezpieczniej byłoby zwrócić w tym miejscu pustą listę niż nulla
			return null;
		}

		Type bookType = new TypeToken<List<Book>>() {
		}.getType();
		return gson.fromJson(reader, bookType);
	}
}
