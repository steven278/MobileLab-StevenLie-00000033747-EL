package id.ac.umn.week11_33747;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DetilActivity extends AppCompatActivity {
    private EditText etNim, etNama, etEmail, etNomorHP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil);
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etNomorHP = findViewById(R.id.etNomorHp);
    }
    public void simpanData(View view){}
    public void batal(View view){}
}
