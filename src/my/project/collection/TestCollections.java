/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package my.project.collection;

import my.project.util.StringGeneration;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 08.11.13</p>
 */
public class TestCollections {

    private static StringGeneration generator = new StringGeneration();

    public static final void testQueue(int capacity){
        Queue<String> q = new ArrayBlockingQueue<String>(capacity);
        for(int i=0;i<capacity;i++)
            q.add(generator.getRandom());
        System.out.println(q);
    }

    public static void main(String[] args) {
        String[] arr = {"a","b","c"};
        List lst = Arrays.asList(arr);
        System.out.println(lst.getClass());
    }

}
