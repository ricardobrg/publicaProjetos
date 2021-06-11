package com.publica.grupo2sprint3.view.providerView;

import com.publica.grupo2sprint3.controller.ProviderController;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.view.View;

/**
 * Abstract class to define the com.publica.grupo2sprint3.controller used in every View of Provider.
 * 
 * @version 2.0.0
 * 
 * @author Caio Shimada <xcaiosr@gmail.com>
 */
public abstract class ProviderView extends View{
	
	protected ProviderController controller;
	
	public ProviderView(Collaborator collab) {
		super(collab);
		this.controller = ProviderController.getInstance(collab);
	}
	
}
