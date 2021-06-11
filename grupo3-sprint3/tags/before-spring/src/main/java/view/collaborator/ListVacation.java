package view.collaborator;

import com.google.gson.JsonObject;
import controller.CollaboratorController;

public class ListVacation {

  CollaboratorController collabController = new CollaboratorController();

  public void print(JsonObject vacationList) {

    System.out.println();
    System.out.println("=======================");

    System.out.printf("%-15s %-15s %-25s %-20s %-25s %-25s \n", 
                      "CPF", 
                      "Nome", 
                      "Pode tirar f�rias?",
                      "Est� de f�rias?", 
                      "Tempo de F�rias", 
                      "Pagamento nas f�rias");

    System.out.printf("%-15s %-15s %-25s %-20s %-25s %-25s \n",         
        vacationList.get("cpf").getAsString(),
        vacationList.get("name").getAsString(),
        vacationList.get("canVacation").getAsString(),
        vacationList.get("inVacation").getAsString(),
        vacationList.get("vacationDays").getAsString(),
        vacationList.get("vacationPayment").getAsString());

    System.out.println("=======================");

  }

}
