package com.example.design.Model;

import com.example.design.Controller.Player;

import java.util.ArrayList;

public abstract class Piece {
    private Player player;
    private Position position;

    public Piece(Player player, Position position){}
    public Piece(Piece piece){}

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Player getPlayer() {
        return player;
    }

    public abstract boolean isAQueen();
    public abstract void execMove(Position to);
    public abstract void execEat(Position eaten);
    public abstract void draw(Position position);


}
