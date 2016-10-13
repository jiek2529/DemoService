package com.jiek.controller;

import android.util.Log;

/**
 * Created by jiek on 10/12/16.
 */

public class SyntaxController {
    private final String TAG = "SyntaxController";

    /**
     * 测试值与引用的区别,在Integer的差异
     */
    public void testInteger() {
        Integer i1, i2, i3, i4;
        i1 = 100;
        i2 = 100;
        i3 = 150;
        i4 = 150;
        Log.e(TAG, "testInteger: " + (i1 == i2));
        Log.e(TAG, "testInteger: " + (i3 == i4));

        int _i1, _i2, _i3, _i4;
        _i1 = 100;
        _i2 = 100;
        _i3 = 150;
        _i4 = 150;
        Log.e(TAG, "test int: " + (_i1 == _i2));
        Log.e(TAG, "test int: " + (_i3 == _i4));
        new MemoryController().ttMemory(500);
    }
}
