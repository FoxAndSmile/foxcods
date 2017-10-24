package com.foxcode.reactive.chapter1;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Operation1 {
    // 상대성이다 ~~ 기능은 비슷한데 먼가 다르다.
    // Iterable  <---> Observable (duality 에릭 마이어)
    // Iterable는 Pull 방식 = 소스 사용하는 곳에서 데이터를 끌어오는거다. next
    // Obsrevabl은 Push 방식 = 너가 가져가라~


    // Observable의 2가지 이슈 - Reactive에서 해결햇다고 한다.
    // 1. Observable은 Complete (끝)??? 끝이 있나?
    // 2. Error


    public static void main(String[] args) {
        // Source --> Event를 옵저버에게 던진다.~ Observer를 Observable에 등록을 시킨다~ Observable은 먼가 이벤트 발생시 Observer에게 Event를 던진다.
        Observer ob1 = new Observer() {
            @Override
            public void update(Observable o, Object arg) {  //
                System.out.println(Thread.currentThread().getName() + " " + arg);
            }
        };

        IntObservable io = new IntObservable();
        io.addObserver(ob1);

        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(io);

        System.out.println(Thread.currentThread().getName() + " EXIT");
        es.shutdown();
    }

    static class IntObservable extends Observable implements Runnable{
        @Override
        public void run() {
            for(int i=1; i<=10; i++) {
                setChanged();   // 먼저 호출
                notifyObservers(i); // Event push
            }
        }
    }

    public static void ItreableCode() {
        Iterable<Integer> iter = () ->
                new Iterator<Integer>() {
                    int i = 0;
                    final static int MAX = 10;

                    public boolean hasNext() {
                        return i < MAX;
                    }

                    public Integer next() {
                        return ++i;
                    }
                };

        for(Integer i : iter) {
            System.out.println(i);
        }
    }

}
