package com.example.design.Model;
import com.example.design.Controller.Player;

import java.util.ArrayList;
public class Queen extends Piece {

    public Queen(Player player, Position position){
        super(player, position);
    }
    public Queen(Piece piece){
        super(piece);
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
    public boolean isAQueen(){return true;}

}
