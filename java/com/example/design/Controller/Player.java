package com.example.design.Controller;

public class Player {
    private User user;
    private String color;

    public Player(User user, String color){
        this.user = user;
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
