package main;

import java.util.function.Predicate;
import view.Login;
import model.entities.person.Collaborator;
import view.console.Console;

/**
 * This class contain the main static method for
 * running the entire project.
 * 
 * Inside the main method, will be called a View Class named
 * Login. This class contains a method called print. This method will
 * show the first page of the system.
 * 
 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
 *
 */

public class Main {
	public static void main(String[] args) {
		new Login().print();
	}
	
	@SuppressWarnings("unused")
	private static void predicateExample() {
		var coll = new Collaborator();
		Predicate<Collaborator> pre = o -> coll.getName().equals(o.getName());
		
		coll.setName("Pedro");
		
		String in = Console.<Collaborator>getInput("Insira um nome", coll, pre, "Erro");
		System.out.println(in);
	}
}
