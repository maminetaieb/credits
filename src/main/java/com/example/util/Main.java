package com.example.util;

import com.example.app.HelloApplication;
import com.example.entity.Client;
import com.example.service.ClientArticleService;
import com.example.service.ClientService;
import com.example.service.CreditService;
import com.example.service.UserService;

public class Main {
    public static Client selectedClient;
    public static void main(String[] args) {
        new UserService().initialize();
        new ClientService().initialize();
        new CreditService().initialize();
        new ClientArticleService().initialize();
        HelloApplication.main(args);
    }
}