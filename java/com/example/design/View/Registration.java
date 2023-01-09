package com.example.design.View;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Design.R;
import com.example.design.View.Utils;

import java.util.ArrayList;


public class Registration extends AppCompatActivity {
    private final String[] Colors = {"BLACK", "WHITE", "RED", "BLUE"};
    private boolean[] selected = {false, false, false, false};
    Spinner spinnerU, spinnerD;
    ArrayList<String> namesArray = new ArrayList<>();
    EditText newUser;
    String p1Name, p2Name;
    String p1Color, p2Color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        namesArray.add("DEFAULT");
        spinner1();
        spinner2();
    }


    public void spinner1() {
        spinnerU = (Spinner) findViewById(R.id.spnr_u1);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, namesArray);
        spinnerU.setAdapter(adapter);
        spinnerU.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Registration.this, ""+adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                p1Name = ""+adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void spinner2() {
        spinnerD = (Spinner) findViewById(R.id.spnr_u2);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, namesArray);
        spinnerD.setAdapter(adapter);
        spinnerD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Registration.this, ""+adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                p2Name = ""+adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void onClickCB(View v) {
        CheckBox f1 = findViewById(R.id.cb_finish1);
        CheckBox f2 = findViewById(R.id.cb_finish2);
        CheckBox cb = (CheckBox) v;
        if (f1.isChecked() && f2.isChecked()) {
            Intent intent = new Intent(this, GameBoard.class);
            intent.putExtra("p1Name", p1Name);
            intent.putExtra("p2Name", p2Name);
            //intent.putExtra("p1Color", p1Color);
            startActivity(intent);
        }
    }

    public void onClickPC(View v) {
        Button button = (Button) v;
        showSingleChoiceColors();
    }

    private void showSingleChoiceColors() {
        Builder builder = new Builder(this);
        builder.setTitle("PICK YOUR COLOR");
        builder.setSingleChoiceItems(Colors, -1, new Registration.MyListener()); // see next slide for listener
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    private class MyListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int buttonPressed) {
            int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
            String show = Colors[selectedPosition];
            Toast.makeText(Registration.this, show, Toast.LENGTH_SHORT).show();
            selected[selectedPosition] = true;
            //p1Color = Colors[selectedPosition];
        }
    }

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

    public void createNewUserClick(View view){
        showCustomDialog();
    }

    public void showCustomDialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.create_new_user);
        newUser = dialog.findViewById(R.id.et_enter_name);
        Button finish = dialog.findViewById(R.id.btn_finish);
        CustomDialogClickListener dcl = new CustomDialogClickListener(dialog);
        finish.setOnClickListener(dcl);
        dialog.show();
    }

    private class CustomDialogClickListener implements View.OnClickListener {
        Dialog dialog;

        public CustomDialogClickListener(Dialog _dialog) {
            this.dialog = _dialog;
            dialog.dismiss();
        }

        @Override
        public void onClick(View v) {
            namesArray.add(newUser.getText().toString());
            dialog.dismiss();
        }
    }
}

