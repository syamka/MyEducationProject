/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package my.project.concurrency.ekkel;

import java.util.concurrent.TimeUnit;

/**
 * <h3></h3>
 * <p>
 *     Daemon-потоки: выполняются в фоновом режиме.
 *     Если все потоки, кроме daemon, завершены, программа завершается.
 * </p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 11.11.13</p>
 */
public class SimpleDaemons implements Runnable {
    @Override
    public void run() {
        try {
            while(true){
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        //Блок finally не будет исполняться
        finally {
            System.out.println(Thread.currentThread() + " " + this + " finally");
        }
    }

    public static void main(String[] args) throws Exception {
        for(int i=0;i<10;i++){
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(102);
    }

}
