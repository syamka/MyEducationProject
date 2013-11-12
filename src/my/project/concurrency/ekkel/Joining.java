/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package my.project.concurrency.ekkel;

/**
 * <h3>Иллюстрация JOIN потоков</h3>
 * <p>
 *     join() - операция, при которой один поток "присоединяется" к другому, то есть,
 *     он ждет, пока тот, к кому он присоединен, не закончит выполнение, и только потом выполняется сам.
 *
 *     Суть примера:
 *          2 пары потоков: Joiner присоединяется к Sleeper
 *          sleepy2 - прерывается искусственно, и тут же выполняется joiny2
 *          sleepy1 - дожидается окончания своего sleep, и только после этого выполняется joiny1
 * </p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 11.11.13</p>
 */
public class Joining {

    public static void main(String[] args) {
        Sleeper
            sleepy1 = new Sleeper("Sleepy 1", 1500),
            sleepy2 = new Sleeper("Sleepy 2", 1500);
        Joiner
            joiny1 = new Joiner("Joiny 1", sleepy1),
            joiny2 = new Joiner("Joiny 2", sleepy2);

        sleepy2.interrupt();
    }
}

class Sleeper extends Thread{
    private int duration;

    public Sleeper(String name, int duration){
        super(name);
        this.duration = duration;
        start();
    }

    public void run(){
        try{
            sleep(duration);
        }
        catch(InterruptedException e){
            //isInterrupted будет возвращать верное значение (true) только ПОСЛЕ обработки исключения,
            //так что здесь - false
            System.out.println(getName() + " was interrupted. isInterrupted: " + isInterrupted());
            return;
        }
        System.out.println(getName() + " has awakened");
    }
}

class Joiner extends Thread{
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper){
        super(name);
        this.sleeper = sleeper;
        start();
    }

    public void run(){
        try{
            sleeper.join();
        }
        catch(InterruptedException e){
            System.out.println(" interrupted");
        }
        System.out.println(getName() + " join complete.");
    }
}
