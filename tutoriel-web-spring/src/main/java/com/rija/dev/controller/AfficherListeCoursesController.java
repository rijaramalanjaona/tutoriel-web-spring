package com.rija.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rija.dev.bean.Course;
import com.rija.dev.services.IListeCoursesService;

@Controller
@RequestMapping(value="/afficherListeCourses")
public class AfficherListeCoursesController {

    @Autowired
    private IListeCoursesService service;

    @RequestMapping(method = RequestMethod.GET)
    public String afficher(ModelMap pModel) {
        final List<Course> lListeCourses = service.rechercherCourses();
        pModel.addAttribute("listeCourses", lListeCourses);
        return "listeCourses";
    }
}
