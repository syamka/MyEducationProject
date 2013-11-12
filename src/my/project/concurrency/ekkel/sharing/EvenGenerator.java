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
 * <h3>Не thread-safety генератор четных чисел</h3>
 * <p>
 *     Иллюстрация коллизии при коллективном доступе к одному и тому же полю нескольких потоков:
 *     через некоторое время после запуска EvenGenerator получает НЕчетное число, полученное из-за
 *     неатомарности операции next() и параллельного доступа.
 * </p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 12.11.13</p>
 */
public class EvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    //Для предотвращения иллюстрирумой проблемы, нужно сделать этот метод synchronized
    @Override
    public int next() {
        /**
         * поскольку +2 осуществляется в 2 операции, очевидно, что между ними число НЕЧЕТНОЕ,
         * а его используют и другие потоки
         */
        ++currentEvenValue;
        //Если раскомментировать строчку ниже, нечетное число будет получено быстрее
        //Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
