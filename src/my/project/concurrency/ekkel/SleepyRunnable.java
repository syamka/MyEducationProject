/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package my.project.concurrency.ekkel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 08.11.13</p>
 */
public class SleepyRunnable implements Runnable {
    private static int taskCnt = 0;
    private final int id = taskCnt++;

    private int count = 10;
    public SleepyRunnable(){}
    public SleepyRunnable(int cnt){
        count = cnt;
    }

    public void print(){
        System.out.println("#" + id + " " +count);
    }

    @Override
    public void run() {
        while(count-- > 0){
            print();
            try {
                /**
                 * Old-style: sleep был методом объекта Thread.
                 * SE 5/6: TimeUnit.
                 * Теперь каждый поток засыпает на секунду после каждого подсчета
                 */
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public static void threadrunning(){
        for(int i=0;i<countThreads;i++){
            new Thread(new SleepyRunnable()).start();
        }
    }

    /**
     *      Executor - промежуточный слой между Runnable и Thread
     *      Предоставляет API для управления потоками из коробки, например, количество потоков
     */
    public static void executorsrunning(){
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0;i<countThreads;i++){
            exec.execute(new SleepyRunnable());
        }
        exec.shutdown();
    }

    public static void main(String[] args) {
        executorsrunning();
    }

    public static final int countThreads = 5;
}
