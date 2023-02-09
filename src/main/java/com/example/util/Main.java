package com.example.util;

import com.example.app.HelloApplication;
import com.example.entity.Client;
import com.example.service.*;

import java.text.DecimalFormat;

public class Main {
    public static Client selectedClient;
    public static DecimalFormat df = new DecimalFormat("#");
    public static void main(String[] args) {
        df.setMaximumFractionDigits(3);
        new UserService().initialize();
        new ClientService().initialize();
        new CreditService().initialize();
        new ArticleService().initialize();
        new ClientArticleService().initialize();
        HelloApplication.main(args);
    }
}