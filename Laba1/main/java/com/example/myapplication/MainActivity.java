package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(new DrawView(this));
    }

    static class DrawView extends View {
        private final Paint redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private final Paint bluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        public DrawView(Context context) {
            super(context);
            redPaint.setColor(Color.RED);
            bluePaint.setColor(Color.BLUE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int width = getWidth();
            int height = getHeight();


            float radius = Math.min(width / 2f, height / 2f);


            float centerXRed = width * 0.25f;
            float centerY = height / 2f;


            float centerXBlue = width * 0.75f;


            canvas.drawCircle(centerXRed, centerY, radius, redPaint);


            canvas.drawCircle(centerXBlue, centerY, radius, bluePaint);
        }
    }
}