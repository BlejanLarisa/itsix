package model;

import java.util.Arrays;
import java.util.List;

public class Book implements IBook {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private List<String> authors;
	private String ISBN;
	private Double price;
	private Integer noOfExemplaries;

	public Book(String title, List<String> authors, String iSBN, Double price, Integer noOfExemplaries) {
		super();
		this.title = title;
		this.authors = authors;
		ISBN = iSBN;
		this.price = price;
		this.noOfExemplaries = noOfExemplaries;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public List<String> getAuthors() {
		return authors;
	}

	@Override
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	@Override
	public String getISBN() {
		return ISBN;
	}

	@Override
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	@Override
	public Double getPrice() {
		return price;
	}

	@Override
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public Integer getNoOfExemplaries() {
		return noOfExemplaries;
	}

	@Override
	public void setNoOfExemplaries(Integer noOfExemplaries) {
		this.noOfExemplaries = noOfExemplaries;
	}

	@Override
	public boolean isSameAs(String iSBN) {
		return ISBN.equals(iSBN);
	}

	@Override
	public String getAuthorsAsString() {
		String authorsString = "";

		authorsString = makeStringFromAuthorsList(authorsString);

		authorsString = removeLastComaFrom(authorsString);

		return authorsString;
	}

	private String makeStringFromAuthorsList(String authorsString) {
		for (String author : authors) {
			authorsString += author + ",";
		}
		return authorsString;
	}

	private String removeLastComaFrom(String authorsString) {
		if (authorsString.length() > 0 && authorsString.charAt(authorsString.length() - 1) == ',') {
			authorsString = authorsString.substring(0, authorsString.length() - 1);
		}
		return authorsString;
	}

	@Override
	public void setAuthorsAsString(String authorsString) {
		authors = Arrays.asList(authorsString.split(","));
	}

	@Override
	public boolean thereIsOnlyOneExemplary() {
		return noOfExemplaries == 1;
	}

	@Override
	public void eliminateOneExemplary() {
		noOfExemplaries--;
	}

}
