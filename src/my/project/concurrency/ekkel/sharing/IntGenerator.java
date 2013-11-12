/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package my.project.concurrency.ekkel.sharing;

/**
 * <h3>Абстрактный генератор целых чисел</h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 12.11.13</p>
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;

    //генерация числа
    public abstract int next();
    // Allow this to be canceled:
    public void cancel(){
        canceled = true;
    }
    public boolean isCanceled() {
        return canceled;
    }

}
