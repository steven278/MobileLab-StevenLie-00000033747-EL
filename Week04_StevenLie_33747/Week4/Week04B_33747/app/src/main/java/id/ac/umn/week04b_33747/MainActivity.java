package id.ac.umn.week04b_33747;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button_change_1, button_change_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_change_1 = findViewById(R.id.main_button_change_1);
        button_change_2 = findViewById(R.id.main_button_change_2);

        button_change_1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intentFirst = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intentFirst);
            }
        });
        button_change_2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intentSecond = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intentSecond);
            }
        });
    }
}