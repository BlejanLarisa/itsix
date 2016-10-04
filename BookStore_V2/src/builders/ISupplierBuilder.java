package builders;

import java.io.Serializable;

import model.ISupplier;

public interface ISupplierBuilder extends Serializable {

	ISupplier build(String name);
}
