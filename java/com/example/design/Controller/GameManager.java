package com.example.design.Controller;

import com.example.design.Model.Board;
import com.example.design.Model.Pawn;
import com.example.design.Model.Piece;
import com.example.design.Model.Position;
import com.example.design.View.GameBoard;

import java.util.ArrayList;

public class GameManager {
    public GameBoard damaUI;
    private Board board;
    private Player[] players = new Player[2];
    Action action;

    public GameManager(GameBoard damaUI){
        this.damaUI = damaUI;
        initGame();
    }

    public void initGame(){
        this.board = new Board();
        damaUI.initBoard();
    }

    public Piece selectPiece(int row, int col){
        if (this.board.isPieceSelectable(this.board.getPiece(new Position(row, col)))){
            //need to change photo into selected piece one, than show markPossibleMoves
            return this.board.getPiece(new Position(row, col));
        }
        else {
            updateCommandBar("THIS PIECE CAN'T BE SELECTED.");
            return null;
        }
    }

    public void cancelPieceSelection(Piece wasSelected, int row, int col){
        //somehow with double click or with click on another player
    }

    public void selectedPieceMove(Piece piece, Position position){
        ArrayList<Position> possibilities = this.board.legalPositions(piece);
        for (int i = 0; i < possibilities.size(); i++) {
            if (possibilities.get(i).equals(position)) {
                piece.execMove(position);
                //move it in game board
                if((piece.getPlayer().getColor() == "white" && position.getRow() == 7) || (piece.getPlayer().getColor() == "black" && position.getRow() == 7) && piece instanceof Pawn) {
                    board.makeQueen((Pawn)piece);
                    //change to queen in board
                }
            }
        }
    }

    public void updateBoard(){

    }

    //?
    public void click(int row, int col){

    }

    public void markPossibleMoves(Piece piece){
        ArrayList<Position> possibilities = this.board.legalPositions(piece);
        //here mark in the visible board the possible moves (change photos) using indexes from possibilities
    }//marks eats, and if there are no eats than marks moves -> use legalPositions

    public void updateCommandBar(String message){
        this.damaUI.setCommands(message);
    }//if must eat for example, tell the players in textTv under the board


}


/*
in mark possible moves:
    if (id == wee) -> w_optional
    if (id == bee) -> b_optional
 */