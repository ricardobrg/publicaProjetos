package com.publica.grupo2sprint3.view.payrollView;

import com.publica.grupo2sprint3.controller.PayrollController;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.view.View;

/***
 * Class responsible for the Payroll com.publica.grupo2sprint3.view
 * 
 * @version 1.0.0
 * 
 * @author Diego Borba <diegoborba25@gmail.com>
 *
 */
public abstract class PayrollView extends View{
	
	protected PayrollController controller;
	
	public PayrollView(Collaborator collab) {
		super(collab);
		this.controller = PayrollController.getInstance(collab);
	}


}
