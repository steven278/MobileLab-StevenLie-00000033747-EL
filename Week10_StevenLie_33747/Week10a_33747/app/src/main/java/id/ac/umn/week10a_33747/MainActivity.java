package id.ac.umn.week10a_33747;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TEXT_STATE = "currentText";
    private TextView mTextView;
    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView1);
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setMax(100);
        if(savedInstanceState != null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }
    public void startTask(View view){
        mTextView.setText("Siap untuk mulai");
        new AsyncTaskSederhana().execute((int) (Math.random()*50)+10);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }
    private class AsyncTaskSederhana extends AsyncTask<Integer, Integer, String>{
        @Override
        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.setProgress(0);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            int n = integers[0].intValue();
            int s = 0;
            try{
                s = n * 200;
                for(int i = 0; i < n; i++){
                    Thread.sleep(200);
                    publishProgress(((int)((100 * i)/(float) n)));
                }
            }catch (InterruptedException e ){
                e.printStackTrace();
            }
            return "Aktif lagi setelah tidur selama "+ s + " milidetik";
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            mTextView.setText("Progress = "+progress[0]+ " persen");
            mProgressBar.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            mTextView.setText(result);
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }
}