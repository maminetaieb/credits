package com.example.entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Article {
    private Integer id;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private SimpleStringProperty title;
    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    private SimpleDoubleProperty defaultPrice;
    public double getDefaultPrice() {
        return defaultPrice.get();
    }

    public SimpleDoubleProperty defaultPriceProperty() {
        return defaultPrice;
    }

    public void setDefaultPrice(double defaultPrice) {
        this.defaultPrice.set(defaultPrice);
    }

    public Article(Integer id, String title, Double defaultPrice) {
        this.id = id;
        this.title = new SimpleStringProperty(title);
        this.defaultPrice = new SimpleDoubleProperty(defaultPrice);
    }
}
