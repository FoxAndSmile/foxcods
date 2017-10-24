package com.foxcode.reactive.chapter2;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PubSup {

    public static void main(String[] args) {

        Publisher<Integer> pub = new Publisher<Integer>() {
            Iterable<Integer> iter = Stream.iterate(1, a->a+1).limit(10).collect(Collectors.toList());

            @Override
            public void subscribe(Subscriber<? super Integer> subscriber) {
                subscriber.onSubscribe(new Subscription() {
                    @Override
                    public void request(long l) {
                        try {
                            iter.forEach(s -> subscriber.onNext(s));
                            subscriber.onComplete();
                        } catch(Exception e) {
                            subscriber.onError(e);
                        }
                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        };

        Subscriber<Integer> sub = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("onSubscribe");
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext = " + integer);

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError = " + throwable);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        pub.subscribe(sub);

    }
}
