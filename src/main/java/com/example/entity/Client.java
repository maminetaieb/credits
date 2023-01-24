package com.example.entity;

import com.example.service.CreditService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Observable;


public class Client extends Observable {
    private Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    private SimpleStringProperty fullName;
    public String getFullName() {
        return fullName.get();
    }

    public SimpleStringProperty fullNameProperty() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    private SimpleStringProperty phoneNumber;
    public String getPhoneNumber() {
        return phoneNumber.get();
    }
    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public double getTotalCredits() {
        return new CreditService().findByClientId(this.getId(), null, null)
                .stream().mapToDouble(credit -> credit.getAmount()).sum();
    }
    public SimpleDoubleProperty totalCreditsProperty() {
        return new SimpleDoubleProperty(this.getTotalCredits());
    }

    public Client(Integer id, String fullName, String phoneNumber) {
        this.id = id;
        this.fullName = new SimpleStringProperty(fullName);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }
}
