//package com.app.cryptotunnel.nutriplan.nutricalculator;
//
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.support.v4.app.DialogFragment;
//
///**
// * Created by codephillip on 9/7/15.
// */
//public class DialogBox extends DialogFragment{
//    String title;
//    String message;
//
//    public DialogBox() {
//    }
//
//    public DialogBox(String title, String message) {
//        this.title = title;
//        this.message = message;
//    }
//
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        // Use the Builder class for convenient dialog construction
//
//        // Creating alert Dialog with one Button
//
//        AlertDialog alertDialog = new AlertDialog.Builder(
//                getActivity()).create();
//
//        // Setting Dialog Title
//        alertDialog.setTitle("Alert Dialog");
//
//        // Setting Dialog Message
//        alertDialog.setMessage("Welcome to AndroidHive.info");
//
////                // Setting Icon to Dialog
////                alertDialog.setIcon(R.drawable.);
//
//        // Setting OK Button
//        alertDialog.setButton("OK",
//                new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog,
//                                        int which) {
////                        // Write your code here to execute after dialog
////                        // closed
////                        Toast.makeText(getApplicationContext(),
////                                "You clicked on OK", Toast.LENGTH_SHORT)
////                                .show();
//                    }
//                });
//
//        // Showing Alert Message
//        alertDialog.show();
//
//        return null;
//    }
//
//    public void showDialog(){
//        // Creating alert Dialog with one Button
//
//        AlertDialog alertDialog = new AlertDialog.Builder(
//                getActivity()).create();
//
//        // Setting Dialog Title
//        alertDialog.setTitle("Alert Dialog");
//
//        // Setting Dialog Message
//        alertDialog.setMessage("Welcome to AndroidHive.info");
//
////                // Setting Icon to Dialog
////                alertDialog.setIcon(R.drawable.);
//
//        // Setting OK Button
//        alertDialog.setButton("OK",
//                new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog,
//                                        int which) {
////                        // Write your code here to execute after dialog
////                        // closed
////                        Toast.makeText(getApplicationContext(),
////                                "You clicked on OK", Toast.LENGTH_SHORT)
////                                .show();
//                    }
//                });
//
//        // Showing Alert Message
//        alertDialog.show();
//
//    }
//
//}
