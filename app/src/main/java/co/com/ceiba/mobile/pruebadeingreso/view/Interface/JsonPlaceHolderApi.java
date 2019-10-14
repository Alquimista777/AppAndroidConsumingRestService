package co.com.ceiba.mobile.pruebadeingreso.view.Interface;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.view.Model.Posts;
import co.com.ceiba.mobile.pruebadeingreso.view.Model.Users;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("users")
    Call<List<Users>> getUsers();

    @GET("posts")
    Call<List<Posts>> getPost();
}
