/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package my.project.concurrency.ekkel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 08.11.13</p>
 */
public class SimpleCallable implements Callable<String> {
    private int id;

    public SimpleCallable(int id){
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result " + id;
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<String>> result = new ArrayList<Future<String>>();
        for(int i=0;i<10;i++){
            result.add(exec.submit(new SimpleCallable(i)));
        }
        for(Future f: result){
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (ExecutionException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            finally{
                exec.shutdown();
            }
        }
    }
}
