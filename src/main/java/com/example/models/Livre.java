package com.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Livre {
	@Id
    private String isbn;
    private String titre;
    private String auteur;


    public Livre() {
    }

    public Livre(String isbn, String titre, String auteur) {
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
    }

    // Getters et setters
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

}
