package view;

import javax.swing.JPanel;

import model.IBill;
import model.IPayment;
import model.IPricing;
import model.ISupplier;

public interface IBillsPanel extends java.io.Serializable {

	void setSeeSelectedBillPanel(JPanel seeSelectedBillPanel);

	void setAddNewBillPanel(JPanel addNewBillPanel);

	void setBillsTab(JPanel billsTab);

	void updateBillsTable();

	ISupplier getSelectedSupplier();

	IPayment getSelectedPayment();

	IPricing getSelectedPricing();

	int getSelectedRowFromBillPanel();

	IBill getBill();

}