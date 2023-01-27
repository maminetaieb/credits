package com.example.entity;

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

    private Double customPrice;
    public Double getCustomPrice() {
        return customPrice;
    }

    public void setCustomPrice(Double customPrice) {
        this.customPrice = customPrice;
    }

    public ClientArticle(Integer clientId, Integer articleId, Double customPrice) {
        this.clientId = clientId;
        this.articleId = articleId;
        this.customPrice = customPrice;
    }
}
