package com.example.justin.test2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {

    private TextView txtTest;
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;

    private Button rem0;
    private Button rem1;
    private Button rem2;
    private Button rem3;
    private Button rem4;
    private Button rem5;

    private Button next;

    private ListPopupWindow errorlist;

    private ArrayDeque<String> errors;
    private ArrayDeque<String> updatedErrors;

    String test[] = {"I wanna be","The very best","Like no one ever was","To catch them all","Is my real test","To train them is my cause"};

    private Timer timer;
    private TimerTask timerTask;
    private Handler textHandler;
    private Handler listHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTest = (TextView) findViewById(R.id.txtListError);
        btn0 = (Button) findViewById(R.id.btnAdd0);
        btn1 = (Button) findViewById(R.id.btnAdd1);
        btn2 = (Button) findViewById(R.id.btnAdd2);
        btn3 = (Button) findViewById(R.id.btnAdd3);
        btn4 = (Button) findViewById(R.id.btnAdd4);
        btn5 = (Button) findViewById(R.id.btnAdd5);

        rem0 = (Button) findViewById(R.id.btnRem0);
        rem1 = (Button) findViewById(R.id.btnRem1);
        rem2 = (Button) findViewById(R.id.btnRem2);
        rem3 = (Button) findViewById(R.id.btnRem3);
        rem4 = (Button) findViewById(R.id.btnRem4);
        rem5 = (Button) findViewById(R.id.btnRem5);

        next = (Button) findViewById(R.id.btnNext);

        errors = new ArrayDeque<>();
        updatedErrors = new ArrayDeque<>(errors);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = listHandler.obtainMessage(0,test[0]);
                listHandler.sendMessage(msg);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = listHandler.obtainMessage(0,test[1]);
                listHandler.sendMessage(msg);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = listHandler.obtainMessage(0,test[2]);
                listHandler.sendMessage(msg);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = listHandler.obtainMessage(0,test[3]);
                listHandler.sendMessage(msg);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = listHandler.obtainMessage(0,test[4]);
                listHandler.sendMessage(msg);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = listHandler.obtainMessage(0,test[5]);
                listHandler.sendMessage(msg);
            }
        });

        rem0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = listHandler.obtainMessage(1,test[0]);
                listHandler.sendMessage(msg);
            }
        });
        rem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = listHandler.obtainMessage(1,test[1]);
                listHandler.sendMessage(msg);
            }
        });
        rem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = listHandler.obtainMessage(1,test[2]);
                listHandler.sendMessage(msg);
            }
        });
        rem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = listHandler.obtainMessage(1,test[3]);
                listHandler.sendMessage(msg);
            }
        });
        rem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = listHandler.obtainMessage(1,test[4]);
                listHandler.sendMessage(msg);
            }
        });
        rem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = listHandler.obtainMessage(1,test[5]);
                listHandler.sendMessage(msg);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this,TestActivity.class);
                a.putExtra("test",test);
                startActivity(a);
            }
        });

        txtTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow(txtTest);
            }
        });

        timer = new Timer();
        timerTask = new TimerTask() {
            Iterator i = updatedErrors.iterator();

            @Override
            public void run() {
                Message msg;
                if (!i.hasNext()) {
                    updatedErrors = new ArrayDeque<>(errors);
                    i = updatedErrors.iterator();
                }
                if (i.hasNext() & !updatedErrors.isEmpty()) {
                    msg = textHandler.obtainMessage(1, i.next());
                    textHandler.sendMessage(msg);
                }
                else if (updatedErrors.isEmpty()) {
                    msg = textHandler.obtainMessage(0, "Hello World!");
                    textHandler.sendMessage(msg);
                }
            }
        };
        timer.schedule(timerTask, 1000, 1000);

        textHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                txtTest.setText(inputMessage.obj.toString());
            }
        };

        listHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                String text = (String) msg.obj;
                if (msg.what == 0) {
                    if (errors.contains(text)) {
                        errors.remove(text);
                    }
                    errors.add(text);
                }
                else {
                    if (errors.contains(text)) {
                        errors.remove(text);
                    }
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void popupWindow(TextView anchor) {
        try {
            errorlist = new ListPopupWindow(this);
            errorlist.setWidth(anchor.getWidth());
            errorlist.setHeight(ListPopupWindow.WRAP_CONTENT);
            errorlist.setAnchorView(anchor);

            errorlist.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.listview, updatedErrors.toArray()));
            errorlist.show();
            //errorlist.setOnItemClickListener(new AdapterView.OnItemClickListener);
        } catch (Exception e) {
            Log.d("List error", "Error creating popup list");
        }

    }
}
