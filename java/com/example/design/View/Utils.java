package com.example.design.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.design.View.MainScreen;
import com.example.Design.R;

public class Utils {

    //Menu Handling - start
    public static boolean handleMenu(MenuItem item, Activity activity) {
        int resID = item.getItemId();
        switch (resID) {
            case R.id.btms:
                activity.startActivity(new Intent(activity, MainScreen.class));
                return true;

            case R.id.settings:
                new Utils().showSettingsDialog(activity);
                return true;

            case R.id.btn_rules:
                new Utils().showRulesDialog(activity);
                return true;

            case R.id.btn_statistics:
                new Utils().showStatisticsDialog(activity);
                return true;

            case R.id.sgs:
                Toast.makeText(activity, "SAVED", Toast.LENGTH_SHORT).show();
                activity.startActivity(new Intent(activity, MainScreen.class));
                return true;

            case R.id.quit:
                new Utils().showQuitDialog(activity);
                return true;

            default:
                return false;
        }
    }

    private class OnAlertDialogClickListener {
        public void onClick(DialogInterface dialog, int which, Activity activity) {
            if (which == dialog.BUTTON_POSITIVE) {
                dialog.dismiss();
                activity.finishAffinity();
            }
            if (which == dialog.BUTTON_NEGATIVE) {
                Toast.makeText(activity.getApplicationContext(), "Exit Canceled", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }  // if BUTTON_NEGATIVE
        }// onClick
    } // class OnAlertDialogClickListener

    private class CustomDialogClickListener implements View.OnClickListener {
        Dialog dialog;

        public CustomDialogClickListener(Dialog _dialog) {
            this.dialog = _dialog;
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.btn_okRls || id == R.id.btn_okStat || id == R.id.btn_okSet)
                dialog.dismiss();
            /*
            if (id == R.id.sound_oo) {
               if (View.OnClick)
                    Toast.makeText(Registration.this, "SOUND ON", Toast.LENGTH_SHORT).show();

                    Toast.makeText(Registration.this, "SOUND OFF", Toast.LENGTH_SHORT).show();
            }
            }*/
        }
    }


    //MENU - QUIT
    public void showQuitDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Are you sure?");
        builder.setTitle("Are you sure?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) new OnAlertDialogClickListener());
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) new OnAlertDialogClickListener());
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    //MENU - RULES
    private void showRulesDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.rules);
        Button ok = dialog.findViewById(R.id.btn_okRls);
        Utils.CustomDialogClickListener dcl = new Utils.CustomDialogClickListener(dialog);
        ok.setOnClickListener(dcl);
        dialog.show();
    }

    //MENU - STATISTICS
    private void showStatisticsDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.statistics);
        Button ok = dialog.findViewById(R.id.btn_okStat);
        Utils.CustomDialogClickListener dcl = new Utils.CustomDialogClickListener(dialog);
        ok.setOnClickListener(dcl);
        dialog.show();
    }


    private void showSettingsDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.options);
        Switch sound = dialog.findViewById(R.id.swc_sound);
        Button ok = dialog.findViewById(R.id.btn_okSet);
        Utils.CustomDialogClickListener dcl = new Utils.CustomDialogClickListener(dialog);
        ok.setOnClickListener(dcl);
        sound.setOnClickListener(dcl);
        dialog.show();
    }
    //Menu Handling - finish
}
