package view;

import java.util.List;

import javax.swing.JPanel;

import controller.IBookStoreController;
import model.IBook;
import model.IDate;
import model.IPricing;
import model.ISupplier;

public interface IAddNewBillPanel extends java.io.Serializable {

	void setBillsPanel(JPanel billsPanel);

	void setBillsTab(JPanel billsTab);

	void setBookStoreControler(IBookStoreController bookStoreControler);

	List<IBook> getBillBooksForNewBill();

	ISupplier getSupplier();

	IPricing getPricingForNewBill();

	Double getExpirationDate();

	void reset();

	String getBillCode();

	IDate getBuyDate();
}