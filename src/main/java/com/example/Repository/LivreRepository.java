package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Models.Livre;

public interface LivreRepository extends JpaRepository<Livre, String> {
    
	List<Livre> findByTitreContainingAndAuteurContaining(String titre, String auteur);
}
