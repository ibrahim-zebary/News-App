package com.example.ibrahim.retrofitguardian;

import android.support.design.widget.Snackbar;
import android.view.View;

public class Helper {

    public static void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
}
