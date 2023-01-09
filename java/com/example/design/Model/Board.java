package com.example.design.Model;

import com.example.design.Controller.Player;

import java.util.ArrayList;

public class Board {
    private Piece[][] board = new Piece[8][8];
    private int remainingPieces[] = {16, 16}; //index 0 -> p1, index 1 -> p2
    private Player[] players = new Player[2];//index 0 -> p1, index 2 ->p2
    private int turn; // 0 -> p1 / 1 -> p2

    public Board(){
        this.turn = 1;
        for (int row = 1; row <= 2; row++){
            for (int col = 0; col < 8; col++){
                this.board[row][col] = new Pawn(players[0], new Position(row, col));
            }
        }
        for (int row = 6; row <= 7; row++){
            for (int col = 0; col < 8; col++){
                this.board[row][col] = new Pawn(players[1], new Position(row, col));
            }
        }

    }

    public Piece[][] getBoard() {
        return board;
    }

    public int getTurn() {
        return turn;
    }
    public void changeTurn() {
        if (turn == 0) {
            turn = 1;
            return;
        }
        else
            turn = 0;
        return;
    }

    public int getCurrPlayerRemainingPieces() {
        return remainingPieces[turn];
    }

    public int winner(){
        if (remainingPieces[0] == 0)
                return 1;
        else if (remainingPieces[1] == 0)
                return 0;
        else
            return -1; //no win
    }

    public boolean checkDraw(){
        if (remainingPieces[0] == remainingPieces[1] && remainingPieces[0] == 1) {
            return true;
        }
        return false;
    }

    //if there eats return those, else return any other moves
    public ArrayList<Position> legalPositions(Piece selected){
        ArrayList<Position> positions = new ArrayList<Position>();
        positions = (this.legalEats(selected).isEmpty() ? this.legalMoves(selected) : this.legalEats(selected));
        return positions;
    }
    //in game manager check if piece in given position isSelectable and if so, use legalPositions with selected piece as param

    public ArrayList<Position> legalMoves(Piece selected){
        ArrayList<Position> temp = new ArrayList<Position>();
        ArrayList<Position> positions = new ArrayList<Position>();

        if(selected instanceof Pawn) {
            if (isCellEmpty(new Position(selected.getPosition().getRow() + 1, selected.getPosition().getCol()))){
                temp.add(new Position(selected.getPosition().getRow() + 1, selected.getPosition().getCol()));
            }
            if (isCellEmpty(new Position(selected.getPosition().getRow(), selected.getPosition().getCol() + 1))){
                temp.add(new Position(selected.getPosition().getRow(), selected.getPosition().getCol() + 1));
            }
            if (isCellEmpty(new Position(selected.getPosition().getRow(), selected.getPosition().getCol() - 1))){
                temp.add(new Position(selected.getPosition().getRow(), selected.getPosition().getCol() - 1));
            }
        }

        else if (selected instanceof Queen) {
            int row = selected.getPosition().getRow();
            int col = selected.getPosition().getCol();
            int up = row + 1, down = row - 1, right = col + 1, left = col -1;
            while (up <= 7 && this.isCellEmpty(new Position(up, col))) {
                temp.add(new Position(up, col));
                up++;
            }
            while (down >= 0 && this.isCellEmpty(new Position(down, col))) {
                temp.add(new Position(down, col));
                down--;
            }
            while (right <= 7 && this.isCellEmpty(new Position(row, right))) {
                temp.add(new Position(row, right));
                right++;
            }
            while (left >= 0 && this.isCellEmpty(new Position(row, left))) {
                temp.add(new Position(row, left));
                left--;
            }
        }

        for (int i = 0; i < temp.size(); i++){
            if (temp.get(i).getRow() <= 7 && temp.get(i).getRow() >= 0 && temp.get(i).getCol() >= 7 && temp.get(i).getCol() <= 0) {
                positions.add(temp.get(i));
            }
        }

        return positions;
    }

    public ArrayList<Position> legalEats(Piece selected){
        ArrayList<Position> temp= new ArrayList<Position>();   //arrayList that contains the legal eats
        ArrayList<Position> positions = new ArrayList<Position>();

        if(selected instanceof Pawn) {
            if((isCellEmpty(new Position(selected.getPosition().getRow() + 2, selected.getPosition().getCol())) && areRivals(selected, this.board[selected.getPosition().getRow() + 1][selected.getPosition().getCol()])))
                temp.add(new Position(selected.getPosition().getRow() + 1, selected.getPosition().getCol()));
            if((isCellEmpty(new Position(selected.getPosition().getRow(), selected.getPosition().getCol() + 2)) && areRivals(selected, this.board[selected.getPosition().getRow()][selected.getPosition().getCol() + 1])))
                temp.add(new Position(selected.getPosition().getRow(), selected.getPosition().getCol() + 1));
            if((isCellEmpty(new Position(selected.getPosition().getRow(), selected.getPosition().getCol() - 2)) && areRivals(selected, this.board[selected.getPosition().getRow()][selected.getPosition().getCol() - 1])))
                temp.add(new Position(selected.getPosition().getRow(), selected.getPosition().getCol() - 1));
        }

        else if (selected instanceof Queen) {
            int row = selected.getPosition().getRow();
            int col = selected.getPosition().getCol();
            int up = row + 1, down = row - 1, right = col + 1, left = col -1;
            while (up <= 7) {
                if(areRivals(selected, this.board[up][col]) && isCellEmpty(new Position(up + 1, col))) {
                    temp.add(new Position(up, col)); //adds position of eaten piece, needs to move to position in isCellEmpty (+1/-1)
                    break;
                }
                up++;
            }
            while (down >= 0) {
                if(areRivals(selected, this.board[down][col]) && isCellEmpty(new Position(down - 1, col))) {
                    temp.add(new Position(down, col));
                    break;
                }
                down--;
            }
            while (right <= 7) {
                if(areRivals(selected, this.board[row][right]) && isCellEmpty(new Position(row, right + 1))) {
                    temp.add(new Position(row, right));
                    break;
                }
                right++;
            }
            while (left >= 0) {
                if(areRivals(selected, this.board[row][left]) && isCellEmpty(new Position(row, left - 1))) {
                    temp.add(new Position(row, left));
                    break;
                }
                left--;
            }
        }

        for (int i = 0; i < temp.size(); i++){
            if (temp.get(i).getRow() <= 7 && temp.get(i).getRow() >= 0 && temp.get(i).getCol() >= 7 && temp.get(i).getCol() <= 0) {
                positions.add(temp.get(i));
            }
        }
        return positions;
    }

    public void makeQueen(Pawn pawn){
        this.board[pawn.getPosition().getRow()][pawn.getPosition().getCol()] = new Queen(pawn);
    }

    public void removePiece(Piece piece){
        this.board[piece.getPosition().getRow()][piece.getPosition().getCol()] = null;
        if (piece.getPlayer() == this.players[0])
            this.remainingPieces[0]--;
        else if (piece.getPlayer() == this.players[1])
            this.remainingPieces[1]--;
        piece = null;
    }

    public Piece getPiece(Position position){
        return this.board[position.getRow()][position.getCol()];
    }

    public boolean isPieceSelectable(Piece piece){
        if ((piece.getPlayer()== this.players[turn]) && (canEat(piece) || (noneCanEat() && canMove(piece)))) {
            return true;
        }
        return false;
    }

    public boolean noneCanEat(){
        for (Piece pArray[] : this.board) {
            for (Piece piece : pArray) {
                if ((piece != null) && (piece.getPlayer() == this.players[turn] && canEat(piece))){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean noneCanMove(){
        for (Piece pArray[] : this.board) {
            for (Piece piece : pArray) {
                if ((piece != null) && (piece.getPlayer() == this.players[turn] && canMove(piece))){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean areRivals(Piece selected, Piece piece){
        if(selected.getPlayer() != piece.getPlayer())
            return true;
        return false;
    }

    private boolean isCellEmpty(Position position){
        if (this.board[position.getRow()][position.getCol()] == null)
            return true;
        return false;
    }


    private boolean canMove(Piece selected){
        return !(this.legalMoves(selected).isEmpty());
    }

    private boolean canEat(Piece selected){
        return !(this.legalEats(selected).isEmpty());
    }

}
