package com.rija.dev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rija.dev.bean.Course;
import com.rija.dev.dao.IListeCoursesDAO;

@Service
public class ListeCoursesService implements IListeCoursesService {

    @Autowired
    private IListeCoursesDAO dao;

    @Transactional(readOnly=true)
    public List<Course> rechercherCourses() {
        return dao.rechercherCourses();
    }
}
