package view.console;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.function.Predicate;

import utils.validations.string.TextValidation;
import utils.UtilsNumber;
import utils.validations.datetime.DateValidation;
import utils.validations.number.CEPValidation;
import utils.validations.number.CNPJValidation;
import utils.validations.number.CPFValidation;
import utils.validations.number.PISValidation;
import utils.validations.number.TelephoneValidation;
import utils.validations.string.EmailValidation;

/**
 * This class provides methods that helps the process of printing and reading
 * data in console.
 * 
 * @version 1.0.0
 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
 *
 */
public class Console {

	private static Scanner scanner;

	/**
	 * Prints a menu header containing a text passed by parameter
	 * 
	 * @param title String: Text that will be displayed in heeader
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static void printHeader(String title) {
		System.out.println("================");
		System.out.println(title);
		System.out.println("================");
	}

	/**
	 * Provides a menu with options for selection.
	 * 
	 * @param optionsArray String[]: Array of String containing options to select
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static void printOptions(ArrayList<String> optionsArray) {
		int index = 1;
		for (String item : optionsArray) {
			System.out.println(index + ". " + item);
			index ++;
		}
		System.out.println("0. Sair");
	}

	/**
	 * When called, it opens a scanner that will read numbers in a interval
	 * (specified by parameters).<br>
	 * If the value typed is out of bounds of the specified parameters, the error
	 * message (also passed as parameter) will be printed.<br>
	 * It also prints a small message that will be displayed before the input value.
	 * 
	 * @param prompt <code>String</code>: Message printed before value input
	 * @param min    <code>int</code>: Minimum value accepted by the scanner
	 * @param max    <code>int</code>: Maximum value accepted by the scanner
	 * @param error  <code>String</code>: Message that will be displayed if the value types is
	 *               out of bounds
	 * @return <code>Double</code>: The value typed in console.
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static double getInputNumber(String prompt, double min, double max, String error) {
		double input;

		while (true) {
			System.out.print(prompt);
			scanner = new Scanner(System.in);

			if (scanner.hasNextDouble()) {
				input = scanner.nextDouble();
				if (UtilsNumber.isValidNumber(input, min, max))
					break;
				System.out.println(error);
			} else
				System.out.println(error);
		}
		return input;
	}
	
	public static int getInputNumber(String prompt, int min, int max, String error) {
		int input;

		while (true) {
			System.out.print(prompt);
			scanner = new Scanner(System.in);

			if (scanner.hasNextDouble()) {
				input = scanner.nextInt();
				if (UtilsNumber.isValidNumber(input, min, max))
					break;
				System.out.println(error);
			} else
				System.out.println(error);
		}
		return input;
	}

	/**
	 * When called, it opens a scanner that will ask for the user's name (specified
	 * by parameters).<br>
	 * The validator specifies only letters.<br>
	 * While the user types an invalid message, it is going to repeat.
	 * 
	 * @param prompt String: Message printed before value input
	 * @return The value typed in console.
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
	 */
	public static String getName(String prompt) {
		scanner = new Scanner(System.in);
		String input = "";

		System.out.println(prompt);

		while (input.length() < 3 || input.isBlank())
			input = scanner.nextLine();
		
		return input;
	}

	/**
	 * When called, it opens a scanner that will ask for the user's name (specified
	 * by parameters).<br>
	 * The validator specifies only letters.<br>
	 * While the user types an invalid message, it is going to repeat.
	 * 
	 * @param prompt String: Message printed before value input
	 * @return <code>String</code>: The value typed in console.
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static <T> String getInput(String prompt, T t, Predicate<T> pre, String error) {
		scanner = new Scanner(System.in);
		String input;

		System.out.print(prompt);
		while (true) {
			input = scanner.next();
			if (pre.test(t))
				break;
			else
				System.out.println(error);
		}

		return input;
	}

	/**
	 * Method for reading a String in console
	 * 
	 * @param prompt String: Message that will be displayed before value input
	 * @return String: The inputed value in console
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static String getText(String prompt) {
		scanner = new Scanner(System.in);
		String input = "";

		while (!TextValidation.isCharactersValid(input)) {
			System.out.println(prompt);
			input = scanner.next();
		}
		return input;
	}

	/**
	 * When called, it opens a scanner that will ask for only numbers.<br>
	 * While the user types an invalid message, it is going to repeat.
	 * 
	 * @param prompt String: Message printed before value input
	 * @return The value typed in console.
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static String getOnlyNumbers(String prompt) {
		scanner = new Scanner(System.in);
		String input = "";

		while (!UtilsNumber.isOnlyNumbers(input)) {
			System.out.println(prompt);
			input = scanner.nextLine();
		}
		return input;
	}

	/**
	 * When called, it opens a scanner that will ask for a valid CPF.<br>
	 * While the user types an invalid message, it is going to repeat.
	 * 
	 * @param prompt String: Message printed before value input
	 * @return The value typed in console.
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static String getCPF(String prompt) {
		scanner = new Scanner(System.in);
		String input = "";
		
		while (!CPFValidation.isCpfValid(input)) {
			System.out.println(prompt);
			input = scanner.nextLine();
		}
		
		return UtilsNumber.onlyNumbers(input);
	}

	/**
	 * When called, it opens a scanner that will ask for a valid CNPJ.<br>
	 * While the user types an invalid message, it is going to repeat.
	 * 
	 * @param prompt String: Message printed before value input
	 * @return The value typed in console.
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static String getCNPJ(String prompt) {
		scanner = new Scanner(System.in);
		String input = "";

		while (!CNPJValidation.isCnpjValid(input)) {
			System.out.println(prompt);
			input = scanner.nextLine();
		}
		return UtilsNumber.onlyNumbers(input);
	}

	/**
	 * When called, it opens a scanner that will ask for a valid email.<br>
	 * While the user types an invalid message, it is going to repeat.
	 * 
	 * @param prompt String: Message printed before value input
	 * @return The value typed in console.
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static String getEmail(String prompt) {
		scanner = new Scanner(System.in);
		String input = "";
		while (!EmailValidation.isEmailValid(input)) {
			System.out.println("Email: ");
			input = scanner.nextLine();
		}
		return input;
	}

	/**
	 * When called, it opens a scanner that will ask for a valid admission date.<br>
	 * If the user hits "Enter" it inserts the current date.<br>
	 * While the user types an invalid message, it is going to repeat.
	 * 
	 * @param prompt String: Message printed before value input
	 * @return The value typed in console.
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static String getAdmissionDate(String prompt) {
		scanner = new Scanner(System.in);
		String input = "";

		while (!DateValidation.isDateValid(input)) {
			System.out.println(prompt);
			input = scanner.nextLine();
			if (input.equals("")) {
				final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				final Calendar cal = Calendar.getInstance();
				return df.format(cal.getTime());
			}
		}
		return input;
	}

	/**
	 * When called, it opens a scanner that will ask for a valid date.<br>
	 * While the user types an invalid message, it is going to repeat.
	 * 
	 * @param prompt String: Message printed before value input
	 * @return The value typed in console.
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static String getDate(String prompt) {
		scanner = new Scanner(System.in);
		String input = "";

		while (!DateValidation.isDateValid(input)) {
			System.out.println(prompt);
			input = scanner.nextLine();
		}
		return input;
	}

	/**
	 * When called, it opens a scanner that will ask for a valid admission
	 * phone.<br>
	 * While the user types an invalid message, it is going to repeat.
	 * 
	 * @param prompt String: Message printed before value input
	 * @return The value typed in console.
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static String getPhone(String prompt) {
		scanner = new Scanner(System.in);
		String input = "";

		while (!TelephoneValidation.isPhoneValid(input)) {
			System.out.println("Telefone: ");
			input = scanner.nextLine();
		}
		return input;
	}

	/**
	 * When called, it opens a scanner that will ask for a valid admission CEP.<br>
	 * While the user types an invalid message, it is going to repeat.
	 * 
	 * @param prompt String: Message printed before value input
	 * @return The value typed in console.
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static String getCEP(String prompt) {
		scanner = new Scanner(System.in);
		String input = "";

		while (!CEPValidation.isCepValid(input)) {
			System.out.println(prompt);
			input = scanner.nextLine();
		}
		return input;
	}

	/**
	 * When called, it opens a scanner that will ask for a valid admission PIS.<br>
	 * While the user types an invalid message, it is going to repeat.
	 * 
	 * @param prompt String: Message printed before value input
	 * @return The value typed in console.
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static String getPIS(String prompt) {
		scanner = new Scanner(System.in);
		String input = "";

		while (!PISValidation.isPisValid(input)) {
			System.out.println("PIS: ");
			input = scanner.nextLine();
		}
		return input;
	}
}
