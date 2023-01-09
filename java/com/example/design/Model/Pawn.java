package com.example.design.Model;
import com.example.design.Controller.Player;

import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(Player player, Position position) {
        super(player, position);
    }

    @Override
    public void execMove(Position to) {
        this.setPosition(to);
    }

    @Override
    public void execEat(Position eaten) {

    }

    @Override
    public void draw(Position position) {

    }

    @Override
    public boolean isAQueen(){return false;}
}
