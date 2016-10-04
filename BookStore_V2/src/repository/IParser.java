package repository;

import java.io.Serializable;

public interface IParser extends Serializable {

	IBillsRepository parse();
}
