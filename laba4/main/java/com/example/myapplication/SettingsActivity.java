import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {
    private int selectedColor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RadioGroup colorGroup = findViewById(R.id.colorGroup);
        Button btnSave = findViewById(R.id.btnSave);

        colorGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.colorDefault) {
                selectedColor = Color.WHITE;
            } else if (checkedId == R.id.colorBlue) {
                selectedColor = Color.CYAN;
            } else if (checkedId == R.id.colorGreen) {
                selectedColor = Color.GREEN;
            }
        });

        btnSave.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("backgroundColor", selectedColor);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}