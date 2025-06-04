package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "PrimeSumCalculator";
    private Timer timer;
    private int currentNumber = 0;
    private int primeSum = 0;
    private final int MAX_NUMBER = 100;
    private final int STEP_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startPrimeCalculation();
    }

    private void startPrimeCalculation() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (currentNumber <= MAX_NUMBER) {
                    if (isPrime(currentNumber)) {
                        primeSum += currentNumber;
                        Log.i(TAG, "Число " + currentNumber + " простое. Текущая сумма: " + primeSum);
                    }
                    currentNumber++;
                } else {
                    Log.i(TAG, "Итоговая сумма простых чисел: " + primeSum);
                    timer.cancel();
                }
            }
        }, 0, STEP_DELAY);
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}