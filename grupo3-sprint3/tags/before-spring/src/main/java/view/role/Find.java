package view.role;

import java.util.ArrayList;
import com.google.gson.JsonObject;

import controller.RoleController;
import view.console.Console;

/***
 * RoleFind.<br>
 * The print method searches for Role information via its ID (automatically inserted in Add part).
 * If found, returns it. Otherwise throws an error message.
 * 
 * @version 2.0.0
 * @author Giovani bruno Buzzi <buzzi.giovanni@outlook.com>
 */

public class Find {

  public void print(ArrayList<JsonObject> roles) {
    RoleController roleController = new RoleController();

    System.out.println();
    System.out.println("=======================");

    int lastId = 0;
    for (JsonObject item : roles) {
      if (item != null) {
        System.out.print(item.get("id") + " - ");
        System.out.print(item.get("name").getAsString() + " - ");
        System.out.println(item.get("accessLevel"));
        lastId = item.get("id").getAsInt();
      } else {
        System.out.println("Nenhum cargo Cadastrado!");
      }
    }

    JsonObject role = new JsonObject();
    int id = Console.getInputNumber("Informe o ID do objeto que será buscado: ", 0, lastId,
        "ID inválido!");

    role.addProperty("id", id);
    role = roleController.find(role);

    System.out.println("---- Role ----");
    System.out.println("Nome: " + role.get("name"));
    System.out.println("Nivel de acesso: " + role.get("accessLevel"));

  }
}
