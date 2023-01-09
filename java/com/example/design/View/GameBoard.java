package com.example.design.View;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Design.R;
import com.example.design.Controller.GameManager;

public class GameBoard extends AppCompatActivity {
    TextView players, commandBar;
    String p1Name, p2Name, p1Color, p2Color, commands;
    ImageView [][] board;
    GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);
        p1Name = "DEFAULT";
        p2Name = "DEFAULT";
        //p1Color = "WHITE";
        //p2Color = "BLACK";
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            p1Name = extras.getString("p1Name").concat("'s TURN");
            p2Name = extras.getString("p2Name").concat("'s TURN");
            //p1Color = extras.getString(p1Color);
        }
        //p1tv = p1Name.concat(p1Color);
        players = (TextView) findViewById(R.id.tv_playerXturn);
        players.setText(p1Name);
        commandBar = (TextView) findViewById(R.id.tv_commands);
        commandBar.setText(commands);
        initBoard();
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }

    public void initBoard() {
        int mRows = 8;
        int mCols = 8;
        board = new ImageView[mRows][mCols];
        for(int iRow = 0; iRow < mRows; iRow++){
            for (int iCol = 0; iCol < mCols; iCol++){
                  String rowCol = "square_".concat(Integer.toString(iRow).concat(Integer.toString(iCol)));
                  int resId = getResources().getIdentifier(rowCol, "id", getPackageName());
                  board[iRow][iCol] = findViewById(resId);
            }
        }
    }

    //todo: function: click on board which finds what imageview was clicked and returns its position


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (Utils.handleMenu(item, this))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
}