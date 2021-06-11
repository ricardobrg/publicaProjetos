package com.publica.grupo2sprint3.model.person.physicalperson;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String description;
	@ManyToOne
	private Collaborator organizer;
	private LocalDateTime startTime;
	
	public Event() {}

	public Event(String name, String description, Collaborator organizer, LocalDateTime startTime) {
		super();
		this.name = name;
		this.description = description;
		this.organizer = organizer;
		this.startTime = startTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collaborator getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Collaborator organizer) {
		this.organizer = organizer;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	
	
}
