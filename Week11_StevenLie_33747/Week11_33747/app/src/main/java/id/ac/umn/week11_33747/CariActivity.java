package id.ac.umn.week11_33747;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CariActivity extends AppCompatActivity {
    EditText etNimCari, etNamaCari, etHasilCari;
    Button btnCari;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari);
        etNimCari = (EditText) findViewById(R.id.etNimCari);
        etNamaCari = (EditText) findViewById(R.id.etNamaCari);
        etHasilCari = (EditText) findViewById(R.id.etHasilCari);
        etHasilCari.setEnabled(false);
        btnCari = (Button) findViewById(R.id.btnCari);
        btnCari.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etHasilCari.setText("");
                String nim = etNimCari.getText().toString().trim();
                String nama = etNamaCari.getText().toString().trim();
                if(!nim.isEmpty() || !nama.isEmpty()){
                    String actinWebPage = MainActivity.server+"cari_mahasiswa.php?";
                    Uri builtURI = null;
                    if(!nim.isEmpty() && !nama.isEmpty()){
                        builtURI = Uri.parse(actinWebPage).buildUpon().appendQueryParameter("nim", nim).appendQueryParameter("nama", nama).build();
                    }else if(!nim.isEmpty() && nama.isEmpty()){
                        builtURI = Uri.parse(actinWebPage).buildUpon().appendQueryParameter("nim", nim).build();
                    }else if(nim.isEmpty() && !nama.isEmpty()){
                        builtURI = Uri.parse(actinWebPage).buildUpon().appendQueryParameter("nama", nama).build();
                    }
                }else{
                    etHasilCari.setText("NIM dan/atau NAMA harus ada yang diisi");
                }
            }
        });
    }
    class AsynCariMahasiswa extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection con = null;
            BufferedReader reader = null;
            String mhsJSONString = null;
            try{
                URL urlRequest = new URL(strings[0]);
                con = (HttpURLConnection) urlRequest.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                InputStream inputStream = con.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder builder = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null){
                    builder.append(line);
                    builder.append("\n");
                }if(builder.length() == 0){
                    return null;
                }
                mhsJSONString = builder.toString();
                JSONObject jsonObject = new JSONObject(mhsJSONString);
                String hasilText = "";
                int sukses = (int) jsonObject.getInt("success");
                if(sukses == 1){
                    JSONArray mhsJSONArray = (JSONArray) jsonObject.get("mahasiswas");
                    for(int i = 0; i < mhsJSONArray.length(); i++){
                        JSONObject mhsJSONobj = (JSONObject) mhsJSONArray.get(i);
                        String nim = mhsJSONobj.getString("nim");
                        String nama = mhsJSONobj.getString("nama");
                        String email = mhsJSONobj.getString("email");
                        String nHp = mhsJSONobj.getString("nomorHp");
                        hasilText += nim + " | " + nama + " | "+email+ " | "+nHp+ "\n";
                    }
                    return hasilText;
                }else{
                    String pesan = jsonObject.getString("message");
                    return pesan;
                }
            }catch (Exception e){
                Log.e("CARI_ACTIVITY", "Error = "+e.getMessage().toString());
                return null;
            }
            finally {
                if(con != null){
                    con.disconnect();
                }
                if(reader != null){
                    try{
                        reader.close();
                    }catch (IOException e){
                        Log.e("WEBSERVICE", "Exception: "+e.getMessage().toString());
                    }
                }
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            etHasilCari.setText(s);
        }
    }
}
