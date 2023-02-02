package com.example.entity;

import com.example.service.ClientService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Credit {
    private Integer id;
    private Integer clientId;
    private Double amount;
    private Date date;
    private String message;

    public Credit(Integer id, Integer clientId, Double amount, String message,Date date) {
        this.id = id;
        this.clientId = clientId;
        this.amount = amount;

        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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


    @Override
    public String toString() {
        return new ClientService().findOne(this.getClientId()).getFullName() + (amount > 0 ? " a pris un credit de " : " a payé ") + Math.abs(amount) + " DT " +getMessage()+ " le " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date.getTime());
    }
}
