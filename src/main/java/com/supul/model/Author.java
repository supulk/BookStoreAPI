/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supul.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Supul
 */
public class Author {
    private int id;
    private String name;
    private String biography;
    
    public Author(){}

    public Author(String name, String biography) {
        this.name = name;
        this.biography = biography;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Override
    public String toString() {
        return "Author{" + "name=" + name + ", biography=" + biography + '}';
    }
    
    
}
