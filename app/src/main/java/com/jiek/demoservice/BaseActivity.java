package com.jiek.demoservice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by jiek on 9/30/16.
 *
 * this found
 */
public abstract class BaseActivity extends Activity {
    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        d("onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        d("onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        d("onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        d("onResume: ");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        d("onPostResume: ");
    }

    @Override
    public void onStateNotSaved() {
        super.onStateNotSaved();
        d("onStateNotSaved: ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        d("onRestoreInstanceState: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        d("onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        d("onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        d("onDestroy: ");
    }

    void d(String msg) {
        Log.d(getClass().getSimpleName() + " --> " + getLocalClassName(), msg);
    }
}
