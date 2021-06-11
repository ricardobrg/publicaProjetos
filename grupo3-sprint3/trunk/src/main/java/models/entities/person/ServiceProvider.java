package models.entities.person;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that extends JuridicaPerson. Represents a Service Provider.
 * 
 * @Version: 1.1.3
 * 
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 * @author Pablo Mafessoli <mafessolip@gmail.com>
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com> 
 */

@Entity
@Table (name="service_providers")
public class ServiceProvider extends JuridicaPerson{
	
	String description;

	public ServiceProvider(String name, String cep, String email,
			String phone, String socialReason, String cnpj,
			String description) {
		
		super(name, cep, email, phone, socialReason, cnpj);		
		this.description = description;
	}
	
	public ServiceProvider() {}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
