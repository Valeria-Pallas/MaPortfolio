package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Livre;
import com.example.repository.LivreRepository;

@Service
public class BibliothequeService {

	@Autowired
    private LivreRepository livreRepository;

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    public void addLivre(Livre nouveauLivre) {
    	livreRepository.save(nouveauLivre);
    }

    public void deleteLivre(String isbn) {
        livreRepository.deleteById(isbn);
    }

    public Livre getLivreByIsbn(String isbn) {
        return livreRepository.findById(isbn).orElse(null);
    }

    public void updateLivre(String isbn, Livre livreModifie) {
        Livre livre = livreRepository.findById(isbn).orElse(null);
        if (livre != null) {
            livre.setTitre(livreModifie.getTitre());
            livre.setAuteur(livreModifie.getAuteur());
            livreRepository.save(livre);
        }
    }

    public List<Livre> rechercherLivres(String titre, String auteur) {
        if (titre.isEmpty() && auteur.isEmpty()) {
            return getAllLivres();
        }

        return livreRepository.findByTitreContainingAndAuteurContaining(titre, auteur);
    }
}