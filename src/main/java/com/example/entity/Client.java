package com.example.entity;

import com.example.service.CreditService;

public class Client {
    private Integer id;
    private String fullName;
    private String phoneNumber;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getTotalCredits() {
        return new CreditService().findByClientId(this.getId(), null, null)
                .stream().mapToDouble(credit -> credit.getAmount()).sum();
    }

    public Client(Integer id, String fullName, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }
}
