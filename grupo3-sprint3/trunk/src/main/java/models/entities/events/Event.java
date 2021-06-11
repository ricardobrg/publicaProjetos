package models.entities.events;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import models.entities.person.Collaborator;
import models.entities.person.Endereco;
import models.entities.security.AccessLevel;
import utils.validations.datetime.DateValidation;

/***
 * Class Event
 * 
 * Class that defines events to happen. Includes for example: Decription of the event, name, etc
 * 
 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
 * @author Pablo Mafessoli
 *  
 * @version 1.0.0
 */
@Entity
@Table(name = "events")
public class Event {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;

	@Column(name = "event_time_start")
	private int eventTimeStart;

	@Column(name = "event_time_end")
	private int eventTimeEnd;

	@Column
	private String date;
	@Column(name = "pathImage")
	private String pathImage;

	@Column
	private String description;
	
	@ManyToOne
	private Endereco address;
	
	@Column
	private String complement;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Collaborator> subscribers;

	private AccessLevel accessLevel;

	@ManyToOne
	private Collaborator creator;
	
	public Event() {
		
	}

	public Event(int id, String name, int eventTimeStart, int eventTimeEnd, String date, String pathImage,
			String description, String complement, ArrayList<Collaborator> subscribers, Collaborator collaborator,
			String cep) {
		super();
		this.id = id;
		this.name = name;
		this.eventTimeStart = eventTimeStart;
		this.eventTimeEnd = eventTimeEnd;
		this.date = date;
		this.pathImage = pathImage;
		this.description = description;
		this.complement = complement;
		this.subscribers = subscribers;
		this.creator = collaborator;
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

	public int getEventTimeStart() {
		return eventTimeStart;
	}

	public void setEventTimeStart(int eventTimeStart) {
		this.eventTimeStart = eventTimeStart;
	}

	public int getEventTimeEnd() {
		return eventTimeEnd;
	}

	public void setEventTimeEnd(int eventTimeEnd) {
		this.eventTimeEnd = eventTimeEnd;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {

		if (DateValidation.isDateValid(date)) {
			this.date = date;
		}
		this.date = date;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public void addCollabEvent(Collaborator collab) {
		subscribers.add(collab);
	}
	
	public void setSubscribers(ArrayList<Collaborator> subscribers) {
		this.subscribers = subscribers;
	}
	
	public List<Collaborator> getSubscribers() {
		return this.subscribers;
	}

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}
	
	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public Collaborator getCollaborator() {
		return creator;
	}

	public void setCollaborator(Collaborator collaborator) {
		this.creator = collaborator;
	}
	
	public boolean checkSubscribed(Collaborator collab) {
		for (Collaborator c : subscribers) {
			if(c.getCpf().equals(collab.getCpf())) {
				return true;
			}
			
		}
		
		return false;
	}

}