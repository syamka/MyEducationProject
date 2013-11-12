/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package my.project.util;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 08.11.13</p>
 */
public class Timer {

    private long start;

    public Timer(){
        refresh();
    }

    public void refresh(){
        start = System.currentTimeMillis();
    }

    public long getTime(){
        return System.currentTimeMillis() - start;
    }

    public float getTimeSeconds(){
        return (getTime() / 1000);
    }
}
