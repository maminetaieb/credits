package com.example.util;

import com.example.app.HelloApplication;
import com.example.entity.User;
import com.example.service.ClientService;
import com.example.service.CreditService;
import com.example.service.UserService;

public class Main {
    public static void main(String[] args) {
        new UserService().initialize();
        new ClientService().initialize();
        new CreditService().initialize();
        new UserService().insert(new User("firas", "firas"));
        HelloApplication.main(args);
    }
}