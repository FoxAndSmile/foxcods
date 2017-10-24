package com.foxcode.reactive.run;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PubSub {
    public static void main(String[] args) throws InterruptedException {
        Iterable<Integer> itr = Arrays.asList(1,2,3,4,5);
//        ExecutorService es = Executors.newSingleThreadExecutor();
        ExecutorService es = Executors.newCachedThreadPool();

        Publisher<Integer> pub = new Publisher<Integer>() {
            public void subscribe(Subscriber<? super Integer> subscriber) {
                Iterator<Integer> it = itr.iterator();

                subscriber.onSubscribe(new Subscription() {
                    public void request(long l) {
                        es.execute(() -> {
                            int i = 0;
                            try {
                                while (i++ < l) {
                                    if (it.hasNext()) {
                                        subscriber.onNext(it.next());
                                    } else {
                                        subscriber.onComplete();
                                        break;
                                    }
                                }
                            } catch(RuntimeException e) {
                                subscriber.onError(e);
                            }
                        });
                    }

                    public void cancel() {

                    }
                });
            }
        };

        Subscriber<Integer> sub = new Subscriber<Integer>() {
            Subscription subscription;

            public void onSubscribe(Subscription subscription) {
                System.out.println(Thread.currentThread().getName() + ": onSubscribe");
                this.subscription = subscription;
                this.subscription.request(1);
            }

            public void onNext(Integer integer) {
                System.out.println(Thread.currentThread().getName() + ": onNext = " + integer);
                this.subscription.request(1);
            }

            public void onError(Throwable throwable) {
                System.out.println("onError" + throwable.getMessage());
            }

            public void onComplete() {
                System.out.println(Thread.currentThread().getName() +"onComplate");
            }
        };

        pub.subscribe(sub);
        es.awaitTermination(10, TimeUnit.HOURS);
        es.shutdown();
//        es.shutdown();
    }
}
