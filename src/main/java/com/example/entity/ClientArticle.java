package com.example.entity;

import javafx.beans.property.SimpleDoubleProperty;

public class ClientArticle {
    private Integer clientId;
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    private Integer articleId;
    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    private SimpleDoubleProperty customPrice;
    public double getCustomPrice() {
        return customPrice.get();
    }

    public SimpleDoubleProperty customPriceProperty() {
        return customPrice;
    }

    public void setCustomPrice(double customPrice) {
        this.customPrice.set(customPrice);
    }

    public ClientArticle(Integer clientId, Integer articleId, Double customPrice) {
        this.clientId = clientId;
        this.articleId = articleId;
        this.customPrice = new SimpleDoubleProperty(customPrice);
    }
}
