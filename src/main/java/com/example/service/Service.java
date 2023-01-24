package com.example.service;

import com.example.util.MyConnection;
import java.sql.Connection;
import java.util.List;

public interface Service <T> {
    Connection connection = MyConnection.getInstance().getConnection();
    Boolean insert(T instance);
    List<T> find(Integer offset, Integer rowCount);
    Boolean modify(T instance);
    Boolean delete(T instance);
}