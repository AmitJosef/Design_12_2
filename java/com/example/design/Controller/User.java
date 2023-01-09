package com.example.design.Controller;

public class User {
        private String name;
        private String password;
        private int wins;
        private int losses;
        private int draws;
        private double winsPercent;

        public User(String name, String password) {
            this.name = name;
            this.password = password;
            this.wins = 0;
            this.losses = 0;
            this.draws = 0;
            this.winsPercent = calcWinsPercent();
        }

        private double calcWinsPercent() {
            return (this.wins / (this.wins + this.losses + this.draws)) * 100;
        }
}

