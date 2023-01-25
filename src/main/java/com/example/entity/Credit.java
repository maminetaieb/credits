package com.example.entity;

import com.example.service.ClientService;

import java.util.Date;

public class Credit {
    private Integer id;
    private Integer clientId;
    private Double amount;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Credit(Integer id, Integer clientId, Double amount, Date date) {
        this.id = id;
        this.clientId = clientId;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return new ClientService().findOne(this.getClientId()).getFullName() + (amount > 0 ? "credit " : "paiement ") + Math.abs(amount) + " a " + date;
    }
}
