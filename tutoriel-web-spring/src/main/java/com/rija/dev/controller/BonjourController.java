package com.rija.dev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
// @RequestMapping("/bonjour") // Passage de donnée à la JSP "bonjour.jsp" et
// Récupération d'un paramètre de la requête HTTP
@RequestMapping("/bonjour/{personne}") // extraire le paramètre depuis l'URI
public class BonjourController {

    // Passage de donnée à la JSP "bonjour.jsp"
    // @RequestMapping(method = RequestMethod.GET)
    // public String afficherBonjour(final ModelMap pModel) {
    // pModel.addAttribute("personne", "Rija");
    // return "bonjour";
    // }

    // Récupération d'un paramètre de la requête HTTP :
    // http://localhost:8080/tutoriel-web-spring/bonjour?personne=Mirija
    // @RequestMapping(method = RequestMethod.GET)
    // public String afficherBonjour(final ModelMap pModel,
    // @RequestParam(value = "personne") final String pPersonne) {
    //
    // pModel.addAttribute("personne", pPersonne);
    // return "bonjour";
    // }

    // extraire le paramètre depuis l'URI : http://localhost:8080/tutoriel-web-spring/bonjour/Mirija
    @RequestMapping(method = RequestMethod.GET)
    public String afficherBonjour(final ModelMap pModel,
	    @PathVariable(value = "personne") final String pPersonne) {

	pModel.addAttribute("personne", pPersonne);
	return "bonjour";
    }

}
