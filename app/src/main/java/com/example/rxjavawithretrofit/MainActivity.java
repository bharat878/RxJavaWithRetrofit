package com.example.rxjavawithretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.rxjavawithretrofit.Adapter.PostAdapter;
import com.example.rxjavawithretrofit.Retrofit.MyApi;
import com.example.rxjavawithretrofit.Retrofit.RetrofitClient;
import com.example.rxjavawithretrofit.model.Post;

import java.util.List;
import io.reactivex.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    MyApi myApi;
    RecyclerView recycler_posts;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    List<Post> mPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = RetrofitClient.getInstance();
        myApi = retrofit.create(MyApi.class);

        recycler_posts = findViewById(R.id.recyclerView);
        recycler_posts.setHasFixedSize(true);
        recycler_posts.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    @SuppressLint("CheckResult")
    private void fetchData() {

//        myApi.getPosts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<Post>>() {
//                    @Override
//                    public void accept(List<Post> posts) throws Exception {
//                        displayData(posts);
//                    }
//                });

        compositeDisposable.add(myApi.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> posts) throws Exception {
                        displayData(posts);
                    }
                }));
    }

    private void displayData(List<Post> posts) {
        PostAdapter postAdapter = new PostAdapter(this, posts);
        recycler_posts.setAdapter(postAdapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
