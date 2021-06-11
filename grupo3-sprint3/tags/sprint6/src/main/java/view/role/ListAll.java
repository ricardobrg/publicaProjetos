package view.role;

import java.util.ArrayList;

import com.google.gson.JsonObject;

/***
 * RoleListAll. 
 * Lists all Roles, Access Levels and IDs. 
 * Which is an ArrayList of Service Providers, in Controllers.
 * Otherwise throws an error message. 
 * 
 * @version 2.0.0
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */
public class ListAll {
	
	public void print(ArrayList<JsonObject> roles) {
		
		System.out.println();
		System.out.println("=======================");
		
        for (JsonObject item : roles) {
            if (item != null) {
                System.out.print(item.get("id") + " - ");
                System.out.print(item.get("name").getAsString()+" - ");
                System.out.println(item.get("accessLevel"));
            }else {
                System.out.println("Nenhum cargo Cadastrado!");
            }
        }
		System.out.println("=======================");
	}
}
