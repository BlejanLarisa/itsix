package view;

import java.util.List;

import javax.swing.JPanel;

import model.IBook;
import model.IDate;

public interface ISeeSelectedBillPanel extends java.io.Serializable {
	void setSelectedBillPayment(String payment);

	void setSelectedBillPricing(String pricing);

	void setSelectedBillSupplier(String supplier);

	void setBillsTab(JPanel billsTab);

	void setBillsPanel(JPanel billsPanel);

	void fillBookTable(List<IBook> books);

	String getSelectedBookTitle();

	String getSelectedBookAuthors();

	String getSelectedBookISBN();

	Integer getSelectedBookNoOfExemplaries();

	Double getSelectedBookPrice();

	Double getSelectedMoneyAmount();

	void setSelectedBillDuePayment(Double duePayment);

	void updateDebt();

	void setSelectedBillBuyDate(IDate buyDate);

	void setSelectedBillCode(String billCode);
}