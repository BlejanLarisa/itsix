package builders;

import java.io.Serializable;
import java.util.List;

import model.IBook;

public interface IBookBuilder extends Serializable {

	IBook build(String title, List<String> authors, String iSBN, Double price, Integer noOfExemplaries);

	IBook buildEmptyBook();
}
