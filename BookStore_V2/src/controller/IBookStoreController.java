package controller;

import java.io.Serializable;

import model.BillsTableModel;
import model.IBill;
import model.IBook;
import view.IBookStoreFrame;

public interface IBookStoreController extends Serializable {

	void setBookStoreFrame(IBookStoreFrame bookStoreFrame);

	void addBookToPaidBooks(String iSBN);

	void addBillToRepository();

	IBill getBillFromTable(BillsTableModel tableModel);

	void showBill(IBill bill);

	void save();

	void payAmountOfMoneyforBill(IBill bill);

	void changetoOnTheRoadPaymentBuilder();

	void changeToOnTermPaymentBuilder();

	void finishBill();

	void changeToOnTheRoadPaymentBillBuilder();

	void changeToOnTermPaymentBillBuilder();

	IBook makeEmptyBook();

}