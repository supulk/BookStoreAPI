/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supul.model;

/**
 *
 * @author Supul
 */
public class Book {
    private int id;
    private String title;
    private int authorId;
    private int ISBN;
    private int year;
    private double price;
    private int stock;
    
    public Book(){}

    public Book(String title, int authorId, int ISBN, int year, double price, int stock) {
        this.title = title;
        this.authorId = authorId;
        this.ISBN = ISBN;
        this.year = year;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Book{" + "title=" + title + ", author=" + authorId + ", ISBN=" + ISBN + ", year=" + year + ", price=" + price + ", stock=" + stock + '}';
    }
}
