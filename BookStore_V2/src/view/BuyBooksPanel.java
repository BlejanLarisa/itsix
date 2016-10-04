package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.IBookStoreController;

public class BuyBooksPanel extends JPanel implements IBuyBooksPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton addBookButton;

	private JTextField enterISBNTextField;

	private IBookStoreController bookStoreControler;

	private JLabel enterISBNLabel;

	public BuyBooksPanel(IBookStoreController bookStoreControler) {

		this.bookStoreControler = bookStoreControler;
		setLayout(null);
		addBookButton = new JButton("Add book ");
		addBookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String ISBN = getISBN();

				bookStoreControler.addBookToPaidBooks(ISBN);

				enterISBNTextField.setText("");

			}

		});

		addBookButton.setBounds(276, 25, 123, 23);
		add(addBookButton);

		enterISBNTextField = new IntegerJTextField();
		enterISBNTextField.setBounds(135, 26, 86, 20);
		add(enterISBNTextField);
		enterISBNTextField.setColumns(10);

		enterISBNLabel = new JLabel("Enter ISBN");
		enterISBNLabel.setBounds(25, 29, 64, 14);
		add(enterISBNLabel);
	}

	@Override
	public String getISBN() {
		String ISBN = enterISBNTextField.getText();
		return ISBN;
	}
}
