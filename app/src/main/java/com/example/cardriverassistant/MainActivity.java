package com.example.cardriverassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView green;//перша картинка
    ImageView yellow;//друга картинка
    ImageView red;//третя картинка
    LoadingTask lt;//об`явленя класу для роботи з фоновим потоком
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

    //клас для роботи з фоновим потоком
    private class LoadingTask extends AsyncTask<Void, Integer, Integer> {

        //завантаження стартових данних
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            green.setVisibility(View.INVISIBLE);
            green.setVisibility(View.INVISIBLE);
            green.setVisibility(View.INVISIBLE);

        }

        // Основна робота спотоком
        @Override
        protected Integer doInBackground(Void... params) {
            int result = 12;
            try {
                int counter = 0;

                for (int i = 0; i < loading0.length; i++) {
                    if (i != 3) {
                        getFloor(counter);
                        //передача проміжкових данних
                        publishProgress(++counter);
                    } else {
                        if (!isOnline()) {
                            result = 1;
                            break;
                        }

                    }
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //передача кінечних данних
            return result;
        }

        // результат роботі потоку
        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            if (result == 1) {
                imageView.setImageResource(R.drawable.no_wifi);
                mInfoTextView.setText(R.string.erroe_wifi);
                mProgressBar.setProgress(0);
                faButton.setVisibility(View.VISIBLE);
            } else {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();

            }
        }

        //робота з проміжковими данними
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            int n = values[0] - 1;
            mInfoTextView.setText(loading0[n]);
            mProgressBar.setProgress(values[0]);

        }

        private void getFloor(int floor) throws InterruptedException {
            TimeUnit.SECONDS.sleep(2);
        }
    }
    //----------------------1---------------------------------------------------------------------

}