package models.entities.workHours;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import models.entities.person.Collaborator;

/**
 * 
 * WorkEntry Class.
 * Stands for "Bater Ponto" in Portuguese.
 * Receives a collaborator, month and day (directly from the system's hour).
 * If the number of registries is odd, returns an error message.
 * 
 * @version 2.0.0
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 */

@Entity
@Table(name = "work_entries")
public class WorkEntry {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private LocalDateTime clock;
	
	@ManyToOne
	private Collaborator collaborator;

	public WorkEntry() {
		
	}
	
	public WorkEntry(Collaborator collaborator) {	
		this.collaborator = collaborator;
	}
	
	public WorkEntry(Collaborator collaborator, LocalDateTime clock) {
		this.collaborator = collaborator;
		this.clock = clock;
	}
	
	public void clockIn() {
		this.clock = LocalDateTime.now();
	}

	public LocalDateTime getClock(){
		return this.clock;
	}
	
	public void setClock(LocalDateTime clock) {
		this.clock = clock;
	}
	
	public Collaborator getCollaborator() {
		return this.collaborator;
	}

	public int getId() {
		return id;
	}
	
}
