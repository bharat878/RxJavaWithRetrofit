package com.example.rxjavawithretrofit.Retrofit;

import com.example.rxjavawithretrofit.model.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface MyApi {

    @GET("posts")
    Observable<List<Post>> getPosts();
}
