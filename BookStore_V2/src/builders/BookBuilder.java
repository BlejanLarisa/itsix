package builders;

import java.util.List;

import model.Book;
import model.EmptyBook;
import model.IBook;

public class BookBuilder implements IBookBuilder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public IBook build(String title, List<String> authors, String iSBN, Double price, Integer noOfExemplaries) {
		return new Book(title, authors, iSBN, price, noOfExemplaries);
	}

	@Override
	public IBook buildEmptyBook() {
		return new EmptyBook();
	}

}
