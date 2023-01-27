package com.example.util;

import com.example.app.HelloApplication;
import com.example.entity.Client;
import com.example.service.*;

public class Main {
    public static Client selectedClient;
    public static void main(String[] args) {
        new UserService().initialize();
        new ClientService().initialize();
        new CreditService().initialize();
        new ArticleService().initialize();
        new ClientArticleService().initialize();
        HelloApplication.main(args);
    }
}