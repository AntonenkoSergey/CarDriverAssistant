package com.example.cardriverassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    ImageView green;//перша картинка
    ImageView yellow;//друга картинка
    ImageView red;//третя картинка
    LoadingTask lt;//об`явленя класу для роботи з фоновим потоком
    private static final String TAG = "MyLog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ініцілізація візуальних об`єктів
        setContentView(R.layout.activity_main);
        green = (ImageView) findViewById(R.id.green);
        yellow = (ImageView) findViewById(R.id.yellow);
        red = (ImageView) findViewById(R.id.red);
        //виклик фонового потоку
        lt = new LoadingTask();
        lt.execute();
    }
//---1------------------------------------------------------------------------------------------------
    private class LoadingTask extends  AsyncTask<Void,Integer,Void>{
        //завантаження стартових данних
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
            green.setVisibility(View.INVISIBLE);
            yellow.setVisibility(View.INVISIBLE);
            red.setVisibility(View.INVISIBLE);
        }
        // Основна робота спотоком
        @Override
        protected Void doInBackground(Void... params) {
            int n= 0;
            for (int i = 0; i < 4; i++) {
                try {
                    publishProgress(n++);//передача проміжкових данних
                    TimeUnit.SECONDS.sleep(1);//атримка в сикундах
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        //робота з проміжковими данними
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            Log.d(TAG,values[0].toString());// ликористання логу
            if (values[0]==1){
                green.setVisibility(View.VISIBLE);
            }
            if (values[0]==2){
                yellow.setVisibility(View.VISIBLE);
            }
            if (values[0]==3){
                red.setVisibility(View.VISIBLE);
            }
        }
        // результат роботі потоку
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //виклик нового активіті
            startActivity(new Intent(getApplicationContext(), Authorization.class));
            finish();
        }
    }
}
//----------------------1---------------------------------------------------------------------

