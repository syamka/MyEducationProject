/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package my.project.collection.list;

import my.project.util.StringGeneration;
import my.project.util.Timer;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 08.11.13</p>
 */
public class MeasurePerformance{

    private List<String> list;
    private int capacity = 1000000;

    private Timer timer = new Timer();

    public MeasurePerformance(List list){
        this.list = list;
        fillList();
        run();
    }

    private void fillList(){
        StringGeneration generator = new StringGeneration();
        for(int i=0;i<capacity;i++){
            list.add(generator.getRandom());
        }
    }

    private void runMethod(String mthd){
        try {
            timer.refresh();
            getClass().getMethod(mthd).invoke(this);
            printTime(mthd);
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void run(){
        System.out.println("[START MEASUREMENTS " + list.getClass() + "]");
        String[] methods = {
            "iterateWithFor",
            "iterateWithIterator",
            "add",
            "removeByIndex"
        };

        for(String mthd: methods)
            runMethod(mthd);
    }

    public void iterateWithFor(){
        for(String s: list){}
    }

    public void iterateWithIterator(){
        Iterator<String> it = list.iterator();
        while(it.hasNext()){it.next();}
    }

    public void add(){
        list.add("test");
    }

    public String access(){
        int i = (int) Math.round(Math.random() * (capacity - 1));
        return list.get(i);
    }

    public void removeByIndex(){
        list.remove((int) Math.round(Math.random() * (capacity - 1)));
    }

    public void removeByValue(String value){
        list.remove(value);
    }

    private void printTime(String subj){
        System.out.println(subj+" execution time: " + timer.getTime() + " ms");
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class[] classes = {
            LinkedList.class,
            ArrayList.class
        };

        for(Class c: classes)
            new MeasurePerformance((List<String>)c.newInstance());

    }

}
