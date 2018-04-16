package com.example.scs.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.scs.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class RXActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        tv = (TextView) findViewById(R.id.tv_show);
    }


    public void rxbtn(View view) {
    }


    public void create() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(1);
                e.onNext(1);
                e.onComplete();
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "String " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    public void rxzip() {
        Observable.zip(Observable.just(1, 2, 3), Observable.just(4, 5, 6, 1, 2, 3), new BiFunction<Integer, Integer, Object>() {
            @Override
            public Object apply(Integer integer, Integer integer2) throws Exception {
                return null;
            }
        }).distinct().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });

    }

    public void rxconcat() {
        Observable.concat(Observable.just(1, 2, 3, 4, 5), Observable.just(4, 5, 6, 7, 8, 9, 10)).distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void rxflatmap() {
        Observable.just(1, 2, 3).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<String>();
                if (integer == 2) {
                    list.add("1");
                    list.add("1");
                    list.add("1");
                    return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
                } else return null;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    public void rxfilter() {
        Observable.just(1, 2, 3, 4, 5, 6, 7).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                if (integer > 3) return true;
                else
                    return false;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    public void rxbuffer() {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .buffer(3, 1)//count:每组的数量  skip:剔除的数量(往后挪) 123/234/345/456/567/67/7
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(@NonNull List<Integer> integers) throws Exception {
                    }
                });
    }
}
