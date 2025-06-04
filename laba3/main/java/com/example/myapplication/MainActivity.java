package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BookDbHelper dbHelper;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new BookDbHelper(this);
        container = findViewById(R.id.container);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> showAddDialog());

        refreshBookList();
    }

    private void refreshBookList() {
        container.removeAllViews();
        ArrayList<Book> books = dbHelper.getAllBooks();

        for (Book book : books) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);

            TextView tvId = new TextView(this);
            tvId.setText(String.valueOf(book.id));
            tvId.setWidth(100);

            TextView tvTitle = new TextView(this);
            tvTitle.setText(book.title);
            tvTitle.setWidth(200);

            TextView tvAuthor = new TextView(this);
            tvAuthor.setText(book.author);
            tvAuthor.setWidth(200);

            TextView tvPrice = new TextView(this);
            tvPrice.setText(String.format("%.2f", book.price));
            tvPrice.setWidth(100);

            row.addView(tvId);
            row.addView(tvTitle);
            row.addView(tvAuthor);
            row.addView(tvPrice);

            row.setOnClickListener(v -> showEditDialog(book));
            row.setOnLongClickListener(v -> {
                dbHelper.deleteBook(book.id);
                refreshBookList();
                return true;
            });

            container.addView(row);
        }
    }

    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Добавить книгу");

        View view = getLayoutInflater().inflate(R.layout.dialog_book, null);
        EditText etTitle = view.findViewById(R.id.etTitle);
        EditText etAuthor = view.findViewById(R.id.etAuthor);
        EditText etPrice = view.findViewById(R.id.etPrice);

        builder.setView(view);
        builder.setPositiveButton("Добавить", (dialog, which) -> {
            String title = etTitle.getText().toString();
            String author = etAuthor.getText().toString();
            double price = Double.parseDouble(etPrice.getText().toString());
            dbHelper.insertBook(title, author, price);
            refreshBookList();
        });
        builder.setNegativeButton("Отмена", null);
        builder.show();
    }

    private void showEditDialog(Book book) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Редактировать книгу");

        View view = getLayoutInflater().inflate(R.layout.dialog_book, null);
        EditText etTitle = view.findViewById(R.id.etTitle);
        EditText etAuthor = view.findViewById(R.id.etAuthor);
        EditText etPrice = view.findViewById(R.id.etPrice);

        etTitle.setText(book.title);
        etAuthor.setText(book.author);
        etPrice.setText(String.valueOf(book.price));

        builder.setView(view);
        builder.setPositiveButton("Сохранить", (dialog, which) -> {
            String newTitle = etTitle.getText().toString();
            String newAuthor = etAuthor.getText().toString();
            double newPrice = Double.parseDouble(etPrice.getText().toString());
            dbHelper.updateBook(book.id, newTitle, newAuthor, newPrice);
            refreshBookList();
        });
        builder.setNegativeButton("Отмена", null);
        builder.show();
    }
}