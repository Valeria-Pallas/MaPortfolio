package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.models.Project;
import com.example.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = {"/listProjects"}, method = RequestMethod.GET)
    public String listeProjects(Model model) {
        // Utiliser le service pour obtenir la liste des projets
        model.addAttribute("projects", projectService.getAllProjects());
        return "listProjects";
    }

    @RequestMapping(value = "/addProject", method = RequestMethod.GET)
    public String afficherFormulaireAjout(Model model) {
        // Afficher le formulaire d'ajout de projet
        model.addAttribute("addProject", new Project());
        return "formAddProject";
    }

    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public String ajouterProject(@ModelAttribute("addProject") Project addProject) {
        // Utiliser le service pour ajouter le nouveau projet
        projectService.addProject(addProject);
        // Rediriger vers la liste des projets
        return "redirect:/project/listeProjects";
    }

    @RequestMapping(value = "/deleteProject/{id}", method = RequestMethod.GET)
    public String supprimerProject(@PathVariable("id") Integer id) {
        // Utiliser le service pour supprimer le projet
        projectService.deleteProjectById(id);
        // Rediriger vers la liste des projets
        return "redirect:/project/listProjects";
    }

    @RequestMapping(value = "/modifyProject/{isbn}", method = RequestMethod.GET)
    public String afficherFormulaireModification(@PathVariable("id") Integer id, Model model) {
        // Utiliser le service pour obtenir le project par ISBN
        Project ProjectToModify = projectService.getProjectById(id);

        if (ProjectToModify != null) {
            // Ajouter le project au modèle
            model.addAttribute("ProjectToModify", ProjectToModify);
            return "modifiedProject";
        } else {
            // Rediriger vers la liste des projects si le livre n'est pas trouvé
            return "redirect:/project/listProjects";
        }
    }

    @RequestMapping(value = "/modifiedProject/{id}", method = RequestMethod.POST)
    public String modifierProject(@PathVariable("id") Integer id, @ModelAttribute("ProjectToModify") Project projectModified) {
        // Utiliser le service pour mettre à jour le projet
        projectService.updateProjectById(id, projectModified);
        // Rediriger vers la liste des projects après la modification
        return "redirect:/project/listeProjects";
    }

    @RequestMapping(value = "/rechercheProjects", method = RequestMethod.GET)
    public String afficherPageRecherche(Model model) {
        return "rechercheProjects";
    }


}
