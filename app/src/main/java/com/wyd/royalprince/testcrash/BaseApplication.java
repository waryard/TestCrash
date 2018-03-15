package com.wyd.royalprince.testcrash;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wyd on 2018/2/13.
 */

public class BaseApplication extends Application implements Thread.UncaughtExceptionHandler {

    @Override
    public void onCreate() {
        super.onCreate();
        //设置异常捕获
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(final Thread thread, final Throwable throwable) {
        //当有异常产生时执行该方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                //模拟网络上传
                printExceptionInfo(thread, throwable);

            }
        }).start();

            //杀死进程（两种方法都可以），或者进行其他操作
            System.exit(0);//关闭当前
//        android.os.Process.killProcess(android.os.Process.myPid());//杀死进程

    }

    /**
     * 打印异常信息
     */
    private void printExceptionInfo(Thread thread, Throwable ex) {

        //获取当前时间
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));

        try {
            //打印手机信息和异常信息
            Log.e("---打印开始--------->", "---------------------->");
            PackageManager pm = this.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(this.getPackageName(), PackageManager.GET_ACTIVITIES);
            System.out.println("发生异常时间：" + time);
            System.out.println("应用版本：" + pi.versionName);
            System.out.println("应用版本号：" + pi.versionCode);
            System.out.println("android版本号：" + Build.VERSION.RELEASE);
            System.out.println("android版本号API：" + Build.VERSION.SDK_INT);
            System.out.println("手机制造商:" + Build.MANUFACTURER);
            System.out.println("手机型号：" + Build.MODEL);
            Log.e("TAG_APP", "current Thread" + Thread.currentThread() + "---thread" + thread.getId() + "ex" + ex.toString());
            Log.e("---打印结束--------->", "---------------------->");
            Toast.makeText(BaseApplication.this, "打印完毕", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e("---打印结束--------->", "PackageInfo出现崩溃：" + e);
        }

    }
}
