package com.example.circleview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;

public class MainActivity extends Activity {

    Thread t;
    Clock clock;
    int i = 0;
    private boolean running = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clock = (Clock) findViewById(R.id.clock);

        t = new Thread(new Runnable() {

            @Override
            public void run() {
                while (running) {
                    try {
                        myHandler.sendEmptyMessage(1);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("e======>" + e.toString());
                    }

                }
            }
        });
        t.start();
    }

    Handler myHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            clock.invalidate();
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        if (t != null && t.isAlive()) {
            running = false;
            t.interrupt();
            t = null;
        }
        super.onDestroy();
    }

}
