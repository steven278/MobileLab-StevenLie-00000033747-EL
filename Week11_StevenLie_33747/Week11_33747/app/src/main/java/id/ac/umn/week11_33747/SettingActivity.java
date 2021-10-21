package id.ac.umn.week11_33747;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingActivity extends AppCompatActivity {
    private EditText etServer;
    private Button btnSimpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        etServer = (EditText) findViewById(R.id.httpServer);
        etServer.setText(MainActivity.server);
        btnSimpan = (Button) findViewById(R.id.btnSimpanSetting);
        btnSimpan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MainActivity.server = etServer.getText().toString();
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}