package id.ac.umn.week11_33747;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<PostModel> data;
    RecyclerView rvPosts;
    PostDataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvPosts = (RecyclerView)findViewById(R.id.rvPosts);
        rvPosts = (RecyclerView)findViewById(R.id.rvPosts);
        PostServices postServices = DataRepository.create();
        postServices.getPosts().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if(response.isSuccessful()) {
// Log.i("MainActivity", "Get success.");
                    data = response.body();
/*
Log.d("MainActivity", "Received responses: " +
String.valueOf(data.size()));
for(PostModel row : data) {
Log.d("MainActivity", " " + row.getBody());
}
*/
                    adapter = new PostDataAdapter(getApplicationContext(), data);
                    rvPosts.setAdapter(adapter);
                    rvPosts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Failed to get posts. Unknown API error.", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
// Log.e("MainActivity", "Failed to get posts: " + t.getMessage());
                Toast.makeText(getApplicationContext(), "Failed to get posts. Check internet connection.", Toast.LENGTH_LONG).show();
            }
        });
    }

}