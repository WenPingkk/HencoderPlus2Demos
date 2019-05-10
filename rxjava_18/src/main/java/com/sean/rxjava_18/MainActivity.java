package com.sean.rxjava_18;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sean.rxjava_18.api.Api;
import com.sean.rxjava_18.model.Repo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }

    private void SingleDemo() {
        api.getRepos("Sean")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Repo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Repo> repos) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

//    private void downLoad() {
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                emitter.onNext("");
//            }
//        }).map(new Function<String, Bitmap>() {
//            @Override
//            public Bitmap apply(String s) throws Exception {
//
//                Bitmap bitmap = getMap(s);
//                return bitmap;
//            }
//        }).observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new Consumer<Bitmap>() {
//            @Override
//            public void accept(Bitmap bitmap) throws Exception {
//                ImageView imageView = new ImageView(MainActivity.this);
//                imageView.setImageBitmap(bitmap);
//            }
//        });
//    }

//    private Bitmap getMap(String s) {
//        return new Bitmap();
//    }

    private void operator() {
        Single
                .just(1)
        .map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return String.valueOf(integer);
            }
        }).delay(2, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * interval 设计线程切换
     * 有延迟,
     */
    private void interval() {
        Observable.interval(2,TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
