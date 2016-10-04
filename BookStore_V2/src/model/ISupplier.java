package model;

public interface ISupplier extends java.io.Serializable {

	String getName();

	boolean isTheSameAs(ISupplier supplier);

}
