package com.example.util;

import com.example.app.HelloApplication;
import com.example.service.ClientService;
import com.example.service.CreditService;

public class Main {
    public static void main(String[] args) {
        new ClientService().initialize();
        new CreditService().initialize();
        HelloApplication.main(args);
    }
}