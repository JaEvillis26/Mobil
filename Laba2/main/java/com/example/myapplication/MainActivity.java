package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        Toast.makeText(this, "Нажата кнопка", Toast.LENGTH_SHORT).show();
    }

    public void onChangeTextClick(View view) {
        Button button2 = findViewById(R.id.button2);
        button2.setText("Иванов");
    }
}