package com.rija.dev.services;

import java.util.List;

import com.rija.dev.bean.Course;

public interface IListeCoursesService {
    List<Course> rechercherCourses();
    
    void creerCourse(final String pLibelle, final Integer pQuantite);
}
