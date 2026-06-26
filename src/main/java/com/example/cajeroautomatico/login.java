package com.example.cajeroautomatico;


import jakarta.inject.Named;
import java.io.Serializable;

@Named("loginBean")

public class login implements Serializable {

    private String username ;
    private String password;


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {}

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {}

    }
