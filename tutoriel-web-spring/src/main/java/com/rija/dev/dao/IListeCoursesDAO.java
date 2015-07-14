package com.rija.dev.dao;

import java.util.List;

import com.rija.dev.bean.Course;

public interface IListeCoursesDAO {
    List<Course> rechercherCourses();
    
    void creerCourse(final Course pCourse);
    
    void supprimerCourse(final Course pCourse);
    
    void modifierCourse(final Course pCourse);
}
