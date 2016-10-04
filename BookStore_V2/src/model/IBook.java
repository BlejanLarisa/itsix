package model;

import java.util.List;

public interface IBook extends java.io.Serializable {

	String getTitle();

	void setTitle(String title);

	List<String> getAuthors();

	void setAuthors(List<String> authors);

	String getISBN();

	void setISBN(String iSBN);

	Double getPrice();

	void setPrice(Double price);

	Integer getNoOfExemplaries();

	void setNoOfExemplaries(Integer noOfExemplaries);

	boolean isSameAs(String iSBN);

	String getAuthorsAsString();

	void setAuthorsAsString(String authorsString);

	boolean thereIsOnlyOneExemplary();

	void eliminateOneExemplary();

}