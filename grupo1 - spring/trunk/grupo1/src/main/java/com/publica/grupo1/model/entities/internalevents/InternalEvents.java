package com.publica.grupo1.model.entities.internalevents;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.model.entities.endereco.Endereco;

@Entity
public class InternalEvents {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int idInternalEvents;
		
		private String imgUrl;

		private String nameEvent;
		private LocalDateTime dateHourEvent;
		private LocalDateTime finalHourEvent;
		
		@OneToOne
		private Endereco addressEvent;
		
		private ArrayList<Collaborator> organizerEvent;
		
		private ArrayList<Collaborator> invitedEvent;
		private String descriptionEvent;
		
		public int getIdInternalEvents() {
			return idInternalEvents;
		}

		public void setIdInternalEvents(int idInternalEvents) {
			this.idInternalEvents = idInternalEvents;
		}

		public String getImgUrl() {
			return imgUrl;
		}
		
		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}
		public String getNameEvent() {
			return nameEvent;
		}

		public void setNameEvent(String nameEvent) {
			this.nameEvent = nameEvent;
		}

		public void setDateHourEvent(LocalDateTime dateHourEvent) {
			if (!(LocalDateTime.now().isBefore(dateHourEvent))) {
				System.out.println("O evento não tem uma data válida.");
			}
			
			this.dateHourEvent = dateHourEvent;
		}

		public LocalDateTime getDateHourEvent() {
			return dateHourEvent;
		}
		
		public LocalDateTime getFinalHourEvent() {
			return finalHourEvent;
		}

		public void setFinalHourEvent(LocalDateTime finalHourEvent) {
			this.finalHourEvent = finalHourEvent;
		}

		public Endereco getAddressEvent() {
			return addressEvent;
		}

		public void setAddressEvent(Endereco addressEvent) {
			this.addressEvent = addressEvent;
		}

		public ArrayList<Collaborator> getOrganizerEvent() {
			return organizerEvent;
		}

		public void setOrganizerEvent(ArrayList<Collaborator> organizerEvent) {
			this.organizerEvent = organizerEvent;
		}

		public String getDescriptionEvent() {
			return descriptionEvent;
		}

		public void setDescriptionEvent(String descriptionEvent) {
			this.descriptionEvent = descriptionEvent;
		}

		public ArrayList<Collaborator> getInvitedEvent() {
			return invitedEvent;
		}

		public void setInvitedEvent(ArrayList<Collaborator> invitedEvent) {
			this.invitedEvent = invitedEvent;
		}

		public InternalEvents() {}
		
		public InternalEvents(int idInternalEvents, 
							  String imgUrl,
							  String nameEvent, 
							  LocalDateTime dateHourEvent, 
							  LocalDateTime finalHourEvent, 
							  Endereco addressEvent, 
							  ArrayList<Collaborator> organizerEvent,
							  String descriptionEvent,
							  ArrayList<Collaborator> invitedEvent) {
			
			this.idInternalEvents = idInternalEvents;
			this.nameEvent = nameEvent;
			this.dateHourEvent = dateHourEvent;
			this.finalHourEvent = finalHourEvent;
			this.addressEvent = addressEvent;
			this.organizerEvent = organizerEvent;
			this.descriptionEvent = descriptionEvent;
			this.invitedEvent = invitedEvent;
			this.imgUrl = imgUrl;
		}

	}

