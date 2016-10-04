package main;

import java.awt.EventQueue;

import builders.IPaymentBuilder;
import builders.OnTermPaymentBuilder;
import builders.OnTheRoadPaymentBuilder;
import controller.BookStoreController;
import controller.IBookStoreController;
import repository.IBillsRepository;
import repository.IParser;
import repository.ParseEmptyFile;
import repository.ParseObjectsFile;
import view.BookStoreFrame;

public class App {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		IParser parser = new ParseEmptyFile();

	    //IParser parser = new ParseObjectsFile();
		IBillsRepository repository = parser.parse();

		IPaymentBuilder onTheRoadPaymentBuilder = new OnTheRoadPaymentBuilder();
		IPaymentBuilder onTermPaymentBuilder = new OnTermPaymentBuilder();
		IBookStoreController bookStoreController = new BookStoreController(repository, onTermPaymentBuilder,
				onTheRoadPaymentBuilder);
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BookStoreFrame window = new BookStoreFrame(repository, bookStoreController);
					bookStoreController.setBookStoreFrame(window);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
