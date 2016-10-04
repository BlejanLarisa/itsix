package repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.IBill;
import model.IInnerPaymentPublisher;
import model.IInnerPublisher;
import model.ISubscriber;
import model.PaymentPublisher;
import model.Publisher;
import view.IPaymentSubscriber;

public class ParseObjectsFile implements IParser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void serialize(IBillsRepository billsRepository) {
		try {

			FileOutputStream fileOut = new FileOutputStream("docs\\repo.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(billsRepository);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in /docs/repo.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public IBillsRepository deserializeBillsRepository() {
		BillsRepository billsRepository = null;
		try {
			FileInputStream fileIn = new FileInputStream("docs\\repo.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			billsRepository = (BillsRepository) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return null;
		}
		System.out.println("Deserialized BillsRepository...");
		System.out.println("Name: " + billsRepository.getUnpaidBills().size());
		return billsRepository;
	}

	public void save(IBillsRepository billsRepository) {
		serialize(billsRepository);
	}

	@Override
	public IBillsRepository parse() {
		List<ISubscriber> subscribers = new ArrayList<>();
		List<IPaymentSubscriber> paymentSubscribers = new ArrayList<>();
		IInnerPublisher publisher = new Publisher(subscribers);
		IInnerPaymentPublisher paymentPublisher = new PaymentPublisher(paymentSubscribers);
		IBillsRepository billsRepository = deserializeBillsRepository();
		billsRepository.setPublisher(publisher);
		billsRepository.setPaymentPublisher(paymentPublisher);
		for (IBill bill : billsRepository.getUnpaidBills()) {
			List<ISubscriber> billsSubscribers = new ArrayList<>();
			IInnerPublisher innerBillsPublisher = new Publisher(billsSubscribers);
			bill.setPublisher(innerBillsPublisher);
		}
		return billsRepository;

	}

}
