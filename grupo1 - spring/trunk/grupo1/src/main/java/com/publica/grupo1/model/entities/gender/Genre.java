package com.publica.grupo1.model.entities.gender;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Genre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idGenre;
	@Column
	private String nameGenre;
	
	public Genre() {
		
	}
	
	public Genre(int idGenre, String nameGenre) {

		this.setIdGenre(idGenre);
		this.setNameGenre(nameGenre);
	}

	public int getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(int idGenre) {
		
			this.idGenre = idGenre;
		
	}

	public String getNameGenre() {
		return nameGenre;
	}

	public void setNameGenre(String nameGenre) {
		this.nameGenre = nameGenre;
	}

}
