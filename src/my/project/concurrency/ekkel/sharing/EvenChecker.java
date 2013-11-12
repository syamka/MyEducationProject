/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package my.project.concurrency.ekkel.sharing;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <h3></h3>
 * <p>
 *     Класс, запрашивающий числа у IntGenerator, пока не будет встречено нечетное
 * </p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 12.11.13</p>
 */
public class EvenChecker implements Runnable {

    private IntGenerator generator;

    private final int id;

    public EvenChecker(int id, IntGenerator generator) {
        this.id = id;
        this.generator = generator;
    }

    @Override
    public void run() {
        while(!generator.isCanceled()){
            int val = generator.next();
            if(val % 2 != 0){
                System.out.println(val + " not even!");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator gp, int count){
        System.out.println("Press Ctrl+C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0;i<count;i++)
            //Внимание ! IntGenerator - один, а значит за него будут конкурировать разные потоки (race condition)
            exec.execute(new EvenChecker(i, gp));
        exec.shutdown();
    }

    public static void test(IntGenerator gp){
        test(gp, 10);
    }
}
