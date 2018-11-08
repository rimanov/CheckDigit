
public class CreditCardNumber implements Comparable<CreditCardNumber> {
	private String issuerId = "000000";
	private String accountNum = "999999999";
	private int checkDigit = 9;
	private StringBuilder builder;

	public CreditCardNumber(String id, String accNum) {
		this();
		if (id == null || accNum == null || id.length() != 6 || accNum.length() != 9 || isDigit(id) == false
				|| isDigit(accNum) == false) {
			throw new IllegalArgumentException("Invalid input, try again!");
		} else
		accountNum = accNum;
		issuerId = id;

		setCheckDigit();

	}

	public CreditCardNumber() {
	} // default constructor

	public String getId() { // accessor
		return issuerId;
	}

	public String getAccNum() { // accessor
		return accountNum;
	}

	public int getCheckDigit() { // accessor
		return checkDigit;
	}

	// A
	private void setCheckDigit() { // assings checkDigit
		checkDigit = 0;
		int sum = checkSum();
		int temp = checkDigit + sum;
		if (temp % 10 != 0) {
			checkDigit = (10 - (checkSum() % 10)) % 10;
		}
	}

	// Method to check if each character in string is a digit
	public boolean isDigit(String s) {
		boolean isdigit = true;
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i))) {
				isdigit = false;
			}
		}
		return isdigit; // which is false
	}

	// B
	public void changeId(String id) {
		int max = 9;
		int min = 0;
		if (id == null || id.length() != 6 || isDigit(id) == false) {
			throw new IllegalArgumentException("Invalid Input!");
		} else {
			issuerId = id;
			builder = new StringBuilder();
		}

		for (int i = 0; i < 9; i++) {
			int randomNum = (int) (Math.random() * (max - min + 1)) + min;
			builder.append(randomNum);
			accountNum = builder.toString();
		}
		setCheckDigit();
	}

	// C
	private int checkSum() {
		int sum = 0;
		builder = new StringBuilder();
		builder.append(issuerId);
		builder.append(accountNum);
		for (int i = 0; i < builder.length(); i++) {
			// In each of the chars with an EVEN index
			if (i % 2 == 0) {
				int x = Character.getNumericValue(builder.charAt(i)); //// get the int value from the char
				int y = x * 2; // multiply it by 2
				if (y >= 10) {
					int z = y % 10;
					z += 1; //// if doubling it has 2 digits, add those digits
					builder.setCharAt(i, Character.forDigit(z, 10)); // put above result back into the StringBuilder
																		// atthe same index
				} else {
					builder.setCharAt(i, Character.forDigit(y, 10)); // put above result back into the StringBuilder at
																		// the same index
				}
			}
		}
		// Add the values of each digit in the StringBuilder
		for (int i = 0; i < builder.length(); i++) {
			sum += Character.getNumericValue(builder.charAt(i));
		}
		return sum;
	}

	// D

	public String toString() {
		builder = new StringBuilder();
		builder.append(issuerId).append(accountNum).append(checkDigit);
		builder.insert(4, ' ');
		builder.insert(9, ' ');
		builder.insert(14, ' ');
		return builder.toString();
	}

	public int compareTo(CreditCardNumber obj) {
		return this.toString().compareTo(obj.toString());
	}
}
