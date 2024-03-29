package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class IntegerJTextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IntegerJTextField() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();

				if (!isNumber(ch) && !isValidSignal(ch) && ch != '\b') {
					e.consume();
				}
			}
		});

	}

	private boolean isNumber(char ch) {
		return ch >= '0' && ch <= '9';
	}

	private boolean isValidSignal(char ch) {
		if ((getText() == null || "".equals(getText().trim())) && ch == '-') {
			return true;
		}

		return false;
	}

}
