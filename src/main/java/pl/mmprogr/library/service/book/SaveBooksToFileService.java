package pl.mmprogr.library.service.book;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mmprogr.library.model.book.Book;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class SaveBooksToFileService {
	private final Gson gson;

	@Autowired
	public SaveBooksToFileService(Gson gson) {
		this.gson = gson;
	}

	public void save(List<Book> books, String pathToFile) {
		try (Writer writer = new FileWriter(pathToFile)) {
			gson.toJson(books, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
