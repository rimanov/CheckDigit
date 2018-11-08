
/*
Rasul Imanov
Programming HW #6
06/17/18
Mac OS, Eclipse
This program generates random 12 digit number the input of the user: card issuer number. It makes use of Luhn algorithm to calculate Check Sum and Check Digit. 
It then prompts the user to enter the ArrayList size and the issuer id + account number for each index until the provided size(limit) by the user. 
It then writes the generated CrediCardNumber list to a file and get the middle element of the ArrayList and displays it.  
*/
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Prog6 {
	static Scanner scanner = new Scanner(System.in); // to take user input
	static ArrayList<CreditCardNumber> aList;
	static CreditCardNumber ccnumber;

	public static void main(String[] args) {
		ccnumber = userInput();
		System.out.println("The complete number from your input: " + ccnumber.toString());
		aList = userInputArrList();
		Collections.sort(aList);
		writeArr(aList);
		int middle = aList.size() / 2;
		System.out.println("Middle element: " + aList.get(middle));
	}

	public static CreditCardNumber userInput() {

		while (true) {
			try {
				System.out.println("Enter a credit card issuer number: ");
				String id = scanner.next(); // issuerid
				CreditCardNumber num = new CreditCardNumber();
				num.changeId(id);
				return num;
			} catch (IllegalArgumentException e) {
				System.out.println(e.toString());
				continue;
			}
		}
	}

	public static ArrayList<CreditCardNumber> userInputArrList() {
		System.out.println("Enter the number of elements in the array: ");
		int arrLength = scanner.nextInt();
		if (arrLength < 1) {
			arrLength = 1;
		}
		aList = new ArrayList<CreditCardNumber>(arrLength);
		for (int i = 0; i < arrLength;) {
			try {
				System.out.println("Enter an issuer ID# (6 digits) for element #" + i);
				String issuerId = scanner.next();
				System.out.println("Enter an account # (9 digits) for element #" + i);
				String accountNum = scanner.next();
				CreditCardNumber obj = new CreditCardNumber(issuerId, accountNum);
				aList.add(obj);
				i++;
			} catch (IllegalArgumentException e) {
				System.out.println(e.toString()); // don't throw just
													// print
			}
		}
		return aList;
	}

	public static void writeArr(ArrayList<CreditCardNumber> temp) {
		try (FileOutputStream outFile = new FileOutputStream("/Users/raska/Desktop/prog6.txt")) {

			Iterator<CreditCardNumber> iter = temp.iterator();
			PrintWriter prtWriter = new PrintWriter(outFile, true);

			while (iter.hasNext()) {
				CreditCardNumber obj = iter.next();
				prtWriter.println(obj.toString());
			}
			System.out.println("Finished writing to prog 6.txt");

		} catch (IOException e) {
			System.err.println(e);
			return;
		}
	}
}
/*
 * OUTPUT ---------------------------------------------------------- FIRST RUN:
 * ---------------------------------------------------------- Enter a credit
 * card issuer number: 654321 The complete number from your input: 6543 2120
 * 7488 7045 Enter the number of elements in the array: 4 Enter an issuer ID# (6
 * digits) for element #0 123123 Enter an account # (9 digits) for element #0
 * 78978*789 java.lang.IllegalArgumentException: Invalid input, try again! Enter
 * an issuer ID# (6 digits) for element #0 787878 Enter an account # (9 digits)
 * for element #0 12345678 java.lang.IllegalArgumentException: Invalid input,
 * try again! Enter an issuer ID# (6 digits) for element #0 1234567 Enter an
 * account # (9 digits) for element #0 987654321
 * java.lang.IllegalArgumentException: Invalid input, try again! Enter an issuer
 * ID# (6 digits) for element #0 898989 Enter an account # (9 digits) for
 * element #0 1234512345 java.lang.IllegalArgumentException: Invalid input, try
 * again! Enter an issuer ID# (6 digits) for element #0 123?56 Enter an account
 * # (9 digits) for element #0 123123123 java.lang.IllegalArgumentException:
 * Invalid input, try again! Enter an issuer ID# (6 digits) for element #0
 * 787878 Enter an account # (9 digits) for element #0 123456789 Enter an issuer
 * ID# (6 digits) for element #1 121212 Enter an account # (9 digits) for
 * element #1 123123123 Enter an issuer ID# (6 digits) for element #2 777777
 * Enter an account # (9 digits) for element #2 888888888 Enter an issuer ID# (6
 * digits) for element #3 454545 Enter an account # (9 digits) for element #3
 * 456456456 Finished writing to prog 6.txt Middle element: 7777 7788 8888 8887
 * 
 * ---------------------------------------------------------- SECOND RUN:
 * ---------------------------------------------------------- Enter a credit
 * card issuer number: 12345 java.lang.IllegalArgumentException: Invalid Input!
 * Enter a credit card issuer number: 12#123 java.lang.IllegalArgumentException:
 * Invalid Input! Enter a credit card issuer number: 1234567
 * java.lang.IllegalArgumentException: Invalid Input! Enter a credit card issuer
 * number: 123456 The complete number from your input: 1234 5638 0753 6340 Enter
 * the number of elements in the array: 0 Enter an issuer ID# (6 digits) for
 * element #0 787878 Enter an account # (9 digits) for element #0 111222333
 * Finished writing to prog 6_1.txt Middle element: 7878 7811 1222 3333
 * 
 */
