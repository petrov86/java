package calucalotor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {

	/**
	 * 
	 */

	private static final long serialVersionUID = -6674927086973480755L;
	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 370;
	private static final int START = 20;
	private static final int WIDTH = 70;
	private static final int HEIGHT = 30;
	private static final int GAP = 10;
	private static final int MAX_DIGITS = 15;
	private static final String ARR_OPERATIONS[] = { "+", "-", "*", "/", "=" };

	private static final String ARR_OPERATIONS_RESOURCES[] = {
			"resources/plus.jpg", "resources/minus.jpg",
			"resources/multiply.jpg", "resources/devide.jpg",
			"resources/equal.jpg" };

	private static final String DIGITS_RESOURCES[] = { "resources/01.jpg",
			"resources/02.jpg", "resources/03.jpg", "resources/04.jpg",
			"resources/05.jpg", "resources/06.jpg", "resources/07.jpg",
			"resources/08.jpg", "resources/09.jpg", "resources/dot.jpg",
			"resources/0.jpg" };

	private static final String NULL_VALUE = "0";
	private static final String EMPTY_VALUE = "";
	private int startX = 20;
	private int startY = 20;
	private JPanel panel = new JPanel();
	private JTextArea firstDisplay;
	private JTextArea secondDisplay;
	// private String tmpEnteredValue = "0";

	private String enteredValueAndOperation = "";
	private JButton[] digits = new JButton[11];
	private JButton[] operations = new JButton[5];
	private JButton clr = new JButton();
	private JButton back = new JButton();
	private String A = null;
	private String B = null;
	private String operation = null;
	private String nextOperation = null;
	private String result = null;
	private Boolean isFirstDisplayChanged = false;

	public Calculator() {

		setTitle("Calculator");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		try {
			Image image = Toolkit.getDefaultToolkit().getImage(
					getClass().getResource("resources/icon.jpg"));
			setIconImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}

		panel.setLayout(null);
		getContentPane().add(panel);
		Font font = new Font("Arial", Font.BOLD, 25);
		panel.setBackground(Color.LIGHT_GRAY);

		// First Display
		firstDisplay = new JTextArea(20, 1);
		firstDisplay.setBounds(startX, startY, FRAME_WIDTH - 2 * START, 35);
		firstDisplay
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		firstDisplay.setEditable(false);
		firstDisplay.setFont(font);
		panel.add(firstDisplay);

		// Second Display
		secondDisplay = new JTextArea(20, 1);
		secondDisplay.setBounds(startX, startY + 35, FRAME_WIDTH - 2 * START,
				15);
		secondDisplay
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		secondDisplay.setEditable(false);
		panel.add(secondDisplay);

		displayManager(NULL_VALUE, EMPTY_VALUE);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setDigitsButtons();
		setOperationsButtons();
		setVisible(true);
	}

	private void setDigitsButtons() {
		startX = START;
		startY = START + 80 + HEIGHT + GAP;

		for (int i = 0; i < digits.length; i++) {
			digits[i] = new JButton();
		}

		int counter = 1;
		for (JButton button : digits) {
			button.setBounds(startX, startY, WIDTH, HEIGHT);
			if (counter == 10) {
				button.setName(".");
			} else if (counter == 11) {
				button.setName("0");
			} else {
				button.setName(Integer.toString(counter));
			}

			try {
				Image img = ImageIO.read(getClass().getResource(
						DIGITS_RESOURCES[counter - 1]));
				ImageIcon icon = new ImageIcon(img);
				button.setIcon(icon);
			} catch (IOException ex) {

			} catch (Exception e) {

			}

			button.addActionListener(new DigitsButtonListener());
			panel.add(button);
			if (counter % 3 == 0) {
				startY += HEIGHT + GAP;
				startX = START;
			} else
				startX += WIDTH + GAP;
			counter++;
		}

	}

	private void setOperationsButtons() {
		startY = START + 80;
		startX = START;

		// set the Clear Button
		// clr.setText("Clr");
		clr.setBounds(startX, startY, WIDTH, HEIGHT);
		clr.addActionListener(new ClearButtonListener());
		try {
			Image img = ImageIO.read(getClass().getResource(
					"resources/clear.jpg"));
			clr.setIcon(new ImageIcon(img));
		} catch (IOException ex) {

		} catch (Exception e) {

		}
		panel.add(clr);

		// set the Back Button
		back.setName("Back");
		back.setBounds(startX + WIDTH + GAP, startY, WIDTH, HEIGHT);
		back.addActionListener(new BackButtonListener());
		try {
			Image img = ImageIO.read(getClass().getResource(
					"resources/back.jpg"));

			back.setIcon(new ImageIcon(img));
		} catch (IOException ex) {

		} catch (Exception e) {

		}
		panel.add(back);
		startX = FRAME_WIDTH - START - WIDTH;

		// set the Operation Buttons
		for (int i = 0; i < operations.length; i++) {
			operations[i] = new JButton();
			operations[i].setBounds(startX, startY, WIDTH, HEIGHT);
			operations[i].setName(ARR_OPERATIONS[i]);
			try {
				Image img = ImageIO.read(getClass().getResource(
						ARR_OPERATIONS_RESOURCES[i]));
				operations[i].setIcon(new ImageIcon(img));
			} catch (IOException ex) {

			} catch (Exception e) {

			}

			startY += GAP + HEIGHT;
			operations[i].addActionListener(new OperationButtonListener());
			panel.add(operations[i]);
		}
	}

	private void displayManager(String firstDisplay, String secondDisplay) {
		if (firstDisplay.startsWith("-")) {
			firstDisplay = firstDisplay.substring(1) + "-";
		}

		if (firstDisplay.endsWith(".")) {
			firstDisplay = "."
					+ firstDisplay.substring(0, firstDisplay.length() - 1);
		}

		if (secondDisplay.endsWith(".")) {
			secondDisplay = "."
					+ secondDisplay.substring(0, secondDisplay.length() - 1);
		}

		this.firstDisplay.setText(firstDisplay);
		this.secondDisplay.setText(secondDisplay);
	}

	private class ClearButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// tmpEnteredValue = "0";
			displayManager(NULL_VALUE, EMPTY_VALUE);
			A = null;
			B = null;
			operation = null;
			nextOperation = null;
			result = null;
			isFirstDisplayChanged = true;
		}
	}

	private class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			isFirstDisplayChanged = true;
			if (firstDisplay.getText().equals(NULL_VALUE)) {
				return;
			} else {
				String tmp = "";
				if (firstDisplay.getText().startsWith(".")) {
					tmp = firstDisplay.getText().substring(1);
				} else {
					char field[] = (firstDisplay.getText()).toCharArray();
					if (field.length == 1) {
						displayManager(NULL_VALUE, EMPTY_VALUE);
						return;
					}

					for (int i = 0; i < field.length - 1; i++) {
						tmp += String.valueOf(field[i]);
					}

					// The check is now in displayManager
					/*
					 * if (tmp.endsWith(".")) { tmp = "." + tmp.substring(0,
					 * tmp.length() - 1); }
					 * 
					 * System.out.println(tmp);
					 */
				}
				if (operation != null) {
					operation = null;
					A = null;
				}
				displayManager(tmp, EMPTY_VALUE);
			}
		}
	}

	private class DigitsButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			// get input digit and the entered value till now
			String input = ((JButton) e.getSource()).getName();
			String displayedValue = firstDisplay.getText();

			if (displayedValue.equals(A)) {
				displayedValue = NULL_VALUE;
			}

			if (displayedValue.equals(NULL_VALUE) && !input.equals(".")) {
				displayedValue = "";
			}

			if (input.equals(".")) {
				if (displayedValue.contains(".")) {
					return;
				}
			}

			if (displayedValue.length() < MAX_DIGITS) {

				if (displayedValue.startsWith(".")) {
					displayedValue = displayedValue.substring(1) + ".";
				}
				// The check is now in displayManager
				// if (input.equals(".")) {
				// displayManager(input + displayedValue,
				// secondDisplay.getText());
				// } else {
				// displayManager(displayedValue + input,
				// secondDisplay.getText());
				// }
				System.out.println(displayedValue + input);
				displayManager(displayedValue + input, secondDisplay.getText());
				isFirstDisplayChanged = true;
			}

		}

	}

	private class OperationButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			// get operation and the entered value
			String input = ((JButton) e.getSource()).getName();
			String displayedValue = firstDisplay.getText();

			if (!input.equals("=")) {
				if ((A == null && !displayedValue.equals(NULL_VALUE) && !displayedValue
						.equals(EMPTY_VALUE))
						|| (operation == null && nextOperation == null)) {

					A = displayedValue;
					isFirstDisplayChanged = false;

				} else if (A != null && !displayedValue.equals(EMPTY_VALUE)
						&& isFirstDisplayChanged) {
					B = displayedValue;
				}

				if (B == null) {
					operation = input;
				} else {
					nextOperation = input;
				}
			}
			/*********************************************************************************/
			// manage the second display value

			if (!input.equals("=")) {

				char[] s = secondDisplay.getText().toCharArray();
				String str = " " + A;

				if (s.length > 0) {
					str = "";
					for (int j = 2; j < s.length; j++) {
						str += s[j];
					}
				}

				enteredValueAndOperation = operation + " " + str;

				if (nextOperation != null && B != null) {
					enteredValueAndOperation = nextOperation + " " + B + " "
							+ enteredValueAndOperation;
				}

				displayManager(firstDisplay.getText(), enteredValueAndOperation);
			} else if (input.equals("=")) {
				// clear second display
				enteredValueAndOperation = EMPTY_VALUE;

			}

			/*********************************************************************************/

			if (input.equals("=") && A != null && operation != null
					&& !displayedValue.equals(EMPTY_VALUE)
					&& isFirstDisplayChanged) {
				B = displayedValue;
			}

			// Calculate if it is ready
			if (isItReadyToCalculate()) {

				result = operation(A, B, operation);
				A = result;
				// result = null;
				B = null;
				operation = null;
				isFirstDisplayChanged = false;

				if (nextOperation != null) {
					operation = nextOperation;
					nextOperation = null;
				}

				displayedValue = A;
				displayManager(A, enteredValueAndOperation);
			}

		}

	}

	private boolean isItReadyToCalculate() {
		boolean isItReady = false;

		if (A != null && B != null && operation != null) {
			isItReady = true;
			validateNumbers();
		}

		return isItReady;
	}

	private void validateNumbers() {
		if (A.startsWith(".")) {
			A = A.substring(1);
		}

		if (B.startsWith(".")) {
			B = B.substring(1);
		}
	}

	private String operation(String numA, String numB, String operation) {

		double a = 0;
		double b = 0;

		try {
			a = Double.parseDouble(numA);
			b = Double.parseDouble(numB);
		} catch (NumberFormatException e) {
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
		}

		long num1 = 0L;
		long num2 = 0L;
		String result = null;
		Boolean isIntegerDivisionPossible = true;

		try {
			num1 = (long) a;// Long.parseLong(this.val1);
			num2 = (long) b;// Long.parseLong(this.val2);
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (operation.equals("/") && a % b != 0)
			isIntegerDivisionPossible = false;

		if (a == num1 && b == num2 && isIntegerDivisionPossible) {
			long lRes = 0L;
			if (operation.equals("+")) {
				lRes = num1 + num2;
			}

			if (operation.equals("-")) {
				lRes = num1 - num2;
			}

			if (operation.equals("*")) {
				lRes = num1 * num2;
			}

			if (operation.equals("/")) {

				lRes = num1 / num2;
			}
			result = String.valueOf(lRes);
		} else {

			double dRes = 0;
			if (operation.equals("+")) {
				dRes = a + b;
			}

			if (operation.equals("-")) {
				dRes = a - b;
			}

			if (operation.equals("*")) {
				dRes = a * b;
			}

			if (operation.equals("/")) {
				dRes = a / b;
			}

			result = String.valueOf(dRes);

			// check if the result is long number
			try {
				long longRes = (long) dRes;
				if (longRes == dRes) {
					result = String.valueOf(longRes);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		WriteFile.write(A + " " + operation + " " + B + " = " + result + "\n");
		System.out.println("------------");
		System.out.println(result);

		if (result.equals("Infinity")) {
			result = "Cannot divide by zero";
		}

		return result;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
