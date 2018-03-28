package com.example.scs.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.scs.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.Observable.create;

public class RXTextActivity extends AppCompatActivity {
    static final private String TAG = "RXTextActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxtext);
    }

    public void demo() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1");
            }
        }).flatMap(new Function<String, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(String s) throws Exception {
                if (s.equals("1")) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(1);
                    list.add(2);
                    list.add(3);
                    list.add(4);
                    list.add(3);
                    list.add(3);
                    return Observable.fromIterable(list).delay(1000, TimeUnit.MILLISECONDS);
                } else
                    return null;
            }
        }).distinct().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer + "  ------------");
            }
        });

    }

    /**
     * 监听者有两种类型　Consumer，Observer　后者可以获取Disposable对象　去停止监听
     * map 转换Observale的值
     * zip 合并需要传入两个Observale 合并的数量以最少的为主　将会产生新的数据类型
     * concat 批量请求 Observale.concat(Observale.just(T)，Observale.just(T))
     * flatmap 将一个监听者转为多个 无序
     * -----flatmap(   return Observale.fromIterable())
     * concatmap 将一个监听者转为多个　有序
     * -----concatMap(  return Observale.fromIterable())
     * distinct 去掉重复的请求
     * just 请求多个数据可以直接 Observale.just(T,T,T)
     * filter 过滤Observale　reture false 过滤　反之保留
     * buffer 将Obervable　进行分组输出
     * timer  interval
     * ------前者执行一次延时操作
     * ------后者　执行多次　有间隔的延时操作
     * doOnNext 这方法在　被监听者发送数据后　监听者监听数据前　触发，可进行写缓存处理
     * skip　可以理解为从第几个开始处理　接收到的数据　Obverable.just(1,2,3,4,5,6).skip(2)--->3,4,5,6
     * take　最多接收多少个 Obverable.just(1,2,3,4,5,6).take(2)--->1,2  takeLast 后面几项
     * Single 正如名字一样　只能处理一个数据
     * debounce 过滤调某一时间内的数据
     * defer 当有订阅者的时候，会生产最新的被订阅者，里面的数据可以是最新的
     * last 获取被监听者发送的最后一个数据
     * merge 也是用于合并数据　数据可以交替合并　前提是监听者是在不同的线程中，同一线程还是有序的
     * reduce 一个操作运算符　seed 是一个初始值　如Oberable.just(1,2,3)--->reduce(2, return a+b) -->2+1=3+2=5+3=8
     * scan 和reduce　一样　只不过会显示过程　而不是直接显示个值
     * window
     * <p>
     * subscribeOn 切换被监听者(obserable)的线程　多次操作去第一次为主　之后都无效  observeOn　切换监听者(observer)的线程　多次切换有效
     */

    public void baserx(View view) {
        demo();
    }

    public void createTest() {
        create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                Log.i(TAG, "Observable emit 1" + "\n");
                e.onNext(1);
                Log.i(TAG, "Observable emit 2" + "\n");
                e.onNext(2);
                Log.i(TAG, "Observable emit 3" + "\n");
                e.onNext(3);
                e.onComplete();
                Log.i(TAG, "Observable emit 4" + "\n");
                e.onNext(4);
            }
        }).map(new Function<Integer, String>() {//进行类型转换
            @Override
            public String apply(Integer integer) throws Exception {
                return integer + " String";
            }
        }).subscribe(new Observer<String>() {
            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "onSubscribe : " + d.isDisposed() + "\n");
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull String integer) {
                Log.i(TAG, "onNext : value : " + integer + "\n");
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                    Log.i(TAG, "onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "onError : value : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete" + "\n");
            }
        });

    }

    public void zipText() {//合并事件　以最少的那组为主 合并成新的类型
        Observable.zip(create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                if (!e.isDisposed()) {
                    Log.e(TAG, "Integer emit : 1 \n");
                    e.onNext(1);
                    Log.e(TAG, "Integer emit : 2 \n");
                    e.onNext(2);
                    Log.e(TAG, "Integer emit : 3 \n");
                    e.onNext(3);
                    Log.e(TAG, "Integer emit : 4 \n");
                    e.onNext(4);
                    Log.e(TAG, "Integer emit : 5 \n");
                    e.onNext(5);
                }
            }
        }), create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                if (!e.isDisposed()) {
                    Log.e(TAG, "String emit : A \n");
                    e.onNext("A");
                    Log.e(TAG, "String emit : B \n");
                    e.onNext("B");
                    Log.e(TAG, "String emit : C \n");
                    e.onNext("C");
                }

            }
        }), new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return "Integer " + integer + " String " + s;
            }
        }).subscribe(
//                new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//            }
//        }

                new Observer<String>() {
                    int i = 0;
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(String s) {
                        i++;
                        if (i == 2)
                            disposable.dispose();
                        Log.e(TAG, "zip : accept : " + s + "\n");

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );
    }

    public void concat() {//批量请求
        Observable.concat(Observable.just(1, 2, 3), Observable.just(4, 5, 6, 1, 2, 3))
                .distinct()//去重复
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "concat : Integer " + integer + "\n");
                    }
                });

//        Observable.concat(Observable.just("1", "2", "3"), Observable.just("4", "5", "6"))
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        Log.e(TAG, "concat : String " + s + "\n");
//
//                    }
//                });
    }

    public void flatMap() {//将一组数据转化为多组数据　转换后的数据　无序的
        create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                if (integer == 2)
                    return Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                            e.onNext("asdasd");
                            e.onComplete();
                        }
                    });
                else
                    return null;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "flatMap : accept : " + s + "\n");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
//                Log.e(TAG, "flatMap : accept : " + throwable.toString());
            }
        });
    }

    public void concatMap() {//将一组数据转化成多组数据　有序
        create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < 3; i++) {
                    list.add("concatmap type = " + integer + " value = " + i);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "concatMap : accept : " + s + "\n");
            }
        });
    }

    public void filter() {
        Observable.just(11, 12, 15, 20, 13, 68, 54, 22).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                if (integer > 20)
                    return true;//保留
                else
                    return false;//过滤
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, "filter accept = " + integer);
            }
        });
    }

    public void buffer() {//将数据分组输出　
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .buffer(3, 1)//count:每组的数量  skip:剔除的数量(往后挪)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(@NonNull List<Integer> integers) throws Exception {
                        Log.i(TAG, "buffer size : " + integers.size() + "\n");
                        Log.i(TAG, "buffer value : ");
                        for (Integer i : integers) {
                            Log.i(TAG, i + "");
                        }
                        Log.i(TAG, "\n");
                    }
                });
    }

    Disposable timerDisposable = null;

    public void timer() {
        Log.i(TAG, "timer start");
        Observable.timer(2, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i(TAG, "timer accept = " + aLong);
                    }
                });

        Log.e(TAG, "interval start ");
        timerDisposable = Observable.interval(3, 2, TimeUnit.SECONDS)//第一次延时3s 第二次延时2s
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // 由于interval默认在新线程，所以我们应该切回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Log.i(TAG, "interval accept = " + aLong);
                    }
                });
    }

    public void doOnNext() {
        create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("123");
            }
        }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "可进行缓存操作　value = " + s);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "doOnNext value = " + s);
            }
        });
    }

    public void skip() {
        Observable.just(0, 1, 2, 3, 4).skip(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "skip accept = " + integer);
                    }
                });
    }

    public void take() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).take(5)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "take accept = " + integer);
                    }
                });
    }

    public void single() {
        Single.just("123")
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        Log.i(TAG, "single onSuccess = " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "single onError = " + e.toString());
                    }
                });
    }

    public void debounce() {
        create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1");
                Thread.sleep(200);
                e.onNext("2");
                Thread.sleep(499);
                e.onNext("3");
                Thread.sleep(501);
                e.onNext("4");
                Thread.sleep(520);
                e.onNext("5");
                Thread.sleep(522);
                e.onComplete();
            }
        }).debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG, "debounce time 500 accept = " + s);
                    }
                });
    }

    public void defer() {
        Observable observable = Observable.defer(new Callable<ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> call() throws Exception {
                return Observable.just(System.currentTimeMillis());
            }
        });
        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.i(TAG, "defer long = " + aLong);
            }
        });
        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.i(TAG, "defer long = " + aLong);
            }
        });

        Observable observable1 = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> e) throws Exception {
                e.onNext(System.currentTimeMillis());
            }
        });
        observable1.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.i(TAG, "create long = " + aLong);
            }
        });
        observable1.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.i(TAG, "create long = " + aLong);
            }
        });
    }

    public void last() {
        Observable.just("1", "2", "3")
                .last("0")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String integer) throws Exception {
                        Log.i(TAG, "last : " + integer + "\n");
                    }
                });
    }

    public void merge() {
        Observable.merge(create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("a");
                Thread.sleep(500);
                e.onNext("b");
                Thread.sleep(100);
                e.onNext("c");
            }
        }).subscribeOn(Schedulers.newThread()), create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("A");
                e.onNext("B");
                e.onNext("C");
            }
        }).subscribeOn(Schedulers.newThread())).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "merge accept = " + s);
            }
        });
    }

    public void reduce() {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "reduce accept = " + integer);
                    }
                });
    }

    public void scan() {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "reduce accept = " + integer);
                    }
                });

    }

    public void windowway() {
        Observable.interval(1, TimeUnit.SECONDS) // 间隔一秒发一次
                .take(15) // 最多接收15个
                .window(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Observable<Long>>() {
                    @Override
                    public void accept(@NonNull Observable<Long> longObservable) throws Exception {
                        Log.e(TAG, "Sub Divide begin...\n");
                        longObservable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(@NonNull Long aLong) throws Exception {
                                        Log.e(TAG, "Next:" + aLong + "\n");
                                    }
                                });
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timerDisposable != null && !timerDisposable.isDisposed()) {
            timerDisposable.dispose();
        }
    }
}
