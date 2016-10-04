package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import repository.IBillsRepository;

public class ShowBillsTermFrame extends JFrame implements IPaymentSubscriber {

	private JPanel contentPane;

	private IBillsRepository billsRepository;

	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public ShowBillsTermFrame(IBillsRepository billsRepository) {
		this.billsRepository = billsRepository;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new ShowBillsTermPanel(billsRepository);
		panel.setBounds(10, 11, 414, 239);
		
		contentPane.add(panel);
		
		billsRepository.subscribe(this);
		
		updateTerm();
	}

	@Override
	public void updateTerm() {
		((ShowBillsTermPanel) panel).updateBillsTermTable();
	}

}
