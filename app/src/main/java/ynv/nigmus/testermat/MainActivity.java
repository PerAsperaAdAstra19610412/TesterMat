package ynv.nigmus.testermat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import Database.FeEn;

public class MainActivity extends AppCompatActivity {

    //private static final long START_TIME_IN_MILLIS = 600000;
    private static final long TIME_8_MIN_IN_MILLIS = 480000;
    private RecyclerView recyclerView;
    //
    private EditText editTextInput;
    private TextView textViewTimeMove;
    private Button buttStart;
    private Button buttStat;
    private Button buttSetting;
    private Button buttStop;
    private ConstraintLayout constraintLayoutTimeMove;

    //private static int second = 0;
    //
    private static CountDownTimer countUpTimer;
    //private static long mTimeInMillis = TIME_8_MIN_IN_MILLIS;
    private static String strTimer;
    private static long forwardTimeInMillis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Log.d("log", FeEn.SQL_CREATE_ENTRIES);


        //--> Недаёт пользователю делать скриншёт
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        //<--

        //-->
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();// Скрывает ActionBar()
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// Портретная орентация
        //<--

        //-->
        //SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //String str = pref.getString("edit_num_min_preference","2");
        //Log.d("Log!","loadsetting -> " + str);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Matematica.setRandMin(pref.getString("edit_num_min_pref","2"));
        Matematica.setRandMax(pref.getString("edit_num_max_pref","8"));

        //<--


        //-->
        editTextInput = findViewById(R.id.editTextNumber);
        buttStart = findViewById(R.id.buttonStart);
        buttStat = findViewById(R.id.buttonStat);
        buttSetting = findViewById(R.id.buttonSetting);
        buttStop = findViewById(R.id.buttonStop);
        textViewTimeMove = findViewById(R.id.textViewTimeMove);
        constraintLayoutTimeMove = findViewById(R.id.constraintLayTimer);
        //<--


        //--> Таймер
        countUpTimer = new CountDownTimer(TIME_8_MIN_IN_MILLIS,1000) {
            @Override
            public void onTick(long backTimeInMillis) {
                forwardTimeInMillis = TIME_8_MIN_IN_MILLIS - backTimeInMillis;
                Matematica.setTimeInMillis(forwardTimeInMillis);
                strTimer = String.format(Locale.getDefault(),"%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(forwardTimeInMillis),
                        TimeUnit.MILLISECONDS.toSeconds(forwardTimeInMillis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(forwardTimeInMillis)));
                textViewTimeMove.setText(strTimer);
            }

            @Override
            public void onFinish() {
                countUpTimer.cancel();
            }
        }.start();
        //<--


        //RecyclerViev Ответы**
        ArrayList<ResultRecyclerVievItem> arrListResuRecViItems = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.Adapter adapter = new RecyclerViewAdapter(arrListResuRecViItems);//
        //LinearLayoutManager adapter = new RecyclerViewAdapter(arrListResuRecViItems);//
        //private RecyclerView.LayoutManager layoutManager;
        /* В чём разница между LinearLayoutManager и RecyclerView.LayoutManager
        в LinerLa... можно перевернуть список.
        */
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //-> Чтобы перевернуть список работает только с LinearLayoutManager
        //layoutManager.setReverseLayout(true);
        //layoutManager.setStackFromEnd(true);
        //<-
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);


        //Обработка ввода c виртуальной клавиатуры.->
        editTextInput.setOnKeyListener((view, keyCode, event) -> {

            if(event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER)){
                //При нажатии ентер чило вводится проверяется, и записывается.
                Matematica.setСheck(editTextInput.getText().toString());// Чтение ввода и проверка результата.

                arrListResuRecViItems.add(new ResultRecyclerVievItem(Matematica.getYESorNO(),
                                                                 Matematica.getResult() ,strTimer));
                editTextInput.getText().clear();//Очистить поле ввода.
                Matematica.getNewNumber();
                editTextInput.setHint(Matematica.getxandy());//Показывает новую задачу
                countUpTimer.start();
                recyclerView.scrollToPosition(0);// работает только RecyclerView.LayoutManager

                //--> Важная настройка без неё клавиатура при вводе меняет тип.
                //android:digits="0123456789"
                //<--
                return true;
            }
            if (Matematica.setСheck(editTextInput.getText().toString())){
                //--> Обработка событий с клавиатуры андройд, если пользователь ввёл правельное
                // число оно вводится автоматически
                //<-- чтобы не нужно было вводить. |<- Enter
                arrListResuRecViItems.add(new ResultRecyclerVievItem(Matematica.getYESorNO(),
                                                                 Matematica.getResult() ,strTimer));
                editTextInput.getText().clear();//Очистить поле ввода.
                Matematica.getNewNumber();
                editTextInput.setHint(Matematica.getxandy());//Показывает новую задачу, эта функция должна быть в конце.
                countUpTimer.start();
                recyclerView.scrollToPosition(0);// работает только RecyclerView.LayoutManager
            }
            return false;
        });//<-Обработка ввода c клавиатуры андройд.

        //START
        buttStart.setOnClickListener(view -> {
            editTextInput.getText().clear();
            Matematica.getNewNumber();
            editTextInput.setHint(Matematica.getxandy());//Показывает новую задачу, эта функция должна быть в конце.
            countUpTimer.start();
            //Toast.makeText(getApplicationContext(),"Click START",Toast.LENGTH_SHORT).show();
            editTextInput.setEnabled(true);//Включить ввод
            buttSetting.setEnabled(false);
            buttStat.setEnabled(false);
            buttStart.setVisibility(View.INVISIBLE);
            buttStop.setVisibility(View.VISIBLE);
            constraintLayoutTimeMove.setVisibility(View.VISIBLE);

        });

        //STOP
        buttStop.setOnClickListener(view -> {
            editTextInput.setText("0");
            countUpTimer.cancel();
            Matematica.setCountYES(0);
            //Matematica.setRandMin();
            //Matematica.setRandMax();
            editTextInput.setEnabled(false);//ВЫКЛ. ввод

            //Toast.makeText(getApplicationContext(),"Click STOP",Toast.LENGTH_SHORT).show();
            buttStart.setVisibility(View.VISIBLE);
            buttStop.setVisibility(View.INVISIBLE);
            constraintLayoutTimeMove.setVisibility(View.INVISIBLE);
            buttSetting.setEnabled(true);
            buttStat.setEnabled(true);

        });

        buttSetting.setOnClickListener(view -> {
            Log.d("Log","-->");
            Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(intent);
        });

        buttStat.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.d("log","-> onPause");
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
        //Log.d("log","-> onDestroy()");
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Matematica.setRandMin(pref.getString("edit_num_min_pref","2"));
        Matematica.setRandMax(pref.getString("edit_num_max_pref","8"));
        //Log.d("log!","-> onPostResume()");
    }
}
//Toast.makeText(getApplicationContext(),"Click STOP",Toast.LENGTH_SHORT).show();