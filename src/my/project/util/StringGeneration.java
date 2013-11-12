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
public class StringGeneration {

    private int minlen = 5;
    private int maxlen = 15;

    private static final int minChar = 97;//48;
    private static final int maxChar = 122;

    public String getRandom(){
        int len = getRandomInt(minlen, maxlen);
        StringBuilder sb = new StringBuilder(len);
        for(int i=0; i<len; i++){
            sb.append((char) getRandomInt(minChar, maxChar));
        }

        return sb.toString();
    }

    public static void printChars(){
        for(int i = minChar; i<= maxChar; i++)
            System.out.println(Integer.toString(i) + " " + (char) i);
    }


    private int getRandomInt(int from, int to){
        return (int) Math.round(Math.random() * (to - from))  + from;
    }

    public static final void main(String...args){
        //printChars();
        System.out.println(new StringGeneration().getRandom());
    }

}
