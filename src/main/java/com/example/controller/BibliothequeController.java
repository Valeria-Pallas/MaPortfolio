package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.Models.Livre;
import com.example.service.BibliothequeService;

@Controller
@RequestMapping("/bibliotheque")
public class BibliothequeController {

    @Autowired
    private BibliothequeService bibliothequeService;

    @RequestMapping(value = {"/listeLivres", "/"}, method = RequestMethod.GET)
    public String listeLivres(Model model) {
        // Utiliser le service pour obtenir la liste des livres
        model.addAttribute("livres", bibliothequeService.getAllLivres());
        return "listeLivres";
    }

    @RequestMapping(value = "/ajouterLivre", method = RequestMethod.GET)
    public String afficherFormulaireAjout(Model model) {
        // Afficher le formulaire d'ajout de livre
        model.addAttribute("nouveauLivre", new Livre());
        return "formulaireAjoutLivre";
    }

    @RequestMapping(value = "/ajouterLivre", method = RequestMethod.POST)
    public String ajouterLivre(@ModelAttribute("nouveauLivre") Livre nouveauLivre) {
        // Utiliser le service pour ajouter le nouveau livre
        bibliothequeService.addLivre(nouveauLivre);
        // Rediriger vers la liste des livres
        return "redirect:/bibliotheque/listeLivres";
    }

    @RequestMapping(value = "/supprimerLivre/{isbn}", method = RequestMethod.GET)
    public String supprimerLivre(@PathVariable("isbn") String isbn) {
        // Utiliser le service pour supprimer le livre
        bibliothequeService.deleteLivre(isbn);
        // Rediriger vers la liste des livres
        return "redirect:/bibliotheque/listeLivres";
    }

    @RequestMapping(value = "/modifierLivre/{isbn}", method = RequestMethod.GET)
    public String afficherFormulaireModification(@PathVariable("isbn") String isbn, Model model) {
        // Utiliser le service pour obtenir le livre par ISBN
        Livre livreAModifier = bibliothequeService.getLivreByIsbn(isbn);

        if (livreAModifier != null) {
            // Ajouter le livre au modèle
            model.addAttribute("livreAModifier", livreAModifier);
            return "modifierLivre";
        } else {
            // Rediriger vers la liste des livres si le livre n'est pas trouvé
            return "redirect:/bibliotheque/listeLivres";
        }
    }

    @RequestMapping(value = "/modifierLivre/{isbn}", method = RequestMethod.POST)
    public String modifierLivre(@PathVariable("isbn") String isbn, @ModelAttribute("livreAModifier") Livre livreModifie) {
        // Utiliser le service pour mettre à jour le livre
        bibliothequeService.updateLivre(isbn, livreModifie);
        // Rediriger vers la liste des livres après la modification
        return "redirect:/bibliotheque/listeLivres";
    }

    @RequestMapping(value = "/rechercheLivres", method = RequestMethod.GET)
    public String afficherPageRecherche(Model model) {
        return "rechercheLivres";
    }

    @RequestMapping(value = "/rechercheLivres", method = RequestMethod.POST)
    public String rechercherLivres(@RequestParam(name = "titre", required = false) String titre,
                                   @RequestParam(name = "auteur", required = false) String auteur,
                                   Model model) {
        List<Livre> livresTrouves = bibliothequeService.rechercherLivres(titre, auteur);
        model.addAttribute("livres", livresTrouves);
        return "listeLivres";
    }
}
