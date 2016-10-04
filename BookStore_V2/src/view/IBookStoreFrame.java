package view;

import java.util.List;

import javax.swing.JPanel;

import model.IBook;
import model.IDate;
import model.IPayment;
import model.IPricing;
import model.ISubscriber;
import model.ISupplier;

public interface IBookStoreFrame extends ISubscriber, java.io.Serializable {

	ISupplier getSupplier();

	IPricing getPricingForNewBill();

	List<IBook> getBillBooksForNewBill();

	IPayment getSelectedPayment();

	IPricing getSelectedPricing();

	ISupplier getSelectedSupplier();

	void setSelectedBillPayment(String payment);

	void setSelectedBillPricing(String pricing);

	void setSelectedBillSupplier(String supplier);

	void showBill(List<IBook> bookList);

	String getSelectedBookTitle();

	String getSelectedBookAuthors();

	String getSelectedBookISBN();

	Integer getSelectedBookNoOfExemplaries();

	Double getSelectedBookPrice();

	Double getSelectedMoneyAmount();

	void setSelectedBillDuePayment(Double duePayment);

	int getSelectedRowFromBillPanel();

	JPanel getBuyBooksPanel();

	Double getExpirationTerm();

	void reset();

	String getBillCode();

	IDate getBuyDate();

	void setSelectedBillBuyDate(IDate buyDate);

	void setSelectedBillCode(String billCode);

}