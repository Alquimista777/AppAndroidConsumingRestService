package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.view.Interface.JsonPlaceHolderApi;
import co.com.ceiba.mobile.pruebadeingreso.view.Model.Posts;
import co.com.ceiba.mobile.pruebadeingreso.view.Model.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    private RecyclerView myRecyclerView;
    private EditText mJsonTxtView;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRecyclerView = findViewById(R.id.recyclerViewSearchResults);
       /* mJsonTxtView = findViewById(R.id.mJsonTxtView);*/
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        getPosts();
    }

    public void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Users>> userCall = jsonPlaceHolderApi.getUsers();
        Call<List<Posts>> call = jsonPlaceHolderApi.getPost();

        userCall.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if(!response.isSuccessful()){
                   /* mJsonTxtView.setText("Codigo: " + response.code());*/
                    return;
                }

                List<Users> usersList = response.body();
                MyAdapter myAdapter = new MyAdapter(usersList);
                myRecyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                /*  mJsonTxtView.setText(t.getMessage());*/
            }
        });

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(!response.isSuccessful()){
                   /* mJsonTxtView.setText("Codigo: " + response.code());*/
                    return;
                }

                List<Posts> postList = response.body();

               /* for(Posts post: postList){
                    String content = "";
                    content += "userId:"+ post.getUserId() + "\n";
                    content += "id:"+ post.getId() + "\n";
                    content += "title"+ post.getTitle() + "\n";
                    content += "body:"+ post.getBody() + "\n\n";
                    mJsonTxtView.append(content);
                }*/
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
               /* mJsonTxtView.setText(t.getMessage());*/
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}