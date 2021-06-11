package view.console;

import java.util.Scanner;
import java.util.function.Predicate;

import utils.UtilsNumber;

/**
 * This class provides methods that helps the proccess of printing
 * and reading data in console.
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
	 * @param optionsArray String[]: Array of String containing options to select
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static void printOptions(String[] optionsArray) {
		for (int i = 0; i < optionsArray.length; i++) {
			int num = i + 1;
			System.out.println(num + ". " +optionsArray[i]);
		}
		System.out.println("0. Sair");
	}
	
	/**
	 * When callen, it opens a scanner that will read numbers in a interval (specified by parameters).<br>
	 * If the value typed is out of bound of the specified parameters, will be printed the erros message,
	 * also passed by parameter.<br>
	 * It also print a small message that will be displayed before the input value.
	 * 
	 * @param prompt String: Message printed before value input
	 * @param min int: Minimum value accepted by the scanner
	 * @param max int: Maximum value accepted by the scanner
	 * @param error String: Message that will be displayed if the value types is out of bound
	 * @return The value typed in console.
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static double getInputNumber(String prompt, double min, double max, String error) {
		double input;
		
		while(true) {
			System.out.print(prompt);
			scanner = new Scanner(System.in);
			
			if(scanner.hasNextDouble()) {
				input = scanner.nextDouble();
				if(UtilsNumber.isValidNumber(input, min, max))
					break;
				System.out.println(error);
			}
			else
				System.out.println(error);
		}
		return input;
	}

	public static <T> String getInput(String prompt, T t, Predicate<T> pre, String error) {
		scanner = new Scanner(System.in);
		String output;
		
		System.out.print(prompt);
		while(true) {
			output = scanner.next();
			if(pre.test(t))
				break;
			else
				System.out.println(error);
		}
		
		return output;
	}
	
	/**
	 * Method for reading a String in console
	 * 
	 * @param prompt String: Message that will be displayed before value input
	 * @return String: The inputed value in console
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static String getInput(String prompt) {
		scanner = new Scanner(System.in);
		String input;
		while(true) {
			System.out.println(prompt);
			if(scanner.hasNext()) {
				input = scanner.next();
				break;
			}
		}
		
		return input;
	}
}












