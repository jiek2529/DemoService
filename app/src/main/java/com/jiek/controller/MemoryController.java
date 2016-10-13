package com.jiek.controller;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jiek on 10/12/16.
 */

public class MemoryController {
    private final String TAG = "MemoryController";

    public void logmemory() {
        Runtime run = Runtime.getRuntime();
//        long max = run.maxMemory();
        long total = run.totalMemory();
        long free = run.freeMemory();
//        long usable = max - total + free;
//        Log.e(this.getClass().getSimpleName(), c.getName()+" ::: 最大内存 = " + max + "\t 已分配内存 = " + total + "\t 已分配内存中的剩余空间 = " + free + "\t 最大可用内存 = " + usable +"  >> 使用"+((total-free)/1024)+"k ");
        Log.w(TAG, " 已分配内存 = " + (total / 1024) + "k  >> 使用" + ((total - free) / 1024) + "k ");
        /*if (total > 8000000 && free / ((double) total) > 0.5) {
            System.gc();
            Log.w(TAG, "内存回收");
        }*/
    }

    /**
     * @param period minimum is 500
     */
    public void ttMemory(long period) {
        if (period < 500)
            period = 500;
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                logmemory();
            }
        };
        t.schedule(tt, 500, period);

        int i = 1;
        Person person = new Person(i, 1);
        loopCount(person, 30);
        Log.e(TAG, "last loopCount: " + person);

        Integer c = 1000;
        c = count(c);//赋不赋值 结果都是1000
        Log.e(TAG, "ttMemory: " + c);
    }

    private Integer count(Integer c) {
        c += 100;
        return c;
    }

    class Person {
        int count, base;

        public Person(int count, int base) {
            this.count = count;
            this.base = base;
        }

        public Person fibonacci() {
            int tmp = count;
            count += base;
            base = tmp;
            return this;
        }

        void a(int i) {
        }

        int a(String i) {
            return 1;
        }


        @Override
        public String toString() {
            return count + "   " + base;
        }
    }

    private Person loopCount(Person p, int loopindex) {
        if (loopindex > 0) {
            Log.e(TAG, "loopCount: " + p + "  " + loopindex);
            p = loopCount(p.fibonacci(), --loopindex);
        }
        return p;
    }

    // 获得可用的内存
    public long getmem_UNUSED(Context mContext) {
        long MEM_UNUSED;
        // 得到ActivityManager
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        // 创建ActivityManager.MemoryInfo对象

        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);

        // 取得剩余的内存空间

        MEM_UNUSED = mi.availMem / 1024;
        return MEM_UNUSED;
    }

    // 获得总内存
    public long getmem_TOLAL() {
        long mTotal;
        // /proc/meminfo读出的内核信息进行解释
        String path = "/proc/meminfo";
        String content = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path), 8);
            String line;
            if ((line = br.readLine()) != null) {
                content = line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // beginIndex
        int begin = content.indexOf(':');
        // endIndex
        int end = content.indexOf('k');
        // 截取字符串信息

        content = content.substring(begin + 1, end).trim();
        mTotal = Integer.parseInt(content);
        return mTotal;
    }

//    static MemoryController instant;

//    public static MemoryController getInstant() {
////        if (instant == null) {
////            instant = new MemoryController();
////        }
////        return instant;
//        return new MemoryController();
//    }
}
